package com.espirit.modules.example1.dataaccess;

import com.espirit.modules.example1.connector.ExampleConnector;
import com.espirit.modules.example1.util.ExampleIcons;
import com.espirit.modules.example1.ExamplePOJO;
import de.espirit.common.base.Logging;
import de.espirit.firstspirit.access.BaseContext;
import de.espirit.firstspirit.access.Language;
import de.espirit.firstspirit.access.editor.ValueIndexer;
import de.espirit.firstspirit.agency.Image;
import de.espirit.firstspirit.agency.TransferAgent;
import de.espirit.firstspirit.client.plugin.dataaccess.DataAccessSession;
import de.espirit.firstspirit.client.plugin.dataaccess.DataAccessSessionBuilder;
import de.espirit.firstspirit.client.plugin.dataaccess.DataSnippetProvider;
import de.espirit.firstspirit.client.plugin.dataaccess.DataStreamBuilder;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.*;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.transfer.HandlerHost;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.transfer.SupplierHost;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.transfer.TransferHandling;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.transfer.TransferSupplying;
import de.espirit.firstspirit.client.search.SegmentProvider;
import de.espirit.firstspirit.generate.functions.json.JsonGenerationContext;
import de.espirit.firstspirit.json.JsonElement;
import de.espirit.firstspirit.json.JsonObject;
import de.espirit.firstspirit.json.JsonPair;
import de.espirit.firstspirit.json.values.JsonStringValue;

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

	private ExampleDataAccessSession(BaseContext context) {
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
		return new ExampleDataStream.Builder(_context);
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

	/**
	 * Basic class to support JSON representations in reports.
	 * Please see FirstSpirit API for more information.
	 */
	public static class ExampleJsonReportingAspect implements JsonSupporting<ExamplePOJO> {

		@Override
		public JsonElement<?> handle(JsonGenerationContext jsonGenerationContext, ExamplePOJO example) {
			final JsonObject jsonResult = JsonObject.create();
			jsonResult.put(JsonPair.of("var", JsonStringValue.of(example.getVar())));
			return jsonResult;
		}

		@Override
		public Class<ExamplePOJO> getSupportedClass() {
			return ExamplePOJO.class;
		}
	}

	/**
	 * Basic class to create a Data Access Session.
	 * Please see FirstSpirit API for more information.
	 */
	public static class Builder implements DataAccessSessionBuilder<ExamplePOJO> {

		private final SessionBuilderAspectMap _aspects = new SessionBuilderAspectMap();

		@Override
		public DataAccessSession<ExamplePOJO> createSession(BaseContext context) {
			return new ExampleDataAccessSession(context);
		}

		@Override
		public <A> A getAspect(SessionBuilderAspectType<A> aspectType) {
			return _aspects.get(aspectType);
		}
	}

	/**
	 * Basic class to implement report snippet representations.
	 * Please see FirstSpirit API for more information.
	 */
	public static class ExampleDataSnippetProvider implements DataSnippetProvider<ExamplePOJO> {

		private final BaseContext _context;
		private final Image<?> _icon;

		private ExampleDataSnippetProvider(BaseContext context) {
			_context = context;

			if (context.is(BaseContext.Env.WEBEDIT)) {
				_icon = null;
			} else {
				_icon = ExampleIcons.getPosting(context);
			}
		}

		@Override
		public Image<?> getIcon(ExamplePOJO posting) {

			//return the report icon

			return _icon;
		}

		@Override
		public Image<?> getThumbnail(ExamplePOJO example, Language language) {

			//returns the thumbnail image for a single report item

			return null;
		}

		@Override
		public String getHeader(ExamplePOJO example, Language language) {

			//returns the header text for a single report item

			return example.getVar();
		}

		@Override
		public String getExtract(ExamplePOJO example, Language language) {

			//returns the extract text for a single report item

			return example.getVar();
		}
	}

	/**
	 * Basic class to internally handle data transfers, i.e. drag and drop, etc.
	 * Please see FirstSpirit API for more information.
	 */
	public static class ExampleTransferHandlingAspect implements TransferHandling<ExamplePOJO> {

		private final BaseContext _context;

		private ExampleTransferHandlingAspect(BaseContext context) {
			_context = context;
		}

		@Override
		public void registerHandlers(HandlerHost<ExamplePOJO> host) {
			TransferAgent transferAgent = _context.requireSpecialist(TransferAgent.TYPE);
			host.registerHandler(transferAgent.getRawValueType(ExamplePOJO.class), new ExampleHandler());
		}

		static class ExampleHandler implements HandlerHost.Handler<ExamplePOJO, ExamplePOJO> {

			@Override
			public List<ExamplePOJO> handle(List<ExamplePOJO> exampleList) {
				return exampleList;
			}
		}
	}

	/**
	 * Basic class to internally handle data transfers, i.e. drag and drop, etc.
	 * Please see FirstSpirit API for more information.
	 */
	public static class ExampleTransferSupplyingAspect implements TransferSupplying<ExamplePOJO> {

		private final BaseContext _context;

		private ExampleTransferSupplyingAspect(BaseContext context) {
			_context = context;
		}

		@Override
		public void registerSuppliers(SupplierHost<ExamplePOJO> host) {
			TransferAgent transferAgent = _context.requireSpecialist(TransferAgent.TYPE);
			host.registerSupplier(transferAgent.getRawValueType(ExamplePOJO.class), new ExampleSupplier());
			host.registerSupplier(transferAgent.getPlainTextType(), new ExampleTextSupplier());
			host.registerSupplier(transferAgent.getQuerySegmentType(), new ExampleSegmentSupplier());
		}

		static class ExampleSupplier implements SupplierHost.Supplier<ExamplePOJO, ExamplePOJO> {

			@Override
			public List<ExamplePOJO> supply(ExamplePOJO example) {
				return Collections.singletonList(example);
			}
		}

		static class ExampleTextSupplier implements SupplierHost.Supplier<ExamplePOJO, String> {

			@Override
			public List<String> supply(ExamplePOJO example) {
				return Collections.singletonList(example.getVar());
			}
		}

		static class ExampleSegmentSupplier implements SupplierHost.Supplier<ExamplePOJO, SegmentProvider> {

			@Override
			public List<SegmentProvider> supply(ExamplePOJO example) {
				return Collections.singletonList(new ExampleQuerySegmentProvider(example.getVar()));
			}

			static class ExampleQuerySegmentProvider implements SegmentProvider {

				private final String _segment;

				ExampleQuerySegmentProvider(String segment) {
					_segment = segment;
				}

				@Override
				public String getSegment() {
					return _segment;
				}
			}
		}
	}

	/**
	 * Basic class to implement report fly-outs.
	 * Please see FirstSpirit API for more information.
	 */
	public static class ExamplePostingDataTemplatingAspect implements DataTemplating<ExamplePOJO> {

		//example tooltip HTML for report items

		@Override
		public String getTemplate(ExamplePOJO posting, Language language) {
			String html =  "<div style=\"width: 450px;\">" +
								"<div style=\"font-size: 1.2em; line-height: 1.3;\">" +
									"${var}" +
								"</div>" +
							"</div>";
			return html;
		}

		@Override
		public void registerParameters(ParameterSet parameters, ExamplePOJO example, Language language) {

			parameters.addHtml("var", example.getVar());

		}
	}

	/**
	 * Basic class to internally handle report listings
	 * Please see FirstSpirit API for more information.
	 */
	public static class ExamplePostingValueIndexingAspect implements ValueIndexing {

		@Override
		public void appendIndexData(String identifier, Language language, boolean recursive, ValueIndexer indexer) {
			indexer.append(ValueIndexer.VALUE_FIELD, identifier);
		}
	}
}
