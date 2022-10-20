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
                new ExampleWebeditToolbarActionsItemsPlugin.ExampleExecutableToolbarActionsItem()
        );
    }

    @Override
    public void setUp(BaseContext baseContext) {
        _context = baseContext;
    }

    @Override
    public void tearDown() {

    }

    private static class ExampleExecutableToolbarActionsItem implements ExecutableToolbarActionsItem {

        private final String BUNDLE_NAME = "example";
        private final String ICON_PATH = "example/cp-flag.png";

        // a button that shows up inside the toolbar action menu of the ContentCreator
        // can be used to trigger something

        @Override
        public String getLabel(ToolbarContext toolbarContext) {

            //just an example to get a multi language label
            //you can even just return a simple string

            final LanguageAgent languageAgent = toolbarContext.requireSpecialist(LanguageAgent.TYPE);
            final WebeditUiAgent webEditUiAgent = toolbarContext.requestSpecialist(WebeditUiAgent.TYPE);
            final Language masterLanguage = languageAgent.getMasterLanguage();
            Language displayLanguage = masterLanguage;
            if (webEditUiAgent != null) {
                displayLanguage = webEditUiAgent.getDisplayLanguage();
            }
            ResourceHandler resourceHandler = ResourceHandler.load(BUNDLE_NAME, displayLanguage.getLocale());

            String returnValue = resourceHandler.getString("toolbar.example.action");


            return returnValue;
        }

        @Override
        public String getIconPath(ToolbarContext toolbarContext) {
            return ICON_PATH;
        }

        @Override
        public boolean isVisible(ToolbarContext toolbarContext) {

            // some logic to decide if the button should be shown can be implemented here
            // e.g. check if the current element is a page of some kind

            return true;
        }

        @Override
        public boolean isEnabled(ToolbarContext toolbarContext) {

            // some logic to decide if the button should be enabled can be implemented here

            return true;
        }

        @Override
        public void execute(ToolbarContext toolbarContext) {

            // do something

        }
    }
}
