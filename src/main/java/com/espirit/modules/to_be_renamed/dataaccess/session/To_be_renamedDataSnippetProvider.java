package com.espirit.modules.to_be_renamed.dataaccess.session;

import com.espirit.modules.to_be_renamed.To_be_renamedPOJO;
import com.espirit.modules.to_be_renamed.util.To_be_renamedIcons;
import de.espirit.firstspirit.access.BaseContext;
import de.espirit.firstspirit.access.Language;
import de.espirit.firstspirit.agency.Image;
import de.espirit.firstspirit.client.plugin.dataaccess.DataSnippetProvider;

/**
 * Basic class to implement report snippet representations.
 * Please see FirstSpirit API for more information.
 */
public class To_be_renamedDataSnippetProvider implements DataSnippetProvider<To_be_renamedPOJO> {

    private final BaseContext _context;
    private final Image<?> _icon;

    public To_be_renamedDataSnippetProvider(BaseContext context) {
        _context = context;

        if (context.is(BaseContext.Env.WEBEDIT)) {
            _icon = null;
        } else {
            _icon = To_be_renamedIcons.getSnippet(context);
        }
    }

    @Override
    public Image<?> getIcon(To_be_renamedPOJO object) {

        //return the report icon

        return _icon;
    }

    @Override
    public Image<?> getThumbnail(To_be_renamedPOJO object, Language language) {

        //returns the thumbnail image for a single report item

        return null;
    }

    @Override
    public String getHeader(To_be_renamedPOJO object, Language language) {

        //returns the header text for a single report item

        return object.getVar();
    }

    @Override
    public String getExtract(To_be_renamedPOJO object, Language language) {

        //returns the extract text for a single report item

        return object.getVar();
    }
}
