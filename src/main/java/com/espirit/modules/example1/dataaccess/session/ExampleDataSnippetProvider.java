package com.espirit.modules.example1.dataaccess.session;

import com.espirit.modules.example1.ExamplePOJO;
import com.espirit.modules.example1.util.ExampleIcons;
import de.espirit.firstspirit.access.BaseContext;
import de.espirit.firstspirit.access.Language;
import de.espirit.firstspirit.agency.Image;
import de.espirit.firstspirit.client.plugin.dataaccess.DataSnippetProvider;

/**
 * Basic class to implement report snippet representations.
 * Please see FirstSpirit API for more information.
 */
public class ExampleDataSnippetProvider implements DataSnippetProvider<ExamplePOJO> {

    private final BaseContext _context;
    private final Image<?> _icon;

    public ExampleDataSnippetProvider(BaseContext context) {
        _context = context;

        if (context.is(BaseContext.Env.WEBEDIT)) {
            _icon = null;
        } else {
            _icon = ExampleIcons.getPosting(context);
        }
    }

    @Override
    public Image<?> getIcon(ExamplePOJO posting) {

        //return the report icon

        return _icon;
    }

    @Override
    public Image<?> getThumbnail(ExamplePOJO example, Language language) {

        //returns the thumbnail image for a single report item

        return null;
    }

    @Override
    public String getHeader(ExamplePOJO example, Language language) {

        //returns the header text for a single report item

        return example.getVar();
    }

    @Override
    public String getExtract(ExamplePOJO example, Language language) {

        //returns the extract text for a single report item

        return example.getVar();
    }
}
