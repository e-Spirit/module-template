package com.espirit.modules.to_be_renamed.dataaccess.plugin.aspects;

import com.espirit.modules.to_be_renamed.To_be_renamedPOJO;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.ReportItemsProviding;
import de.espirit.firstspirit.client.plugin.report.ReportItem;

import java.util.Collection;
import java.util.Collections;

public class To_be_renamedReportItemsProvidingAspect implements ReportItemsProviding<To_be_renamedPOJO> {

    private final To_be_renamedPreviewItem _clickItem;

    public To_be_renamedReportItemsProvidingAspect() {
        _clickItem = new To_be_renamedPreviewItem();
    }

    @Override
    public ReportItem<To_be_renamedPOJO> getClickItem() {
        return _clickItem;
    }

    @Override
    public Collection<? extends ReportItem<To_be_renamedPOJO>> getItems() {
        return Collections.emptyList();
    }

}
