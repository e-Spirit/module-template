package com.espirit.modules.example1.web;

import com.espirit.moddev.components.annotations.WebAppComponent;
import com.espirit.moddev.components.annotations.WebResource;
import de.espirit.firstspirit.module.WebApp;
import de.espirit.firstspirit.module.WebEnvironment;
import de.espirit.firstspirit.module.descriptor.WebAppDescriptor;

@WebAppComponent(name = "devcon-cloudinary-1-web-app",
        displayName = "Devcon Cloudinary 1 Web App",
        webXml = "web/web.xml",
        webResources = {
                @WebResource(path = "example/", name = "devcon-cloudinary-1-web-resources", version = "1.0.0", targetPath = "example/")
        }
)
public class ExampleWebApp implements WebApp {

        @Override
        public void createWar() {
                // Nothing needs to be done here
        }

        @Override
        public void init(WebAppDescriptor webAppDescriptor, WebEnvironment webEnvironment) {
                // Nothing needs to be done here
        }

        @Override
        public void installed() {
                // Nothing needs to be done here
        }

        @Override
        public void uninstalling() {
                // Nothing needs to be done here
        }

        @Override
        public void updated(String s) {
                // Nothing needs to be done here
        }

}
