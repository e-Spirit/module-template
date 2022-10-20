package com.espirit.modules.to_be_renamed.web;

import com.espirit.moddev.components.annotations.PublicComponent;
import de.espirit.firstspirit.access.BaseContext;
import de.espirit.firstspirit.webedit.plugin.WebeditInlineEditItemsPlugin;
import de.espirit.firstspirit.webedit.plugin.inlineedit.InlineEditItem;

import java.util.Arrays;
import java.util.Collection;

@PublicComponent(name = "DevconCloudinary1SectionnWebeditInlineEditItemsPlugin")
public class To_be_renamedSectionWebeditInlineEditItemsPlugin implements WebeditInlineEditItemsPlugin {

    private BaseContext _context;

    private static final Class<?> LOGGER = To_be_renamedSectionWebeditInlineEditItemsPlugin.class;

    @Override
    public Collection<? extends InlineEditItem> getItems() {
        return Arrays.asList(
                new To_be_renamedExecutableInlineEditItem()
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
