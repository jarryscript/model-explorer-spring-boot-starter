# Model Explorer

Model Explorer 是一个可以查看开发过程中领域模型演进历史的工具。
Model Explorer 可以扫描项目源代码，生成Plantuml类图并持久化，同时提供UI界面，以浏览已经保存的，各个版本的类图。

## Getting Started
1. 在工程中引入model-explorer-spring-boot-starter:
```gradle
implementation("io.github.jarryzhou:model-explorer:${latest_version}")
```

2. 在application.yaml中添加配置
```yaml
model-explorer:
  # 是否启动自动配置以使用Model explorer, 默认为false
  enabled: true
  # 要扫描并生成类图动包路径
  scanPackages:
    - xxx.xxx.xxx
    - xxx.xxx.xxx
  # 是否持久化类图, 若设置为true,需要提供Spring JDBC 数据源配置，默认为false
  persist: true
```

3. 启动工程并访问 ${your_host}/model-explorer

目前已经支持的数据库类型:
- Postgres
- MySql


## Contributing

If you would like to contribute to the Model Explorer project, please fork the repository and submit a pull request. If you find a bug or have a feature request, please create a new issue.

## License

This project is licensed under the MIT License - see the LICENSE file for details.