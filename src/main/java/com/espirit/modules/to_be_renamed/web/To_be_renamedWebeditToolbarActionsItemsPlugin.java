package com.espirit.modules.to_be_renamed.web;

import com.espirit.moddev.components.annotations.PublicComponent;
import de.espirit.firstspirit.access.BaseContext;
import de.espirit.firstspirit.webedit.plugin.WebeditToolbarActionsItemsPlugin;
import de.espirit.firstspirit.webedit.plugin.toolbar.WebeditToolbarItem;

import java.util.Arrays;
import java.util.Collection;

@PublicComponent(name = "DevconCloudinary1WebeditToolbarActionsItemsPlugin")
public class To_be_renamedWebeditToolbarActionsItemsPlugin implements WebeditToolbarActionsItemsPlugin {

    private BaseContext _context;
    private static final Class<?> LOGGER = To_be_renamedWebeditToolbarActionsItemsPlugin.class;

    @Override
    public Collection<? extends WebeditToolbarItem> getItems() {
        return Arrays.asList(
                new To_be_renamedExecutableToolbarActionsItem()
        );
    }

    @Override
    public void setUp(BaseContext baseContext) {
        _context = baseContext;
    }

    @Override
    public void tearDown() {

    }
}
