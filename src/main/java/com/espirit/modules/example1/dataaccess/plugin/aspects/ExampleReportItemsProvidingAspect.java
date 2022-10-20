package com.espirit.modules.example1.dataaccess.plugin.aspects;

import com.espirit.modules.example1.ExamplePOJO;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.ReportItemsProviding;
import de.espirit.firstspirit.client.plugin.report.ReportItem;

import java.util.Collection;
import java.util.Collections;

public class ExampleReportItemsProvidingAspect implements ReportItemsProviding<ExamplePOJO> {

    private final ExamplePreviewItem _clickItem;

    public ExampleReportItemsProvidingAspect() {
        _clickItem = new ExamplePreviewItem();
    }

    @Override
    public ReportItem<ExamplePOJO> getClickItem() {
        return _clickItem;
    }

    @Override
    public Collection<? extends ReportItem<ExamplePOJO>> getItems() {
        return Collections.emptyList();
    }

}
