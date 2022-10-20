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

    private static class ExampleExecutableInlineEditItem implements ExecutableInlineEditItem {

        // a button that shows up inside the inline edit action menu of an element inside the ContentCreator
        // can be used to trigger something

        ExampleExecutableInlineEditItem() {}

        public String getLabel(final InlineEditContext context) {
            return "Example";
        }

        public String getIconPath(final InlineEditContext context) {
            return "icons/cp-flag.png";
        }

        public boolean isVisible(final InlineEditContext context) {

            // some logic to decide if the button should be shown can be implemented here
            // e.g. check if the current element is a section of some kind

            return true;
        }

        public boolean isEnabled(final InlineEditContext context) {

            // some logic to decide if the button should be enabled can be implemented here

            return true;
        }

        public void execute(final InlineEditContext context) {

            // do something

        }

    }

}
