# OSGifier

The objectives of the *OSGifier* project are to

*   help developers to create proper OSGi bundles.
*   provide automatable solutions to integrate non-OSGi JARs.

Checkout the following chapters for more detailed information.

*   [Create Proper OSGi Bundles](#create-proper-osgi-bundles)
*   [Deal with dependencies to non-OSGi JARs](#deal-with-dependencies-to-non-osgi-jars)
*   [Configuration Options](#configuration-options)
    *   [Adopt Bundle Symbolic Name and Version](#adopt-bundle-symbolic-name-and-version)
    *   [Determine internal Packages](#determine-internal-packages)
    *   [Adopt version ranges for package imports](#adopt-version-ranges-for-package-imports)
    *   [Require-Bundle instead of Import-Package](#require-bundle-instead-of-import-package)
    *   [Bundle-RequiredExecutionEnvironment](#bundle-requiredexecutionenvironment)
    *   [Force OSGifier to override MANIFEST.MF of native Bundles](#force-osgifier-to-override-manifestmf-of-native-bundles)
*   [OSGifier Maven Plugin](#osgifier-maven-plugin)
    *   [Plugin Goals and Usage](#plugin-goals-and-usage)
    *   [Plugin Management](#plugin-management)
*   [OSGifier Maven APIs and Components](#osgifier-maven-apis-and-components)
*   [OSGifier Core APIs and Components](#osgifier-core-apis-and-components)
*   [How to Contribute](#how-to-contribute)
*   [License](#license)

## Create Proper OSGi Bundles

From my own experience I've noticed that it is very cumbersome and error-prone to maintain the metadata for OSGi
bundles by hand. And I also realized that everything that is needed for that is already present in my projects.

*   Bundle identity: Maven project information (GAV, name, description)
*   Bundle content: Java classes
*   Bundle requirements: Maven dependencies (incl. versioning)
*   Bundle environment: Compiler settings and required Java classes

But to create **proper** OSGi metadata, you also

*   need solid knowlege about OSGi.
*   must be aware of many best practises and recommendations (scattered all over the Internet).

This is where the *OSGifier* comes into place. The *OSGifier* gathers the information of your project, applies many
best pracises, recommendations and customizations (as wished) and finally generates a `MANIFES.MF` file that contains
all the metadata required by OSGi.

This is done by the following goals of the [OSGifier Maven Plugin](#osgifier-maven-plugin).

*   [osgifier:generate-manifest](#osgifiergenerate-manifest)
*   [osgifier:inject-manifest](#osgifierinject-manifest)

## Deal with dependencies to non-OSGi JARs

In OSGi it is a significant problem to re-use functionality from third-party libraries that are not OSGi-enabled.
Existing solutions (known by me) either contradict the idea of modularization, or are only satisfactorily applicable
to simple libraries without any dependencies.

To respect both, idea of modularization and transitive dependencies, the *OSGifier* provides tooling to * fetch a set
of libraries and their dependencies from Maven repositories and to equip each with its own `MANIFES.MF`. * deploy
those *osgified* libaries to a Maven repository (using customized Maven coordinates). So you can use those as normal
Maven dependencies in your project.

This is done by the following goals of the [OSGifier Maven Plugin](#osgifier-maven-plugin).

*   [osgifier:osgify-artifacts](#osgifierosgify-artifacts)
*   [osgifier:deploy-osgified-artifacts](#osgifierdeploy-osgified-artifacts)
*   [osgifier:install-osgified-artifacts](#osgifierinstall-osgified-artifacts)

## Configuration Options

### Adopt Bundle Symbolic Name and Version

*   `forceContextQualifier`
    
    String that will be appended as qualifier to the OSGi version that is determined for a osgified project or
    library. Usually this is sth. like a date or timestamp.

*   `saveMavenQualifier`
    
    When set to `true` Maven version qualifiers will always be appended to at the end of the determined OSGi version,
    default `true`.

*   `symbolicNameMappings`
    
    Comma separated list with mappings of Maven coordinate to a specific *Symbolic Bundle Names*, e.g
    "xalan:xalan:jar=org.apache.xalan, stax:stax-api:jar=javax.xml.stream"

### Determine internal Packages

*   `internalPackages`
    
    Comma separated list with patterns to determine which Java packages should be treated as internal and not as
    public API, e.g. `**.internal.**, **.impl.**, !**.api.**`.

*   `treatInheritedPackagesAsInternal`
    
    When set to `true`, packages from classes your classes inherits from are treated as internal, default is `false`.

### Adopt version ranges for package imports

*   `eraseMicro`
    
    When set to `true`, the *micro* version numbers are considered when determining version ranges to requirements,
    default is `false`.

*   `publicImportPolicy`
    
    Policy that takes place when determining version ranges for imports of public packages, default is `compatible`.

*   `internalImportPolicy`
    
    Policy that takes place when determining version ranges for imports of internal packages, default is `equivalent`.

*   `selfImportPolicy`
    
    Policy that takes place when determining version ranges for imports of packages from the own bundle, default is
    `equivalent`.

*   `recommendedImportPolicies`
    
    Comma separated list with mappings of bundle symbolic name patterns to the import policies that should take place
    when importing packages from this bundle, e.g. `org.sourcepit.dev.**=strict|perfect` (public packages will be
    imported *strict*, internal *perfect*).

### Require-Bundle instead of Import-Package

*   `requireBundle`
    
    Comma separated list with patterns to determine which bundles should be referenced via `Require-Bundle`instead of
    `Import-Package` header.

### Bundle-RequiredExecutionEnvironment

*   `executionEnvironmentMappings`
    
    Comma separated list with mappings to set one or more *Execution Environments* for a bundle, e.g.
    `org.sourcepit.core.**=JavaSE/compact1-1.8|JavaSE-1.7,org.sourcepit.runtime.**=JavaSE/compact1-1.8`.

*   `excludedExecutionEnvironments`
    
    Comma separated list with *Execution Environments* won't be considered when determining the required execution
    environments for a bundle.

### Force OSGifier to override MANIFEST.MF of native Bundles

*   `overrideNativeBundles`
    
    `true`, `false` or a list with patterns that will be used to determine if `MANIFEST.MF` files of existing bundles
    should be overridden, default is `false`.

## OSGifier Maven Plugin

### Plugin Goals and Usage

Here you can find the documentation and usage examples for all goals provided by this plugin. A goal represents a
specific task that can be executed either during the build lifecycle of your project or by command line. See also [A
Build Phase is Made Up of Plugin Goals](http://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html#A_Build_Phase_is_Made_Up_of_Plugin_Goals).

Available goals:

*   [osgifier:deploy-osgified-artifacts](#osgifierdeploy-osgified-artifacts)
*   [osgifier:generate-manifest](#osgifiergenerate-manifest)
*   [osgifier:inject-manifest](#osgifierinject-manifest)
*   [osgifier:install-osgified-artifacts](#osgifierinstall-osgified-artifacts)
*   [osgifier:osgify-artifacts](#osgifierosgify-artifacts)

#### osgifier:deploy-osgified-artifacts

Use this goal to deploy artifacts which previously was osgified via goal *osgify-artifacts*.

To invoke this goal during the build lifecycle of your project, add the snipped below to your `pom.xml` and adjust it
to your needs.

```xml
<project>
  <build>
    <plugins>
      <plugin>
        <groupId>org.sourcepit.osgifier</groupId>
        <artifactId>osgifier-maven-plugin</artifactId>
        <goals>
          <goal>deploy-osgified-artifacts</goal> <!-- bount to phase *deploy* -->
        </goals>
        <configuration>
          <!-- 
            Target repository.
            
            ```
            <repository>
              <id>repo-id</id>
              <url>http://foo.bar/repo</url>
            </repository>
            ```
            
            Type: org.apache.maven.model.Repository
            Required: true
          -->
          <repository />
        </configuration>
      </plugin>
    </plugins>
  </build>
</project>
```

#### osgifier:generate-manifest

The goal *generate-manifest* can be used to generate OSGi manifest files for Java projects.

**Note:** This goal considers the dependency information of this project to determine proper OSGi requirements. For
that the dependency artifacts musn't be OSGi bundles for themselves but it is highly recommended.

To invoke this goal during the build lifecycle of your project, add the snipped below to your `pom.xml` and adjust it
to your needs.

```xml
<project>
  <build>
    <plugins>
      <plugin>
        <groupId>org.sourcepit.osgifier</groupId>
        <artifactId>osgifier-maven-plugin</artifactId>
        <goals>
          <goal>generate-manifest</goal> <!-- bount to phase *process-classes* -->
        </goals>
        <configuration>
          <!-- 
            Output directory where the generated META-INF/MANIFEST.MF for the main bundle
            goes to.
            
            Type: java.io.File
            Default Value: ${project.build.directory}/generated-resources/bundle
            Required: false
          -->
          <bundleOutputDirectory />
          <!-- 
            With header modifications you can customize the generated `MANIFEST.MF` file.
            You can remove or add headers.
            
            ```
            <headerModifications>
              <headers>
                <header>
                  <name>Bundle-Vendor</name>
                  <value>Sourcepit.org</value>
                  <after>Bundle-SymbolicName</after>
                </header>
                <header>
                  <name>Bundle-Name</name>
                  <value>${project.name}</value>
                  <before>Bundle-Vendor</before>
                </header>
              </headers>
              <removals>
                <name>Ant-Version<name>
                <name>Created-By<name>
              </removals>
            </headerModifications>
            ```
            
            Type: org.sourcepit.osgifier.core.headermod.HeaderModifications
            Required: false
          -->
          <headerModifications />
          <!-- 
            Mapping between option name and value. These options will be passed to the
            OSGifier and are intended to customize the OSGifiers default behavior to your
            needs.
            
            ```
            <options>
              <symbolicNameMappings>
                xalan:xalan:jar=org.apache.xalan,
                stax:stax-api:jar=javax.xml.stream,
              </symbolicNameMappings>
              <overrideNativeBundles>
                slf4j.api
              </overrideNativeBundles>
            </options>
            ```
            
            Type: java.util.Map
            Required: false
          -->
          <options />
          <!-- 
            When set to `true`, the generated `MANIFEST.MF` will also be copied to
            `${project.basedir}/META-INF/MANIFEST.MF`.
            
            Type: boolean
            Default Value: false
            Required: false
          -->
          <pde />
          <!-- 
            Whether an Eclipse compatible `MANIFEST.MF` file for the source artifacts
            should be generated or not.
            
            Property: ${source.skip}
            Type: boolean
            Default Value: false
            Required: false
          -->
          <skipSource />
          <!-- 
            Output directory where the generated META-INF/MANIFEST.MF for the source bundle
            goes to.
            
            Type: java.io.File
            Default Value: ${project.build.directory}/generated-resources/bundle.source
            Required: false
          -->
          <sourceBundleOutputDirectory />
          <!-- 
            Classifier that is used for the internal source artifact stub (used when an
            Eclipse compatible `MANIFEST.MF` file should be generated).
            
            Property: ${maven.source.classifier}
            Type: java.lang.String
            Default Value: sources
            Required: false
          -->
          <sourceClassifier />
          <!-- 
            `Bundle-SymbolicName` header value for this project.
            
            Type: java.lang.String
            Default Value: ${project.artifactId}
            Required: false
          -->
          <symbolicName />
        </configuration>
      </plugin>
    </plugins>
  </build>
</project>
```

#### osgifier:inject-manifest

The goal *inject-manifest* can be used to inject the OSGi manifest (generated with the *generate-manifest* goal) into
packaged JAR. Reason: Lines are not wrapped after 72 bytes, so content of the manifest file is still human readable
(and parsable by each parser known to me).

To invoke this goal during the build lifecycle of your project, add the snipped below to your `pom.xml` and adjust it
to your needs.

```xml
<project>
  <build>
    <plugins>
      <plugin>
        <groupId>org.sourcepit.osgifier</groupId>
        <artifactId>osgifier-maven-plugin</artifactId>
        <goals>
          <goal>inject-manifest</goal> <!-- bount to phase *package* -->
        </goals>
        <configuration>
          <!-- 
            Classifier to identify this projects source artifact.
            
            Property: ${maven.source.classifier}
            Type: java.lang.String
            Default Value: sources
            Required: false
          -->
          <sourceClassifier />
        </configuration>
      </plugin>
    </plugins>
  </build>
</project>
```

#### osgifier:install-osgified-artifacts

Use this goal to install artifacts which previously was osgified via goal *osgify-artifacts* to your local repository.

To invoke this goal during the build lifecycle of your project, add the snipped below to your `pom.xml` and adjust it
to your needs.

```xml
<project>
  <build>
    <plugins>
      <plugin>
        <groupId>org.sourcepit.osgifier</groupId>
        <artifactId>osgifier-maven-plugin</artifactId>
        <goals>
          <goal>install-osgified-artifacts</goal> <!-- bount to phase *install* -->
        </goals>
      </plugin>
    </plugins>
  </build>
</project>
```

#### osgifier:osgify-artifacts

The goal *osgify-artifacts* can be used to fetch a set of artifacts together with their transitive dependencies from
Maven repositories and to equip each with its own OSGi `MANIFES.MF`.

**Note**: Use this goal together with the goals *install-osgified-artifacts* and *deploy-osgified-artifacts* to
install and deploy the osgified artifacts to a Maven repository.

To invoke this goal during the build lifecycle of your project, add the snipped below to your `pom.xml` and adjust it
to your needs.

```xml
<project>
  <build>
    <plugins>
      <plugin>
        <groupId>org.sourcepit.osgifier</groupId>
        <artifactId>osgifier-maven-plugin</artifactId>
        <goals>
          <goal>osgify-artifacts</goal> <!-- bount to phase *package* -->
        </goals>
        <configuration>
          <!-- 
            Type: java.util.List
            Required: true
          -->
          <artifacts />
          <!-- 
            Optional string that replaces the *groupId* of any osgified artifact. Use this
            to prevent conflicts with the original artifacts.
            
            Type: java.lang.String
            Required: false
          -->
          <groupId />
          <!-- 
            Optional string that will be prepended to the *groupId* of any osgified
            artifact. Use this to prevent conflicts with the original artifacts.
            
            Type: java.lang.String
            Required: false
          -->
          <groupIdPrefix />
          <!-- 
            Mapping between option name and value. These options will be passed to the
            OSGifier and are intended to customize the OSGifiers default behavior to your
            needs.
            
            ```
            <options>
              <symbolicNameMappings>
                xalan:xalan:jar=org.apache.xalan,
                stax:stax-api:jar=javax.xml.stream,
              </symbolicNameMappings>
              <overrideNativeBundles>
                slf4j.api
              </overrideNativeBundles>
            </options>
            ```
            
            Type: java.util.Map
            Required: false
          -->
          <options />
          <!-- 
            Directory the osgified artifacts will be stored in.
            
            Type: java.io.File
            Default Value: ${project.build.directory}/osgified
            Required: false
          -->
          <workDir />
        </configuration>
      </plugin>
    </plugins>
  </build>
</project>
```

### Plugin Management

It is a good practice to define plugin versions in the plugin management section of your or a parents `pom.xml`.

```xml
<project>
  <build>
    <pluginManagement>
      <plugins>
        <plugin>
          <groupId>org.sourcepit.osgifier</groupId>
          <artifactId>osgifier-maven-plugin</artifactId>
          <version>0.22.0</version>
        </plugin>
      </plugins>
    </pluginManagement>
  </build>
</project>
```

This plugin also provides goals that can be invoked from command line. For convenience you can use the shorter plugin
prefix `osgifier` for that instead of the full-blown plugin coordinates. To enable the prefix add this plugins group
id to the list of plugin groups in your 'settings.xml':

```xml
<settings>
  <pluginGroups>
    <pluginGroup>org.sourcepit.osgifier</pluginGroup>
  </pluginGroups>
</settings>
```

See also [Introduction to Plugin Prefix Resolution](http://maven.apache.org/guides/introduction/introduction-to-plugin-prefix-mapping.html).

## OSGifier Maven APIs and Components

```xml
<project>
  <dependencies>
    <dependency>
      <groupId>org.sourcepit.osgifier</groupId>
      <artifactId>org.sourcepit.osgifier.maven</artifactId>
      <version>0.22.0</version>
    </dependency>
  </dependencies>
</project>
```

## OSGifier Core APIs and Components

```xml
<project>
  <dependencies>
    <dependency>
      <groupId>org.sourcepit.osgifier</groupId>
      <artifactId>org.sourcepit.osgifier.core</artifactId>
      <version>0.22.0</version>
    </dependency>
  </dependencies>
</project>
```

## How to Contribute

The simplest way of contributing is probably to report issues. You can do so using the [Issue Tracker](https://github.com/sourcepit/osgifier/issues).

If you want to contribute your code or just want to share it with others, you [can create a fork of the official
repository](https://github.com/sourcepit/osgifier/fork) at any time, for which you will have full access so that your
local changesets can be pushed to it.

Once your code is ready and accepted (see code style section below), it is then easy for the project owners to pull
your changesets into the official repository - all you have to do is to [create a pull request](https://help.github.com/articles/creating-a-pull-request).

For general information see [Contributing to Open Source on GitHub](https://guides.github.com/activities/contributing-to-open-source).

## License

[The Apache Software License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.txt)

