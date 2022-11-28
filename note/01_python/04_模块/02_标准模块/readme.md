Python自带一个标准模块的库，它在Python库参考（此处以下称为“库参考”）里另外描述。一些模块是内嵌到编译器里面的，它们给一些并非语言核心但却内嵌的操作提供接口，要么是为了效率，要么是给操作系统基础操作例如系统调入提供接口。这些模块集是一个配置选项，并且还依赖于底层的操作系统。例如：[winreg](https://docs.python.org/zh-cn/3/library/winreg.html#module-winreg)模块只在`Windows`系统上提供，一个特别值得注意的模块[sys](https://docs.python.org/zh-cn/3/library/sys.html#module-sys)，他被内嵌到每一个Python编译器中。`sys.ps1c`中`sys.ps2`变量定义了一些字符，它们可以用作主提示符和辅助提示符：

```python
>>> import sys
>>> sys.ps1
'>>> '
>>> sys.ps2
'... '
>>> sys.ps1 = 'C> '
C> print('Yuck!')
Yuck!
C>
```

只有解释器用于交互模式时，才定义这两个变量。

变量 `sys.path` 是字符串列表，用于确定解释器的模块搜索路径。该变量以环境变量 [PYTHONPATH](https://docs.python.org/zh-cn/3/using/cmdline.html#envvar-PYTHONPATH) 提取的默认路径进行初始化，如未设置 [PYTHONPATH](https://docs.python.org/zh-cn/3/using/cmdline.html#envvar-PYTHONPATH)，则使用内置的默认路径。可以用标准列表操作修改该变量：

```python
>>> import sys
>>> sys.path.append('/ufs/guido/lib/python')
```