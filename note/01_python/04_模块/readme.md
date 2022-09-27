# 模块

退出`Python`解释器之后，再次进入时，之前在`Python`解释器中定义的函数和变量就丢失了。因此编写较长程序时，建议用文本编辑器代替解释器，执行文件中的输入内容，这就是编写`脚本`。随着程序越来越长，为了方便维护，最好把脚本拆分成多个文件。编写脚本还有一个好处，不同程序调用同一个函数时，不用每次把函数复制到各个程序。

为实现这些需求，`Python`把各种定义存入一个文件，在脚本或解释器的交互式实例中使用。这个文件就是`模块`；模块中的定义可以`导入`到其他模块或`主模块`（在顶层和计算器模式下，执行脚本中可以访问的变量集）。

模块时包含Python定义和语句的文件。其`文件名`是`模块名加后缀名`为`.py`。在模块内部，通过全局变量`__name__`可以获取模块名（即为字符串）。例如：用文本编辑器在当前目录下创建`fibo.py`文件，输入以下内容：

```python
# fibo.py
def fibWithPrint(n):
    a, b = 0, 1
    while a < n:
        print(a, end=' ')
        a, b = b, a + b
    print()

def fibWithReturn(n):
    a, b = 0, 1
    result = []
    while n > a:
        result.append(a)
        a, b = b, a + b
    return result
```

同目录创建`index.py`文件用以下命令导入`fibo.py`模块

```python
# index.py
# 导入模块
import fibo

# 调用模块中的方法
print(fibo.fibWithReturn(14))
fibo.fibWithPrint(14)

# 赋值模块中的方法并且调用
fibWithResult = fibo.fibWithReturn
print(fibWithResult(14))

# 打印模块名字
print(fibo.__name__) # fibo
```

> 执行`index.py`查看结果

```python
import fibo
```

这不会将`fibo` 直接定义在当前命名空间中的函数的名称添加到当前[命名空间](https://docs.python.org/zh-cn/3/glossary.html#term-namespace)中（有关详细信息，请参阅Python [作用域和命名空间](https://docs.python.org/zh-cn/3/tutorial/classes.html#tut-scopes)）；它只在`fibo`那里添加模块名称。使用模块名称，您可以访问功能。

## [模块详解](./01_%E6%A8%A1%E5%9D%97%E8%AF%A6%E8%A7%A3/readme.md)

## [标准模块](./02_%E6%A0%87%E5%87%86%E6%A8%A1%E5%9D%97/readme.md)

## [dir()](./03_dir()%E5%87%BD%E6%95%B0/readme.md)函数