# Model Explorer

Model Explorer is a Kotlin project that scans specified packages, generates PlantUML class diagrams from the model classes and shows in a web UI. 
It provides ability to store the generated diagrams in the database, or simply show the current class diagram without using database for storage.

Usually for projects which have evolving domain models, development team might need to check the progress of the evolving.
Model Explorer is designed for this goal.

## Getting Started
1. Add Model Explorer into your project.

Gradle
```groovy
implementation group: 'io.github.jarryzhou', name: 'model-explorer', version: '0.1.0'
```

Maven
```xml
<dependency>
    <groupId>io.github.jarryzhou</groupId>
    <artifactId>model-explorer</artifactId>
    <version>0.1.0</version>
</dependency>
```

2. Configure Model Explorer
```yaml
model-explorer:
  enable: true # Whether to enable Model Explorer. Default: true
  scanPackages:  # Packages to scan and generate class diagram
      - com.test
      - com.test2
```

3. Start your application and visit {Your host}/model-explorer to get to the web UI.
By visiting the web UI, if model-explorer.persist is set to false, Model explorer will scan the specified packages and show class diagram.
if model-explorer.persist is set to true, Model explorer will load class diagram history from database, and show diagram upon clicking a diagram title.

## Contributing

If you would like to contribute to the Model Explorer project, please fork the repository and submit a pull request. If you find a bug or have a feature request, please create a new issue.

## License

This project is licensed under the MIT License - see the LICENSE file for details.