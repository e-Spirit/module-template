package com.espirit.modules.example1.dataaccess.session.aspects;

import com.espirit.modules.example1.ExamplePOJO;
import de.espirit.firstspirit.access.Language;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.DataTemplating;

/**
 * Basic class to implement report fly-outs.
 * Please see FirstSpirit API for more information.
 */
public class ExamplePostingDataTemplatingAspect implements DataTemplating<ExamplePOJO> {

    //example tooltip HTML for report items

    @Override
    public String getTemplate(ExamplePOJO posting, Language language) {
        String html = "<div style=\"width: 450px;\">" +
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
