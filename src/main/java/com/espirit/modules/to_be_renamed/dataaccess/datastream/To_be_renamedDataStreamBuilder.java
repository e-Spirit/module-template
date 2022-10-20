package com.espirit.modules.to_be_renamed.dataaccess.datastream;

import com.espirit.modules.to_be_renamed.To_be_renamedPOJO;
import com.espirit.modules.to_be_renamed.connector.To_be_renamedConnector;
import com.espirit.modules.to_be_renamed.dataaccess.datastream.aspects.To_be_renamedFilterableAspect;
import com.espirit.modules.to_be_renamed.util.ResourceHandler;
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
 * The FirstSpirit data stream builder.
 * Please see FirstSpirit API for more information.
 */
public class To_be_renamedDataStreamBuilder implements DataStreamBuilder<To_be_renamedPOJO> {

    private static final Class<?> LOGGER = To_be_renamedDataStreamBuilder.class;

    private final String BUNDLE_NAME = "to_be_renamed";

    private To_be_renamedFilterableAspect filterableAspect = null;
    private StreamBuilderAspectMap aspects = null;
    private To_be_renamedConnector to_be_renamedConnector = null;

    /**
     * Instantiates a new Builder.
     *
     * @param context the context
     */
    public To_be_renamedDataStreamBuilder(BaseContext context) {

        final LanguageAgent languageAgent = context.requireSpecialist(LanguageAgent.TYPE);
        final WebeditUiAgent webEditUiAgent = context.requestSpecialist(WebeditUiAgent.TYPE);
        final Language masterLanguage = languageAgent.getMasterLanguage();
        Language displayLanguage = masterLanguage;
        if (webEditUiAgent != null) {
            displayLanguage = webEditUiAgent.getDisplayLanguage();
        }

        ResourceHandler resourceHandler = ResourceHandler.load(BUNDLE_NAME, displayLanguage.getLocale());

        try {
            this.to_be_renamedConnector = to_be_renamedConnector.getInstance(context);
            this.aspects = new StreamBuilderAspectMap();

            List<ParameterSelect.SelectItem> selectItems = new ArrayList<>();
            if (this.to_be_renamedConnector != null) {

                //set up some sort of filter criteria

                ParameterSelect.SelectItem selectItemAll = Parameter.Factory.createSelectItem(resourceHandler.getString("report.select.all"), "all");
                selectItems.add(selectItemAll);

                // do something
                // i.e. use the connector class to fill the selectItems list with apopriate filter data

                //ParameterSelect.SelectItem selectItem = Parameter.Factory.createSelectItem("some name", "some id");
                //selectItems.add(selectItem);

            }
            this.filterableAspect = new To_be_renamedFilterableAspect(selectItems);
            this.aspects.put(Filterable.TYPE, this.filterableAspect);

        } catch (IOException ioe) {
            Logging.logError(ioe.getMessage(), ioe, LOGGER);
        }
    }

    @Override
    public DataStream<To_be_renamedPOJO> createDataStream() {

        //get the actual data from the connector

        return new To_be_renamedDataStream(this.to_be_renamedConnector.getSomeData(this.filterableAspect.getSomeData()));
    }

    @Override
    public <A> A getAspect(StreamBuilderAspectType<A> aspectType) {
        return this.aspects.get(aspectType);
    }
}
