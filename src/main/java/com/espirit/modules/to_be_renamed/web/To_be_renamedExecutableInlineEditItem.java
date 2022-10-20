package com.espirit.modules.to_be_renamed.web;

import de.espirit.firstspirit.webedit.plugin.inlineedit.ExecutableInlineEditItem;
import de.espirit.firstspirit.webedit.plugin.inlineedit.InlineEditContext;

public class To_be_renamedExecutableInlineEditItem implements ExecutableInlineEditItem {

    // a button that shows up inside the inline edit action menu of an element inside the ContentCreator
    // can be used to trigger something

    To_be_renamedExecutableInlineEditItem() {}

    public String getLabel(final InlineEditContext context) {
        return "To_be_renamed";
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