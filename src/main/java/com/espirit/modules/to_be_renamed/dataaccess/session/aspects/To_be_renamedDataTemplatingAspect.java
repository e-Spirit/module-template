package com.espirit.modules.to_be_renamed.dataaccess.session.aspects;

import com.espirit.modules.to_be_renamed.To_be_renamedPOJO;
import de.espirit.firstspirit.access.Language;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.DataTemplating;

/**
 * Basic class to implement report fly-outs.
 * Please see FirstSpirit API for more information.
 */
public class To_be_renamedDataTemplatingAspect implements DataTemplating<To_be_renamedPOJO> {

    //tooltip HTML for report items

    @Override
    public String getTemplate(To_be_renamedPOJO object, Language language) {
        String html = "<div style=\"width: 450px;\">" +
                "<div style=\"font-size: 1.2em; line-height: 1.3;\">" +
                "${var}" +
                "</div>" +
                "</div>";
        return html;
    }

    @Override
    public void registerParameters(ParameterSet parameters, To_be_renamedPOJO object, Language language) {

        parameters.addHtml("var", object.getVar());

    }
}
