# “已编译”Python文件

为了快速加载模块，Python 把模块的编译版缓存在 `__pycache__` 目录中，文件名为 `module.version.pyc`，version 对编译文件格式进行编码，一般是 Python 的版本号。例如，CPython 的 3.3 发行版中，spam.py 的编译版本缓存为 `__pycache__/spam.cpython-33.pyc`。使用这种命名惯例，可以让不同 Python 发行版及不同版本的已编译模块共存。

Python 对比编译版本与源码的修改日期，查看它是否已过期，是否要重新编译，此过程完全自动化。此外，编译模块与平台无关，因此，可在不同架构系统之间共享相同的支持库。

Python 在两种情况下不检查缓存。其一，从命令行直接载入模块，只重新编译，不存储编译结果；其二，没有源模块，就不会检查缓存。为了支持无源文件（仅编译）发行版本， 编译模块必须在源目录下，并且绝不能有源模块。

给专业人士的一些小建议：

- 在Python命令中使用[`-O`](https://docs.python.org/zh-cn/3/using/cmdline.html#cmdoption-O)或[`-OO`](https://docs.python.org/zh-cn/3/using/cmdline.html#cmdoption-OO)开关可以见效编译模块的大小。`-O`去除断言语句，`-OO`去除断言语句和`__doc__`字符串。有些程序可能依赖于这些内容，因此没有十足的把握，不要使用这两个选项。“优化过的”模块带有`opt-`标签，并且文件通常会小一些。将来的发行版或许会改进优化的效果。

- 从`.pyc`文件读取的程序不比从`.py`读取的执行速度快，`.pyc`文件只是加载速度更快。
- [`compileall`](https://docs.python.org/zh-cn/3/library/compileall.html#module-compileall)模块可以为一个陌路下的所有模块创建`.pyc`文件。
- 本过程的细节及决策流程图，详见[PEP 3147](https://www.python.org/dev/peps/pep-3147)