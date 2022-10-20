package com.espirit.modules.example1.dataaccess.plugin.aspects;

import com.espirit.modules.example1.util.ExampleIcons;
import de.espirit.firstspirit.access.BaseContext;
import de.espirit.firstspirit.agency.Image;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.Reporting;

/**
 * Basic class to set up a Content Creator Report. Please see FirstSpirit API for more information.
 */
public class ExampleReportingAspect implements Reporting {

    private final BaseContext _context;

    public ExampleReportingAspect(BaseContext context) {
        _context = context;
    }

    @Override
    public Image<?> getReportIcon(boolean active) {

        if (_context.is(BaseContext.Env.WEBEDIT)) {
            return active ? ExampleIcons.getActive(_context) : ExampleIcons.getInactive(_context);
        } else {
            return active ? ExampleIcons.getInactive(_context) : ExampleIcons.getActive(_context);
        }
    }
}
