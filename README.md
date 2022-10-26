# FirstSpirit Module Template
 
Generic template of a FirstSpirit module. Fill the skeleton and write a proper readme documentation to get started.

## Preparation

Before you can start to develop your own module please make sure to setup a few things.

### Setting up work environment
As a developer you need to set up your environment. This has only to be done once since the
configuration is shared for all repositories. Therefore you will have to set artifactory credentials.

#### Setting artifactory credentials to access Module
Dependencies specified in your module will be downloaded from our artifactory which acts as
a Maven repository. For this to work you need to specify the credentials in your personal, not
module-local gradle.properties (see specification of
[Gradle Properties](https://docs.gradle.org/current/userguide/build_environment.html#sec:gradle_configuration_properties)).
The file is located in `$HOME/.gradle/gradle.properties` and should contain at least these lines:
```
artifactory_hosting_username=CLOUD_USERNAME
artifactory_hosting_password=CLOUD_ENCRYPTED_PASSWORD
```
`CLOUD_USERNAME` is usually your complete e-mail address. The encrypted password can be
retrieved through a simple
[artifactory REST call](https://artifactory.e-spirit.hosting/artifactory/api/security/encryptedPassword).
(If prompted for a username and password, please use your cloud credentials.)

### Configure Gradle Properties

Within the gradle.properties file of this project you have to setup the following variables:

- **version** 
  - The version identifier of your module, e.g. `version=0.0.1-SNAPSHOT`
- **firstSpiritModule.moduleName** 
  - The name of your module containing no blank spaces. Should start with a capital
  letter as it is also used to rename classfiles, etc., e.g. `firstSpiritModule.moduleName=ModuleSkeleton`
- **firstSpiritModule.displayName** 
  - The name of your module to be displayed within the server configuration, e.g. `firstSpiritModule.displayName=Module Skeleton`
- **firstSpiritModule.description**
  - The description of your module to be displayed within the server configuration, e.g. `firstSpiritModule.description=An example description.`
- **firstSpiritModule.vendor**
  - The vendor of you module, i.e. your company, e.g. `firstSpiritModule.vendor=Crownpeak`
- **groupId**
  - The group ID to identify your module if uploaded to an artifactory, e.g. `groupId=com.espirit.modules.mymodule`
- **publishing.releaseRepository**
  - The repository to upload the release version of your module to, e.g. `publishing.releaseRepository=my-department-release`
- **publishing.snapshotRepository**
  - The repository to upload the snapshot / development version of your module to, e.g. `publishing.snapshotRepository=my-department-snapshot`
- **rootProjectName**
  - The Gradle project name, e.g. `rootProjectName=Module Skeleton`
  ```
  #  ----------------------------------
  # | FirstSpirit Module configuration |
  #  ----------------------------------
  #
  # ENTER YOUR MODULE VERSION
  version=
  #
  # ENTER THE NAME OF YOUR MODULE
  firstSpiritModule.moduleName=
  #
  # ENTER DISPLAY NAME OF YOUR MODULE
  firstSpiritModule.displayName=
  #
  # ENTER DESCRIPTION OF YOUR MODULE
  firstSpiritModule.description=
  #
  #CHANGE VENDOR OF YOUR MODULE
  firstSpiritModule.vendor=
  #
  # ENTER YOUR GROUP ID
  groupId=
  #
  # ENTER PUBLISHING CONFIGURATION
  publishing.releaseRepository=
  publishing.snapshotRepository=
  #
  #
  # 
  #  ------------------------------
  # | Gradle Project configuration |
  #  ------------------------------
  #
  # ENTER NAME OF YOUR GRADLE PROJECT HERE
  rootProjectName=
  ```

### Initialize Module Skeleton

After setting up the project's gradle.properties one can execute the *initModule* Gradle task by simply executing
`gradle initModule` or running the task from your IDE. This task will take the name specified as 
*firstSpiritModule.moduleName* (see above) and rename all classes, variables and packages according to your needs. If
you do not wish to automatically refactor your project you can also replace all occurences of *to_be_renamed* and
*To_be_renamed* (note case sensivity!) in filenames, packages and within files manually.

### Debug SiteArchitect
In some cases it is helpful to be able to debug your own implementation in SiteArchitect. With the gradle task 
*startSiteArchitect* it is possible to start a SiteArchitect from the IDE.
###### Configuration
- Module must be installed on server
- Add the following properties to the local gradle.properties in your home directory `$HOME/.gradle/gradle.properties`
  - **firstSpirit.debug.host**
    - The host to connect to, e.g. `firstSpirit.debug.host=smart-living.e-spirit.hosting`
  - **firstSpirit.debug.projectId**
    - The project to be opened, e.g. `firstSpirit.debug.projectId=424242`
    ```
    # FIRSTSPIRIT DEBUG PROPERTIES
    # ENTER THE HOST TO CONNECT TO HEAR (e.g. smartliving.e-spirit.hosting)
    firstSpirit.debug.host=
    # ENTER THE PROJECT ID TO CONNECT TO HEAR
    firstSpirit.debug.projectId=
    ```
###### Features
- Uses locally compiled classes (may differ from those in installed module!)
- Allows testing client plugins without repeated module installation
- Possibility to set breakpoints in code