package com.espirit.modules.to_be_renamed.dataaccess.session.aspects;

import com.espirit.modules.to_be_renamed.To_be_renamedPOJO;
import com.espirit.modules.to_be_renamed.dataaccess.session.aspects.supplier.To_be_renamedSegmentSupplier;
import com.espirit.modules.to_be_renamed.dataaccess.session.aspects.supplier.To_be_renamedSupplier;
import com.espirit.modules.to_be_renamed.dataaccess.session.aspects.supplier.To_be_renamedTextSupplier;
import de.espirit.firstspirit.access.BaseContext;
import de.espirit.firstspirit.agency.TransferAgent;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.transfer.SupplierHost;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.transfer.TransferSupplying;

/**
 * Basic class to internally handle data transfers, i.e. drag and drop, etc.
 * Please see FirstSpirit API for more information.
 */
public class To_be_renamedTransferSupplyingAspect implements TransferSupplying<To_be_renamedPOJO> {

    private final BaseContext _context;

    public To_be_renamedTransferSupplyingAspect(BaseContext context) {
        _context = context;
    }

    @Override
    public void registerSuppliers(SupplierHost<To_be_renamedPOJO> host) {
        TransferAgent transferAgent = _context.requireSpecialist(TransferAgent.TYPE);
        host.registerSupplier(transferAgent.getRawValueType(To_be_renamedPOJO.class), new To_be_renamedSupplier());
        host.registerSupplier(transferAgent.getPlainTextType(), new To_be_renamedTextSupplier());
        host.registerSupplier(transferAgent.getQuerySegmentType(), new To_be_renamedSegmentSupplier());
    }
}
