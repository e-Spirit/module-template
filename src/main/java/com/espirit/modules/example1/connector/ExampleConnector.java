package com.espirit.modules.example1.connector;

import com.espirit.modules.example1.ExamplePOJO;
import com.espirit.modules.example1.project.ExampleProjectApp;
import com.espirit.modules.example1.project.ExampleProjectConfig;
import de.espirit.firstspirit.agency.SpecialistsBroker;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ExampleConnector {

    //connector class to take all third party API communication

    private static final Class<?> LOGGER = ExampleConnector.class;

    private static ExampleConnector connector = null;

    private String var1;
    private String var2;

    //get a connector instance using a service broker to get all information from the project app config panel
    public static ExampleConnector getInstance(SpecialistsBroker broker) throws IOException {

        if (connector != null) {
            return connector;
        } else {
            connector = new ExampleConnector(broker);
            return connector;
        }
    }

    //get a connector instance with manual values
    public static ExampleConnector getInstance(String var1, String var2) throws IOException {

        if (connector != null) {
            return connector;
        } else {
            connector = new ExampleConnector(var1, var2);
            return connector;
        }
    }

    //constructor with project app config panel values
    private ExampleConnector(SpecialistsBroker broker) {
        var1 = ExampleProjectConfig.values(broker, ExampleProjectApp.class).getString(ExampleProjectConfig.VAR_1);
        var2 = ExampleProjectConfig.values(broker, ExampleProjectApp.class).getString(ExampleProjectConfig.VAR_2);
    }

    //constructor with manual values if needed
    private ExampleConnector(String var1, String var2) {
       this.var1 = var1;
        this.var2 = var2;
    }

    //implement some methods to communicate with some third party API

    public List<ExamplePOJO> getSomeData(String filterBySomething) {
        return new ArrayList<ExamplePOJO>();
    }

    public List<ExamplePOJO> getSomeData(Collection<String> filterBySomethingMultiple) {
        return new ArrayList<ExamplePOJO>();
    }

}
