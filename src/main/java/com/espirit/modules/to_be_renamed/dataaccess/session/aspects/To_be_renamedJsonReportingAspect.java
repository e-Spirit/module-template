package com.espirit.modules.to_be_renamed.dataaccess.session.aspects;

import com.espirit.modules.to_be_renamed.To_be_renamedPOJO;
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
public class To_be_renamedJsonReportingAspect implements JsonSupporting<To_be_renamedPOJO> {

    @Override
    public JsonElement<?> handle(JsonGenerationContext jsonGenerationContext, To_be_renamedPOJO object) {
        final JsonObject jsonResult = JsonObject.create();
        jsonResult.put(JsonPair.of("var", JsonStringValue.of(object.getVar())));
        return jsonResult;
    }

    @Override
    public Class<To_be_renamedPOJO> getSupportedClass() {
        return To_be_renamedPOJO.class;
    }
}
