package com.espirit.modules.to_be_renamed.project;

import com.espirit.ps.psci.genericconfiguration.ExecuteAction;
import com.espirit.ps.psci.genericconfiguration.GenericConfigPanel;
import de.espirit.firstspirit.module.ProjectEnvironment;

public class To_be_renamedProjectConfig extends GenericConfigPanel<ProjectEnvironment> {

    private static final Class<?> LOGGER = To_be_renamedProjectConfig.class;

    public static final String VAR_1 = "var1";
    public static final String VAR_2 = "var2";

    public static final String DOM_COMPONENT = "domComponent";
    public static final String TEXT_COMPONENT = "textComponent";



    @Override
    protected void configure() {

        ExecuteAction ea = () -> {
            try {

                //do something when the configuration is loaded

            }catch (Exception e) {

                //do something, i.e. log error outputs

            }
        };

        //set up a configuration
        //you can use and concatenate several generic fields like text, password, checkbox or hiddenString

        builder().text("Var 1", VAR_1, "some value", "Variable for a basic configuration")
                .password("Var 2", VAR_2, "some value", "Variable for a basic configuration")
                .hiddenString(DOM_COMPONENT, "sc_dom")
                .hiddenString(TEXT_COMPONENT, "sc_text");
    }
}