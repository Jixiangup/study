# 多目录中的包

包支持一个更特殊的属性[__path__](https://docs.python.org/zh-cn/3/reference/import.html#path__)。在包的：[file:__init__.py](file:///__init__.py)文件中的代码被执行前，该属性被初始化为包含 :[file:__init__.py](file:///__init__.py) 文件所在的目录名在内的列表。可以修改此变量；但这样做会影响在此包中搜索子模块和子包。

这个功能虽然不常用，但可用于扩展包中的模块集。