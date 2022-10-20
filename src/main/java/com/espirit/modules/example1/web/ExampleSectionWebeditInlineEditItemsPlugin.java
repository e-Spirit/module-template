package com.espirit.modules.example1.web;

import com.espirit.moddev.components.annotations.PublicComponent;
import de.espirit.firstspirit.access.BaseContext;
import de.espirit.firstspirit.webedit.plugin.WebeditInlineEditItemsPlugin;
import de.espirit.firstspirit.webedit.plugin.inlineedit.ExecutableInlineEditItem;
import de.espirit.firstspirit.webedit.plugin.inlineedit.InlineEditContext;
import de.espirit.firstspirit.webedit.plugin.inlineedit.InlineEditItem;

import java.util.Arrays;
import java.util.Collection;

@PublicComponent(name = "DevconCloudinary1SectionnWebeditInlineEditItemsPlugin")
public class ExampleSectionWebeditInlineEditItemsPlugin implements WebeditInlineEditItemsPlugin {

    private BaseContext _context;

    private static final Class<?> LOGGER = ExampleSectionWebeditInlineEditItemsPlugin.class;

    @Override
    public Collection<? extends InlineEditItem> getItems() {
        return Arrays.asList(
                new ExampleExecutableInlineEditItem()
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
