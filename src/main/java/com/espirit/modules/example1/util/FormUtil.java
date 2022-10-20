package com.espirit.modules.example1.util;

import com.espirit.modules.example1.project.ExampleProjectApp;
import com.espirit.modules.example1.project.ExampleProjectConfig;
import de.espirit.firstspirit.access.BaseContext;

public class FormUtil {

    // this is just an example of a utility class used to create an FS form dialog, if needed


    private static final Class<?> LOGGER = FormUtil.class;

    public static String getExampleForm(BaseContext context) {

        try {

            final String domComponent = ExampleProjectConfig.values(context, ExampleProjectApp.class).getString(ExampleProjectConfig.DOM_COMPONENT);
            final String textComponent = ExampleProjectConfig.values(context, ExampleProjectApp.class).getString(ExampleProjectConfig.TEXT_COMPONENT);

            final StringBuilder formBuilder = new StringBuilder();

            formBuilder.append("<CMS_MODULE>");

            formBuilder.append("<CMS_INPUT_DOM name=\"" + domComponent +"\" hFill=\"yes\" list=\"no\" bold=\"no\" italic=\"no\">");
            formBuilder.append("<FORMATS>");
            formBuilder.append("<TEMPLATE name=\"p\"/>");
            formBuilder.append("</FORMATS>");
            formBuilder.append("<LANGINFOS>");
            formBuilder.append("<LANGINFO lang=\"*\" label=\"DOM-Text\"/>");
            formBuilder.append("</LANGINFOS>");
            formBuilder.append("<LINKEDITORS>");
            formBuilder.append("<LINKEDITOR name=\"\"/>");
            formBuilder.append("</LINKEDITORS>");
            formBuilder.append("</CMS_INPUT_DOM>");

            formBuilder.append("<CMS_INPUT_TEXT name=\"" + textComponent + "\" hFill=\"yes\">");
            formBuilder.append("<LANGINFOS>");
            formBuilder.append("<LANGINFO lang=\"*\" label=\"Simple-Text\"/>");
            formBuilder.append("</LANGINFOS>");
            formBuilder.append("</CMS_INPUT_TEXT>");

            formBuilder.append("</CMS_MODULE>");

            return formBuilder.toString();

        } catch (Exception e) {

            //do something, i.e. log error outputs

            return null;
        }
    }

}
