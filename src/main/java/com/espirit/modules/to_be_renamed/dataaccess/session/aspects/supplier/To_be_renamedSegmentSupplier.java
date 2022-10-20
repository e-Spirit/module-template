package com.espirit.modules.to_be_renamed.dataaccess.session.aspects.supplier;

import com.espirit.modules.to_be_renamed.To_be_renamedPOJO;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.transfer.SupplierHost;
import de.espirit.firstspirit.client.search.SegmentProvider;

import java.util.Collections;
import java.util.List;

public class To_be_renamedSegmentSupplier implements SupplierHost.Supplier<To_be_renamedPOJO, SegmentProvider> {

    @Override
    public List<SegmentProvider> supply(To_be_renamedPOJO object) {
        return Collections.singletonList(new To_be_renamedQuerySegmentProvider(object.getVar()));
    }
}
