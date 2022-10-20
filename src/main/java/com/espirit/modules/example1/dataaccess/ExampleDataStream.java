package com.espirit.modules.example1.dataaccess;

import com.espirit.modules.example1.ExamplePOJO;
import com.espirit.modules.example1.connector.ExampleConnector;
import com.espirit.modules.example1.util.ResourceHandler;
import de.espirit.common.base.Logging;
import de.espirit.common.tools.Strings;
import de.espirit.firstspirit.access.BaseContext;
import de.espirit.firstspirit.access.Language;
import de.espirit.firstspirit.agency.LanguageAgent;
import de.espirit.firstspirit.client.plugin.dataaccess.DataStream;
import de.espirit.firstspirit.client.plugin.dataaccess.DataStreamBuilder;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.Filterable;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.StreamBuilderAspectMap;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.StreamBuilderAspectType;
import de.espirit.firstspirit.client.plugin.report.Parameter;
import de.espirit.firstspirit.client.plugin.report.ParameterMap;
import de.espirit.firstspirit.client.plugin.report.ParameterSelect;
import de.espirit.firstspirit.webedit.WebeditUiAgent;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The FirstSpirit Example posting data stream.
 * Please see FirstSpirit API for more information.
 */
public class ExampleDataStream implements DataStream<ExamplePOJO> {

	//this class is used to provide the actual data stream for a report
	//it sets up filter variables, search queries and pagination support as well



	private final List<ExamplePOJO> examples;
	private int total = -1;
	private boolean hasNext = false;

	private int index = -1;

	private int page = -1;



	private static final Class<?> LOGGER = ExampleDataStream.class;

	public ExampleDataStream(final List<ExamplePOJO> examples) {
		this.examples = examples;
		this.total = this.examples.size();
		if (this.total > 0) {
			this.hasNext = true;
			this.index = 0;
			this.page = 1;
		}
	}

	@Override
	public void close() {
		// Nothing
	}

	@Override
	public List<ExamplePOJO> getNext(int count) {

		List<ExamplePOJO> result = new ArrayList<ExamplePOJO>();

		if (!hasNext()) {
			return Collections.emptyList();
		} else{

			int limit = count*this.page;

			while (this.index < limit) {
				result.add(this.examples.get(this.index));
				try {
					this.examples.get(this.index+1);
				} catch (IndexOutOfBoundsException ioe) {
					this.hasNext = false;
					break;
				}
				this.index++;
			}
			this.page++;
		}
		return result;
	}

	@Override
	public int getTotal() {
		return this.examples.size();
	}

	@Override
	public boolean hasNext() {
		return this.hasNext;
	}

	/**
	 * The FirstSpirit Example data stream builder.
	 * Please see FirstSpirit API for more information.
	 */
	public static class Builder implements DataStreamBuilder<ExamplePOJO> {

		private final String BUNDLE_NAME = "example";

		private FilterableAspect filterableAspect = null;
		private StreamBuilderAspectMap aspects = null;
		private ExampleConnector exampleConnector = null;

		/**
		 * Instantiates a new Builder.
		 *
		 * @param context the context
		 */
		Builder(BaseContext context) {

			final LanguageAgent languageAgent = context.requireSpecialist(LanguageAgent.TYPE);
			final WebeditUiAgent webEditUiAgent = context.requestSpecialist(WebeditUiAgent.TYPE);
			final Language masterLanguage = languageAgent.getMasterLanguage();
			Language displayLanguage = masterLanguage;
			if (webEditUiAgent != null) {
				displayLanguage = webEditUiAgent.getDisplayLanguage();
			}

			ResourceHandler resourceHandler = ResourceHandler.load(BUNDLE_NAME, displayLanguage.getLocale());

			try {
				this.exampleConnector = ExampleConnector.getInstance(context);
				this.aspects = new StreamBuilderAspectMap();

				List<ParameterSelect.SelectItem> selectItems = new ArrayList<>();
				if (this.exampleConnector != null) {

					//example to set up some sort of filter criteria

					ParameterSelect.SelectItem selectItemAll = Parameter.Factory.createSelectItem(resourceHandler.getString("report.select.all"), "all");
					selectItems.add(selectItemAll);

					// do something
					// i.e. use the connector class to fill the selectItems list with apopriate filter data

					//ParameterSelect.SelectItem selectItem = Parameter.Factory.createSelectItem("some name", "some id");
					//selectItems.add(selectItem);

				}
				this.filterableAspect = new FilterableAspect(selectItems);
				this.aspects.put(Filterable.TYPE, this.filterableAspect);

			} catch (IOException ioe) {
				Logging.logError(ioe.getMessage(), ioe, LOGGER);
			}
		}

		@Override
		public DataStream<ExamplePOJO> createDataStream() {

			//get the actual data from the connector

			return new ExampleDataStream(this.exampleConnector.getSomeData(this.filterableAspect.getSomeData()));
		}

		@Override
		public <A> A getAspect(StreamBuilderAspectType<A> aspectType) {
			return this.aspects.get(aspectType);
		}
	}

	/**
	 * Aspect to provide filters for a data stream.
	 * Please see FirstSpirit API for more information.
	 */
	public static class FilterableAspect implements Filterable {

		private ParameterSelect someFilterVariable = null;

		private ParameterMap filter = null;

		private FilterableAspect(List<ParameterSelect.SelectItem> selectItems) {
			if (selectItems != null && !selectItems.isEmpty()) {
				this.someFilterVariable = Parameter.Factory.createSelect("someFilterVariableFilterSelect", selectItems, "all");
			}
		}

		@Override
		public List<Parameter<?>> getDefinedParameters() {
			List<Parameter<?>> pList = new ArrayList<>();
			if (this.someFilterVariable != null) {
				pList.add(this.someFilterVariable);
			}

			return pList;
		}

		@Override
		public void setFilter(ParameterMap filter) {
			this.filter = filter;
		}

		/**
		 * Gets the selected filter variable.
		 *
		 * @return the filter variable
		 */
		@Nullable
		String getSomeData() {
			String someFilterVariable = null;
			if (this.someFilterVariable != null) {
				someFilterVariable = this.filter.get(this.someFilterVariable);
			}
			return !Strings.isEmpty(someFilterVariable) ? someFilterVariable : null;
		}
	}
}
