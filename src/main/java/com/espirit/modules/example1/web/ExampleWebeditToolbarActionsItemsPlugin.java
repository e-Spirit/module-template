package com.espirit.modules.example1.web;

import com.espirit.moddev.components.annotations.PublicComponent;
import com.espirit.modules.example1.util.ResourceHandler;
import de.espirit.firstspirit.access.BaseContext;
import de.espirit.firstspirit.access.Language;
import de.espirit.firstspirit.agency.LanguageAgent;
import de.espirit.firstspirit.client.plugin.toolbar.ToolbarContext;
import de.espirit.firstspirit.webedit.WebeditUiAgent;
import de.espirit.firstspirit.webedit.plugin.WebeditToolbarActionsItemsPlugin;
import de.espirit.firstspirit.webedit.plugin.toolbar.ExecutableToolbarActionsItem;
import de.espirit.firstspirit.webedit.plugin.toolbar.WebeditToolbarItem;

import java.util.Arrays;
import java.util.Collection;

@PublicComponent(name = "DevconCloudinary1WebeditToolbarActionsItemsPlugin")
public class ExampleWebeditToolbarActionsItemsPlugin implements WebeditToolbarActionsItemsPlugin {

    private BaseContext _context;
    private static final Class<?> LOGGER = ExampleWebeditToolbarActionsItemsPlugin.class;

    @Override
    public Collection<? extends WebeditToolbarItem> getItems() {
        return Arrays.asList(
                new ExampleExecutableToolbarActionsItem()
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
