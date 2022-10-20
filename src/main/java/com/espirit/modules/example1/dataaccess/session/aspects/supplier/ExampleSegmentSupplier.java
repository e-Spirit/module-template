package com.espirit.modules.example1.dataaccess.session.aspects.supplier;

import com.espirit.modules.example1.ExamplePOJO;
import com.espirit.modules.example1.dataaccess.session.aspects.supplier.ExampleQuerySegmentProvider;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.transfer.SupplierHost;
import de.espirit.firstspirit.client.search.SegmentProvider;

import java.util.Collections;
import java.util.List;

public class ExampleSegmentSupplier implements SupplierHost.Supplier<ExamplePOJO, SegmentProvider> {

    @Override
    public List<SegmentProvider> supply(ExamplePOJO example) {
        return Collections.singletonList(new ExampleQuerySegmentProvider(example.getVar()));
    }
}
