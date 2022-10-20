package com.espirit.modules.example1.dataaccess.datastream;

import com.espirit.modules.example1.ExamplePOJO;
import com.espirit.modules.example1.connector.ExampleConnector;
import com.espirit.modules.example1.dataaccess.datastream.aspects.ExampleFilterableAspect;
import com.espirit.modules.example1.util.ResourceHandler;
import de.espirit.common.base.Logging;
import de.espirit.firstspirit.access.BaseContext;
import de.espirit.firstspirit.access.Language;
import de.espirit.firstspirit.agency.LanguageAgent;
import de.espirit.firstspirit.client.plugin.dataaccess.DataStream;
import de.espirit.firstspirit.client.plugin.dataaccess.DataStreamBuilder;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.Filterable;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.StreamBuilderAspectMap;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.StreamBuilderAspectType;
import de.espirit.firstspirit.client.plugin.report.Parameter;
import de.espirit.firstspirit.client.plugin.report.ParameterSelect;
import de.espirit.firstspirit.webedit.WebeditUiAgent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The FirstSpirit Example data stream builder.
 * Please see FirstSpirit API for more information.
 */
public class ExampleDataStreamBuilder implements DataStreamBuilder<ExamplePOJO> {

    private static final Class<?> LOGGER = ExampleDataStreamBuilder.class;

    private final String BUNDLE_NAME = "example";

    private ExampleFilterableAspect filterableAspect = null;
    private StreamBuilderAspectMap aspects = null;
    private ExampleConnector exampleConnector = null;

    /**
     * Instantiates a new Builder.
     *
     * @param context the context
     */
    public ExampleDataStreamBuilder(BaseContext context) {

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
            this.filterableAspect = new ExampleFilterableAspect(selectItems);
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
