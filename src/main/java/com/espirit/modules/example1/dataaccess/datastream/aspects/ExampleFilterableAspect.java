package com.espirit.modules.example1.dataaccess.datastream.aspects;

import de.espirit.common.tools.Strings;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.Filterable;
import de.espirit.firstspirit.client.plugin.report.Parameter;
import de.espirit.firstspirit.client.plugin.report.ParameterMap;
import de.espirit.firstspirit.client.plugin.report.ParameterSelect;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Aspect to provide filters for a data stream.
 * Please see FirstSpirit API for more information.
 */
public class ExampleFilterableAspect implements Filterable {

    private ParameterSelect someFilterVariable = null;

    private ParameterMap filter = null;

    public ExampleFilterableAspect(List<ParameterSelect.SelectItem> selectItems) {
        if (selectItems != null && !selectItems.isEmpty()) {
            this.someFilterVariable = Parameter.Factory.createSelect("someFilterVariableFilterSelect", selectItems, "all");
        }
    }

    @Override
    public List<Parameter<?>> getDefinedParameters() {
        List<Parameter<?>> pList = new ArrayList<>();
        if (this.someFilterVariable != null) {
            pList.add(this.someFilterVariable);
        }

        return pList;
    }

    @Override
    public void setFilter(ParameterMap filter) {
        this.filter = filter;
    }

    /**
     * Gets the selected filter variable.
     *
     * @return the filter variable
     */
    @Nullable
    public String getSomeData() {
        String someFilterVariable = null;
        if (this.someFilterVariable != null) {
            someFilterVariable = this.filter.get(this.someFilterVariable);
        }
        return !Strings.isEmpty(someFilterVariable) ? someFilterVariable : null;
    }
}
