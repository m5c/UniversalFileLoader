# Universal File Loader

Reliable resource access for stubborn libraries.

![version](https://img.shields.io/badge/version-1.0.0-brightgreen)
![coverage](https://img.shields.io/badge/coverage-100%25-brightgreen)
![building](https://img.shields.io/badge/build-passing-brightgreen)

## About

Some stubborn libraries may mandate you to load resources form disk as java File objects, completely ignoring the
fact that java has an established and reliable way to access resources from classpath. This library offers a
workaround, by cloning requested classpath resources into a file system buffer and producing a corresponding java
file reference on demand.
 
## Documentation

For a full description of available functionality, read the [JavaDoc](https://kartoffelquadrat.github.io/UniversalFileLoader/eu/kartoffelquadrat/universalfileaccess/package-summary.html)

## Invokation

 1. Get the sources:  
```git clone https://github.com/kartoffelquadrat/UniversalFileAccess.git```

 2. Compile the project with maven. This adds the library to your local ```.m2``` repository.  
```mvn clean install```
 
 3. Add this library as maven dependency:  
 ```xml
<dependency> 
    <groupId>eu.kartoffelquadrat</groupId>
    <artfactId>unversalfileloader</artfactId>
    <version>1.0.0</version>
</dependency> 
```

 4. Call the library:
 ```java
    UniversalFileLoader.getInstance().getFileForResource("poem.txt")
```

## Contact / Pull Requests

 * Author: Maximilian Schiedermeier ![email](email.png)
 * Github: Kartoffelquadrat
 * Webpage: https://www.cs.mcgill.ca/~mschie3
 * License: [MIT](https://opensource.org/licenses/MIT)

