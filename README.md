# Universal File Loader

Reliable file system access for classpath resources.

![version](https://img.shields.io/badge/version-1.0.0-brightgreen)
![coverage](https://img.shields.io/badge/coverage-100%25-brightgreen)
![building](https://img.shields.io/badge/build-passing-brightgreen)

## About

This library provides file-system access for classpath resources, using the OS provided buffer directory. Resulting access is reliable and independent of program packaging and runtime environment.
 
### Example

 * Imagine a resource ```poem.txt``` in your classpath that you wish to access at runtime.
 * The [third party library](https://download.eclipse.org/modeling/emf/emf/javadoc/2.4.2/org/eclipse/emf/ecore/resource/ResourceSet.html#getResource(org.eclipse.emf.common.util.URI,%20boolean)) consuming ```poem.txt``` mandates [a file system location](https://docs.oracle.com/javase/7/docs/api/java/io/File.html).
 * File object paths are unreliable since they depend on product packaging and runtime environment, e.g.:
    * Actual file location during development in IDE.
    * Pseudo file location when packaged to a self-contained JAR.
 * Using this library you obtain a reliable file system location for your classpath resource ```poem.txt```.
 
## Documentation

For a full description of available functionality, read the [JavaDoc](https://m5c.github.io/UniversalFileLoader/eu/kartoffelquadrat/ufl/package-summary.html)

## Invocation

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
 * Github: m5c
 * Webpage: https://www.cs.mcgill.ca/~mschie3
 * License: [MIT](https://opensource.org/licenses/MIT)

