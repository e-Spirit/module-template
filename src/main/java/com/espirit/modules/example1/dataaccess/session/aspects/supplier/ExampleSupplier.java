package com.espirit.modules.example1.dataaccess.session.aspects.supplier;

import com.espirit.modules.example1.ExamplePOJO;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.transfer.SupplierHost;

import java.util.Collections;
import java.util.List;

public class ExampleSupplier implements SupplierHost.Supplier<ExamplePOJO, ExamplePOJO> {

    @Override
    public List<ExamplePOJO> supply(ExamplePOJO example) {
        return Collections.singletonList(example);
    }
}
