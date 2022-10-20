package com.espirit.modules.example1.dataaccess.session.aspects;

import com.espirit.modules.example1.ExamplePOJO;
import com.espirit.modules.example1.dataaccess.session.aspects.handler.ExampleHandler;
import de.espirit.firstspirit.access.BaseContext;
import de.espirit.firstspirit.agency.TransferAgent;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.transfer.HandlerHost;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.transfer.TransferHandling;

import java.util.List;

/**
 * Basic class to internally handle data transfers, i.e. drag and drop, etc.
 * Please see FirstSpirit API for more information.
 */
public class ExampleTransferHandlingAspect implements TransferHandling<ExamplePOJO> {

    private final BaseContext _context;

    public ExampleTransferHandlingAspect(BaseContext context) {
        _context = context;
    }

    @Override
    public void registerHandlers(HandlerHost<ExamplePOJO> host) {
        TransferAgent transferAgent = _context.requireSpecialist(TransferAgent.TYPE);
        host.registerHandler(transferAgent.getRawValueType(ExamplePOJO.class), new ExampleHandler());
    }
}
