# 模块搜索路径
当一个名为`spam`的模块被导入时，解释器首先搜索具有改名称的内置模块。这些模块的名字被列在[`sys.builtin_module_names`](https://docs.python.org/zh-cn/3/library/sys.html#sys.builtin_module_names)中。如果没有找到，它就在变量[`sys.path`](https://docs.python.org/zh-cn/3/library/sys.html#sys.path)给出的目录列表中搜索一个名为`spam.py`的文件，[`sys.path`](https://docs.python.org/zh-cn/3/library/sys.html#sys.path)从这些位置初始化：

- 输入脚本的目录（或未指定文件时的当前目录）。
- [PYTHONPAYH](https://docs.python.org/zh-cn/3/using/cmdline.html#envvar-PYTHONPATH)（目录列表，与`shell`变量`PATH`的语法一样）。
- 依赖于安装的默认值（按照惯例包括一个`site-packages`目录，有[`site`](https://docs.python.org/zh-cn/3/library/site.html#module-site)模块处理）。

> 注解：在支持`symlink`的文件系统中，输入脚本目录是在追加`symlink`后计算出来的。换句话说，包含`symlink`的目录并`没有`添加至模块搜索路径。

初始化后，Python程序可以更改[`sys.path`](https://docs.python.org/zh-cn/3/library/sys.html#sys.path)。运行脚本的目录在标准库路径之前，基于搜索路径的开头。即，加载的是该目录里的脚本，而不是标准库的同名模块。除非刻意替换，否则会报错。详见[标准模块](https://docs.python.org/zh-cn/3/tutorial/modules.html#tut-standardmodules)