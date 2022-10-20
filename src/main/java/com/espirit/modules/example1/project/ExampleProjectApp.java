package com.espirit.modules.example1.project;

import com.espirit.moddev.components.annotations.ProjectAppComponent;
import de.espirit.firstspirit.module.ProjectApp;
import de.espirit.firstspirit.module.ProjectEnvironment;
import de.espirit.firstspirit.module.descriptor.ProjectAppDescriptor;

@ProjectAppComponent(name = "Devcon Cloudinary 1 Project App", configurable = ExampleProjectConfig.class)
public class ExampleProjectApp implements ProjectApp {

    // several hooks defined by the ProjectApp interface

    @Override
    public void init(ProjectAppDescriptor projectAppDescriptor, ProjectEnvironment projectEnvironment) {
        // Do something when the project app is loaded
    }

    @Override
    public void installed() {
        // Do something when installing the project app
    }

    @Override
    public void uninstalling() {
        // Do something when uninstalling the project app
    }

    @Override
    public void updated(String s) {
        // Do something when updating the project app
    }
}
