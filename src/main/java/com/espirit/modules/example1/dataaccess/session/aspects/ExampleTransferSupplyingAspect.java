package com.espirit.modules.example1.dataaccess.session.aspects;

import com.espirit.modules.example1.ExamplePOJO;
import com.espirit.modules.example1.dataaccess.session.aspects.supplier.ExampleSegmentSupplier;
import com.espirit.modules.example1.dataaccess.session.aspects.supplier.ExampleSupplier;
import com.espirit.modules.example1.dataaccess.session.aspects.supplier.ExampleTextSupplier;
import de.espirit.firstspirit.access.BaseContext;
import de.espirit.firstspirit.agency.TransferAgent;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.transfer.SupplierHost;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.transfer.TransferSupplying;

/**
 * Basic class to internally handle data transfers, i.e. drag and drop, etc.
 * Please see FirstSpirit API for more information.
 */
public class ExampleTransferSupplyingAspect implements TransferSupplying<ExamplePOJO> {

    private final BaseContext _context;

    public ExampleTransferSupplyingAspect(BaseContext context) {
        _context = context;
    }

    @Override
    public void registerSuppliers(SupplierHost<ExamplePOJO> host) {
        TransferAgent transferAgent = _context.requireSpecialist(TransferAgent.TYPE);
        host.registerSupplier(transferAgent.getRawValueType(ExamplePOJO.class), new ExampleSupplier());
        host.registerSupplier(transferAgent.getPlainTextType(), new ExampleTextSupplier());
        host.registerSupplier(transferAgent.getQuerySegmentType(), new ExampleSegmentSupplier());
    }
}
