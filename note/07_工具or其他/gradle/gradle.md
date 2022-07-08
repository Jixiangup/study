# 项目目录结构

```
|- build: 封装编译后的字节码、打成的包(jar\war)测试报告等信息
|- gradke: 封装包装器文件夹
|--|-- wrapper
|--|--|-- gradle-wrapper.jar
|--|--|-- gradle-wrapper.properties
|-- src: 代码目录
|-- gradlew
|-- gradkew.bat 包装器启动脚本
|-- build.gradle 构建脚本,类似于maven中的pom.xml
|-- settings.gradle 设置文件,定义项目及子项目名称信息,和项目是一一对应关系
```
