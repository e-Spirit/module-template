package com.espirit.modules.example1.dataaccess.session;

import com.espirit.modules.example1.connector.ExampleConnector;
import com.espirit.modules.example1.dataaccess.datastream.ExampleDataStreamBuilder;
import com.espirit.modules.example1.ExamplePOJO;
import com.espirit.modules.example1.dataaccess.session.aspects.*;
import de.espirit.common.base.Logging;
import de.espirit.firstspirit.access.BaseContext;
import de.espirit.firstspirit.client.plugin.dataaccess.DataAccessSession;
import de.espirit.firstspirit.client.plugin.dataaccess.DataSnippetProvider;
import de.espirit.firstspirit.client.plugin.dataaccess.DataStreamBuilder;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.*;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.transfer.TransferHandling;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.transfer.TransferSupplying;

import java.io.IOException;
import java.util.*;

/**
 * Basic class to provide a Data Access Session to support the Data Access Plugin.
 * Please see FirstSpirit API for more information.
 */
public class ExampleDataAccessSession implements DataAccessSession<ExamplePOJO> {

	private final BaseContext _context;
	private final SessionAspectMap _aspects;

	private static final Class<?> LOGGER = ExampleDataAccessSession.class;

	public ExampleDataAccessSession(BaseContext context) {
		_context = context;

		_aspects = new SessionAspectMap();
		_aspects.put(TransferHandling.TYPE, new ExampleTransferHandlingAspect(_context));
		_aspects.put(TransferSupplying.TYPE, new ExampleTransferSupplyingAspect(_context));
		_aspects.put(DataTemplating.TYPE, new ExamplePostingDataTemplatingAspect());
		_aspects.put(ValueIndexing.TYPE, new ExamplePostingValueIndexingAspect());
		_aspects.put(JsonSupporting.TYPE, new ExampleJsonReportingAspect());
		//_aspects.put(UrlSupporting.TYPE, new ExampleUrlSupportingAspect());
	}

	@Override
	public DataSnippetProvider<ExamplePOJO> createDataSnippetProvider() {
		return new ExampleDataSnippetProvider(_context);
	}

	@Override
	public DataStreamBuilder<ExamplePOJO> createDataStreamBuilder() {
		return new ExampleDataStreamBuilder(_context);
	}

	@Override
	public <A> A getAspect(SessionAspectType<A> aspectType) {
		return _aspects.get(aspectType);
	}

	@Override
	public ExamplePOJO getData(String identifier) throws NoSuchElementException {
		List<ExamplePOJO> examples = getData(Collections.singletonList(identifier));
		return examples.isEmpty() ? null : examples.get(0);
	}

	@Override
	public List<ExamplePOJO> getData(Collection<String> identifierList) {
		try {
			ExampleConnector exampleConnector = ExampleConnector.getInstance(_context);
			return exampleConnector.getSomeData(identifierList);
		} catch (IOException ioe) {
			Logging.logError(ioe.getMessage(), ioe, LOGGER);
		}
		return new ArrayList<ExamplePOJO>();
	}

	@Override
	public String getIdentifier(ExamplePOJO example) throws NoSuchElementException {
		return example.getVar();
	}

}
