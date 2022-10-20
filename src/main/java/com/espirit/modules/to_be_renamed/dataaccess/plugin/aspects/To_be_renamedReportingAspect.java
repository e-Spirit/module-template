package com.espirit.modules.to_be_renamed.dataaccess.plugin.aspects;

import com.espirit.modules.to_be_renamed.util.To_be_renamedIcons;
import de.espirit.firstspirit.access.BaseContext;
import de.espirit.firstspirit.agency.Image;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.Reporting;

/**
 * Basic class to set up a Content Creator Report. Please see FirstSpirit API for more information.
 */
public class To_be_renamedReportingAspect implements Reporting {

    private final BaseContext _context;

    public To_be_renamedReportingAspect(BaseContext context) {
        _context = context;
    }

    @Override
    public Image<?> getReportIcon(boolean active) {

        if (_context.is(BaseContext.Env.WEBEDIT)) {
            return active ? To_be_renamedIcons.getActive(_context) : To_be_renamedIcons.getInactive(_context);
        } else {
            return active ? To_be_renamedIcons.getInactive(_context) : To_be_renamedIcons.getActive(_context);
        }
    }
}
