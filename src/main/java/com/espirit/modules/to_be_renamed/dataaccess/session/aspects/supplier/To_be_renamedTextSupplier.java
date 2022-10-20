package com.espirit.modules.to_be_renamed.dataaccess.session.aspects.supplier;

import com.espirit.modules.to_be_renamed.To_be_renamedPOJO;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.transfer.SupplierHost;

import java.util.Collections;
import java.util.List;

public class To_be_renamedTextSupplier implements SupplierHost.Supplier<To_be_renamedPOJO, String> {

    @Override
    public List<String> supply(To_be_renamedPOJO object ) {
        return Collections.singletonList(object.getVar());
    }
}
