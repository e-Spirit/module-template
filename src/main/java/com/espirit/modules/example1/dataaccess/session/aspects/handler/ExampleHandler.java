package com.espirit.modules.example1.dataaccess.session.aspects.handler;

import com.espirit.modules.example1.ExamplePOJO;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.transfer.HandlerHost;

import java.util.List;

public class ExampleHandler implements HandlerHost.Handler<ExamplePOJO, ExamplePOJO> {

    public ExampleHandler() {

    }

    @Override
    public List<ExamplePOJO> handle(List<ExamplePOJO> exampleList) {
        return exampleList;
    }
}
