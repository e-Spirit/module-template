package com.espirit.modules.to_be_renamed.dataaccess.session.aspects;

import com.espirit.modules.to_be_renamed.To_be_renamedPOJO;
import com.espirit.modules.to_be_renamed.dataaccess.session.aspects.handler.To_be_renamedHandler;
import de.espirit.firstspirit.access.BaseContext;
import de.espirit.firstspirit.agency.TransferAgent;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.transfer.HandlerHost;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.transfer.TransferHandling;

/**
 * Basic class to internally handle data transfers, i.e. drag and drop, etc.
 * Please see FirstSpirit API for more information.
 */
public class To_be_renamedTransferHandlingAspect implements TransferHandling<To_be_renamedPOJO> {

    private final BaseContext _context;

    public To_be_renamedTransferHandlingAspect(BaseContext context) {
        _context = context;
    }

    @Override
    public void registerHandlers(HandlerHost<To_be_renamedPOJO> host) {
        TransferAgent transferAgent = _context.requireSpecialist(TransferAgent.TYPE);
        host.registerHandler(transferAgent.getRawValueType(To_be_renamedPOJO.class), new To_be_renamedHandler());
    }
}
