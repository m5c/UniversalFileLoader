# Universal File Loader

Reliable file system access for classpath resources.

![version](https://img.shields.io/badge/version-1.0.0-brightgreen)
![coverage](https://img.shields.io/badge/coverage-100%25-brightgreen)
![building](https://img.shields.io/badge/build-passing-brightgreen)

## About

This library enables pseudo file system access for classpath resources, via replication to the OS-buffer directory. It provides reliable resource access independent of build and runtime context for API calls that stipulate resource loading via file system.
 
### Example

 * Imagine you have a resource ```poem.txt``` in your classpath that you with to access at runtime.
 * The most reliable way is access as classpath resource.
 * However, [some libraries](https://download.eclipse.org/modeling/emf/emf/javadoc/2.4.2/org/eclipse/emf/ecore/resource/ResourceSet.html#getResource(org.eclipse.emf.common.util.URI,%20boolean)) may stipulate resource access as a [Java File object](https://docs.oracle.com/javase/7/docs/api/java/io/File.html).
 * File object paths are unreliable since they may be different depending on runtime environment.
 * As a workaround you can invoke this library and generate a reliable file system location via the OS provided buffer directory.
 
## Documentation

For a full description of available functionality, read the [JavaDoc](https://kartoffelquadrat.github.io/UniversalFileLoader/eu/kartoffelquadrat/ufl/package-summary.html)

## Invokation

 1. Get the sources:  
```git clone https://github.com/kartoffelquadrat/UniversalFileAccess.git```

 2. Compile the project with maven. This adds the library to your local ```.m2``` repository.  
```mvn clean install```
 
 3. Add this library as maven dependency:  
 ```xml
<dependency> 
    <groupId>eu.kartoffelquadrat</groupId>
    <artfactId>universalfileloader</artfactId>
    <version>1.0.0</version>
</dependency> 
```

 4. Call the library:
 ```java
    File poemFile = UniversalFileLoader.getInstance().getFileForResource("poem.txt");
```

## Contact / Pull Requests

 * Author: Maximilian Schiedermeier ![email](email.png)
 * Github: Kartoffelquadrat
 * Webpage: https://www.cs.mcgill.ca/~mschie3
 * License: [MIT](https://opensource.org/licenses/MIT)

