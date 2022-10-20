package com.espirit.modules.to_be_renamed.web;

import com.espirit.modules.to_be_renamed.util.ResourceHandler;
import de.espirit.firstspirit.access.Language;
import de.espirit.firstspirit.agency.LanguageAgent;
import de.espirit.firstspirit.client.plugin.toolbar.ToolbarContext;
import de.espirit.firstspirit.webedit.WebeditUiAgent;
import de.espirit.firstspirit.webedit.plugin.toolbar.ExecutableToolbarActionsItem;

public class To_be_renamedExecutableToolbarActionsItem implements ExecutableToolbarActionsItem {

    private final String BUNDLE_NAME = "to_be_renamed";
    private final String ICON_PATH = "to_be_renamed/cp-flag.png";

    // a button that shows up inside the toolbar action menu of the ContentCreator
    // can be used to trigger something

    @Override
    public String getLabel(ToolbarContext toolbarContext) {

        //get a multi language label
        //you can even just return a simple string

        final LanguageAgent languageAgent = toolbarContext.requireSpecialist(LanguageAgent.TYPE);
        final WebeditUiAgent webEditUiAgent = toolbarContext.requestSpecialist(WebeditUiAgent.TYPE);
        final Language masterLanguage = languageAgent.getMasterLanguage();
        Language displayLanguage = masterLanguage;
        if (webEditUiAgent != null) {
            displayLanguage = webEditUiAgent.getDisplayLanguage();
        }
        ResourceHandler resourceHandler = ResourceHandler.load(BUNDLE_NAME, displayLanguage.getLocale());

        String returnValue = resourceHandler.getString("toolbar.to_be_renamed.action");


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