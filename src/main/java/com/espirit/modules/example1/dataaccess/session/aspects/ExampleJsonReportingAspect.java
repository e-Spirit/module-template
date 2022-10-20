package com.espirit.modules.example1.dataaccess.session.aspects;

import com.espirit.modules.example1.ExamplePOJO;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.JsonSupporting;
import de.espirit.firstspirit.generate.functions.json.JsonGenerationContext;
import de.espirit.firstspirit.json.JsonElement;
import de.espirit.firstspirit.json.JsonObject;
import de.espirit.firstspirit.json.JsonPair;
import de.espirit.firstspirit.json.values.JsonStringValue;

/**
 * Basic class to support JSON representations in reports.
 * Please see FirstSpirit API for more information.
 */
public class ExampleJsonReportingAspect implements JsonSupporting<ExamplePOJO> {

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
