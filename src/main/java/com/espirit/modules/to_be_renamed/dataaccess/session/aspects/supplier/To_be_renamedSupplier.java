package com.espirit.modules.to_be_renamed.dataaccess.session.aspects.supplier;

import com.espirit.modules.to_be_renamed.To_be_renamedPOJO;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.transfer.SupplierHost;

import java.util.Collections;
import java.util.List;

public class To_be_renamedSupplier implements SupplierHost.Supplier<To_be_renamedPOJO, To_be_renamedPOJO> {

    @Override
    public List<To_be_renamedPOJO> supply(To_be_renamedPOJO object) {
        return Collections.singletonList(object);
    }
}
