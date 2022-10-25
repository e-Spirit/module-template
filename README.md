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

-**version** The version identifier of your module

-**firstSpiritModule.moduleName** The name of your module containing no blank spaces. Should start with a capital
letter as it is also used to rename classfiles, etc.

-**firstSpiritModule.displayName** The name of your module to be displayed within the server configuration
-**firstSpiritModule.description** The description of your module to be displayed within the server configuration
-**firstSpiritModule.vendor** The vendor of you module, i.e. your company
-**groupId** The group ID to identify your module if uploaded to an artifactory
-**publishing.releaseRepository** The repository to upload the release version of your module to
-**publishing.snapshotRepository** The repository to upload the snapshot / development version of your module to
-**rootProjectName** The Gradle project name

### Initialize Module Skeleton

After setting up the project's gradle.properties one can execute the *initModule* Gradle task by simply executing
`gradle initModule` or running the task from your IDE. This task will take the name specified as 
*firstSpiritModule.moduleName* (see above) and rename all classes, variables and packages according to your needs. If
you do not wish to automatically refactor your project you can also replace all occurences of *to_be_renamed* and
*To_be_renamed* (note case sensivity!) in filenames, packages and within files manually.



<!-- EXAMPLE MARKDOWN 

## Topic #1
- Just a sample bullet point
- And another one...
    - Some more
    - This is how you can write your module's readme.md

## Topic #2

### SubTopic #1
1. Do something
2. Do some **more**
-->