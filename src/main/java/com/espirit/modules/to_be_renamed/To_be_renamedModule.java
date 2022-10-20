package com.espirit.modules.to_be_renamed;

import com.espirit.moddev.components.annotations.ModuleComponent;
import de.espirit.firstspirit.module.Module;
import de.espirit.firstspirit.module.ServerEnvironment;
import de.espirit.firstspirit.module.descriptor.ModuleDescriptor;

/**
 * The FirstSpirit integration module.
 */
@ModuleComponent()
public class To_be_renamedModule implements Module {

    // several hooks defined by the Module interface

    @Override
    public void init(ModuleDescriptor moduleDescriptor, ServerEnvironment serverEnvironment) {
        // Do something when the module is loaded
    }

    @Override
    public void installed() {
        // Do something when installing the module
    }

    @Override
    public void uninstalling() {
        // Do something when uninstalling the module
    }

    @Override
    public void updated(String s) {
        // Do something when updating the module
    }
}