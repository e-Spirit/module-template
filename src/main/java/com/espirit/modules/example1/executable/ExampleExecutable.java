package com.espirit.modules.example1.executable;

import com.espirit.moddev.components.annotations.PublicComponent;

import de.espirit.firstspirit.access.ClientScriptContext;
import de.espirit.firstspirit.access.script.Executable;

import java.io.Writer;

import java.util.Map;

@PublicComponent(name = "DevconCloudinary1Executable")
public class ExampleExecutable implements Executable {
    /*
        #!executable-class
        ExampleExecutable
     */

    // this executable can be used within beanshell scripts, etc.

    private static final Class<?> LOGGER = ExampleExecutable.class;

    @Override
    public Object execute(Map<String, Object> map, Writer writer, Writer writer1) {

        final ClientScriptContext context = (ClientScriptContext) map.get("context");

        try {

            //do something

        } catch (Exception ioe) {

            //do something, i.e. log error outputs

        }
        return null;
    }
}
