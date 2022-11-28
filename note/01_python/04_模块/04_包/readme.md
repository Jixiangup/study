# 包

包是一种用"点式模块名"构造Python模块命名空间的方法。例如，模块名A.B表示`A`中名为`B`的子模块。正如模块可以区分不同模块之间的全局变量名称一样，点式模块名可以区分`NumPy`或`Pillow`等不同多模块包之间的模块名称。

假设要为统一处理声音文件于声音数据设计一个模块集("包")。声音文件的格式很多（通常以扩展名来识别，例如为了实现对声音数据的不同处理（例如：混声、添加回声、均衡器功能、创造人工立体声效果），还要编写无穷无尽的模块流。下面这个分级文件数展示了这个包的架构

```
sound/                          Top-level package
      __init__.py               Initialize the sound package
      formats/                  Subpackage for file format conversions
              __init__.py
              wavread.py
              wavwrite.py
              aiffread.py
              aiffwrite.py
              auread.py
              auwrite.py
              ...
      effects/                  Subpackage for sound effects
              __init__.py
              echo.py
              surround.py
              reverse.py
              ...
      filters/                  Subpackage for filters
              __init__.py
              equalizer.py
              vocoder.py
              karaoke.py
              ...
```

导入包时，Python 搜索 `sys.path` 里的目录，查找包的子目录。

Python 只把含 `__init__.py` 文件的目录当成包。这样可以防止以 `string` 等通用名称命名的目录，无意中屏蔽出现在后方模块搜索路径中的有效模块。 最简情况下，`__init__.py` 只是一个空文件，但该文件也可以执行包的初始化代码，或设置 `__all__` 变量，详见下文。

还可以从包中导入单个模块，例如：

```python
import sound.effects.echo
```

这段代码加载子模块 `sound.effects.echo`，但饮用时必须使用子模块全名：

```python
sound.effects.echo.echofilter(input, output, delay=0.7, atten=4)
```

另一种导入子模块的方法是 ：

```python
from sound.effects import echo
```

这段代码还可以加载子模块 echo ，不加包前缀也可以使用。因此，可以按如下方式使用：

```python
echo.echofilter(input, output, delay=0.7, atten=4)
```

Import 语句的另一种变体是直接导入所需的函数或变量：

```python
from sound.effects.echo import echofilter
```

同样，这样也会加载子模块 echo，但可以直接使用函数 echofilter()：

```python
echofilter(input, output, delay=0.7, atten=4)
```

注意，使用 `from package import item` 时，item 可以是包的子模块（或子包），也可以是包中定义的函数、类或变量等其他名称。`impor`t 语句首先测试包中是否定义了 item；如果未在包中定义，则假定 item 是模块，并尝试加载。如果找不到 item，则触发 [ImportError](https://docs.python.org/zh-cn/3/library/exceptions.html#ImportError) 异常。

相反，使用 `import item.subitem.subsubitem` 句法时，除最后一项外，每个 item 都必须是包；最后一项可以是模块或包，但不能是上一项中定义的类、函数或变量。

## [从子包中导入](./01_%E4%BB%8E%E5%AD%90%E5%8C%85%E4%B8%AD%E5%AF%BC%E5%85%A5/readme.md)

## [子包参考](./02_%E5%AD%90%E5%8C%85%E5%8F%82%E8%80%83/readme.md)

## [多目录中的包](./03_%E5%A4%9A%E7%9B%AE%E5%BD%95%E4%B8%AD%E7%9A%84%E5%8C%85/readme.md)