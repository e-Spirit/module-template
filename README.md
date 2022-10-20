# FirstSpirit Module Template
 
Generic template of a FirstSpirit module. Fill the skeleton and write a proper readme documentation to get started.

### Setting up work environment
As a developer you need to set up your environment. This has only to be done once since the
configuration is shared for all repositories. Therefore you will have to set artifactory credentials.

#### Setting artifactory credentials to access Module
Dependencies specified in your module will be downloaded from our artifactory which acts as
a Maven repository. For this to work you need to specify the credentials in your personal, not
module-local gradle.properties (see specification of [Gradle Properties](https://docs.gradle.org/current/userguide/build_environment.html#sec:gradle_configuration_properties)).
The file is located in `$HOME/.gradle/gradle.properties` and should contain at least these lines:
```
artifactory_hosting_username=CLOUD_USERNAME
artifactory_hosting_password=CLOUD_ENCRYPTED_PASSWORD
```
`CLOUD_USERNAME` is usually your complete e-mail address. The encrypted password can be
retrieved through a simple [artifactory REST call](https://artifactory.e-spirit.hosting/artifactory/api/security/encryptedPassword).
(If prompted for a username and password, please use your cloud credentials.)

## Topic #1
- Just a sample bullet point
- And another one...
    - Some more
    - This is how you can write your module's readme.md

## Topic #2

### SubTopic #1
1. Do something
2. Do some **more**