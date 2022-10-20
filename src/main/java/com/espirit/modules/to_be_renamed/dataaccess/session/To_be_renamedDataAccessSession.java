package com.espirit.modules.to_be_renamed.dataaccess.session;

import com.espirit.modules.to_be_renamed.connector.To_be_renamedConnector;
import com.espirit.modules.to_be_renamed.dataaccess.datastream.To_be_renamedDataStreamBuilder;
import com.espirit.modules.to_be_renamed.To_be_renamedPOJO;
import com.espirit.modules.to_be_renamed.dataaccess.session.aspects.*;
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
public class To_be_renamedDataAccessSession implements DataAccessSession<To_be_renamedPOJO> {

	private final BaseContext _context;
	private final SessionAspectMap _aspects;

	private static final Class<?> LOGGER = To_be_renamedDataAccessSession.class;

	public To_be_renamedDataAccessSession(BaseContext context) {
		_context = context;

		_aspects = new SessionAspectMap();
		_aspects.put(TransferHandling.TYPE, new To_be_renamedTransferHandlingAspect(_context));
		_aspects.put(TransferSupplying.TYPE, new To_be_renamedTransferSupplyingAspect(_context));
		_aspects.put(DataTemplating.TYPE, new To_be_renamedDataTemplatingAspect());
		_aspects.put(ValueIndexing.TYPE, new To_be_renamedValueIndexingAspect());
		_aspects.put(JsonSupporting.TYPE, new To_be_renamedJsonReportingAspect());
		//_aspects.put(UrlSupporting.TYPE, new To_be_renamedUrlSupportingAspect());
	}

	@Override
	public DataSnippetProvider<To_be_renamedPOJO> createDataSnippetProvider() {
		return new To_be_renamedDataSnippetProvider(_context);
	}

	@Override
	public DataStreamBuilder<To_be_renamedPOJO> createDataStreamBuilder() {
		return new To_be_renamedDataStreamBuilder(_context);
	}

	@Override
	public <A> A getAspect(SessionAspectType<A> aspectType) {
		return _aspects.get(aspectType);
	}

	@Override
	public To_be_renamedPOJO getData(String identifier) throws NoSuchElementException {
		List<To_be_renamedPOJO> objects = getData(Collections.singletonList(identifier));
		return objects.isEmpty() ? null : objects.get(0);
	}

	@Override
	public List<To_be_renamedPOJO> getData(Collection<String> identifierList) {
		try {
			To_be_renamedConnector to_be_renamedConnector = To_be_renamedConnector.getInstance(_context);
			return to_be_renamedConnector.getSomeData(identifierList);
		} catch (IOException ioe) {
			Logging.logError(ioe.getMessage(), ioe, LOGGER);
		}
		return new ArrayList<To_be_renamedPOJO>();
	}

	@Override
	public String getIdentifier(To_be_renamedPOJO object) throws NoSuchElementException {
		return object.getVar();
	}

}
