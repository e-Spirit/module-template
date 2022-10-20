package com.espirit.modules.to_be_renamed.dataaccess.session.aspects.handler;

import com.espirit.modules.to_be_renamed.To_be_renamedPOJO;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.transfer.HandlerHost;

import java.util.List;

public class To_be_renamedHandler implements HandlerHost.Handler<To_be_renamedPOJO, To_be_renamedPOJO> {

    public To_be_renamedHandler() {

    }

    @Override
    public List<To_be_renamedPOJO> handle(List<To_be_renamedPOJO> objectList) {
        return objectList;
    }
}
