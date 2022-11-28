# 搭建python开发环境

- 直接进入python下载3.x之后的版本即可
- 唤出`CMD`输入`py`或`python`进入python的编译器, 如显示python详细信息则表示安装成功, 退出输入`quit()`
- 在`Mac OS`中输入`python3`进入python的编辑器

# python学习

## HelloWorld

```python
flag = False
if flag: 
    print("Hello Python!!!")
else:
    print("Hello Bnyte!!!")
```

## 配置字符编码

默认情况下，Python 源码文件的编码是 UTF-8。

如果不使用默认编码，则要声明文件的编码，文件的 第一 行要写成特殊注释。句法如下：

```python
# -*- coding: encoding -*-
```

其中，encoding 可以是 Python 支持的任意一种 [codecs](https://docs.python.org/zh-cn/3/library/codecs.html#module-codecs)。

比如，声明使用 Windows-1252 编码，源码文件要写成：

```python
# -*- coding: cp1252 -*-
```

第一行 的规则也有一种例外情况，源码以 [UNIX "shebang"](https://docs.python.org/zh-cn/3/tutorial/appendix.html#tut-scripts) 行 开头。此时，编码声明要写在文件的第二行。例如：

```python
#!/usr/bin/env python3
# -*- coding: cp1252 -*-
```

## python的运算

> `/`除法

使用除法时会自动转换为`float`且会保留小数, 可称为`精准运算`

```python
5 / 2
# 结果为2.5
```

> `//`整除法

使用整除法时会自动转换为`int`并且会忽略后面的小数

```python
5 // 2
# 结果为2
```

> '*'乘法

当字符串与数字使用乘法运算时, 会对字符串倍数打印

```python
3 * "大家好!"
# 大家好!大家好!大家好!
```

> `**`乘方

使用乘方时会计算该数的乘方

```python
2 ** 7
# 128
```

> 其他与`Java`相同

## python字符串

字符串字面值可以实现跨行连续输入。实现方式是用三引号："""...""" 或 '''...'''，字符串行尾会自动加上回车换行，如果不需要回车换行，在行尾添加 \ 即可, 以下Demo表示首行和末行都不换行。示例如下：

```python
print("""\
Usage: thingy [OPTIONS]
     -h                        Display this usage message
     -H hostname               Hostname to connect to\
""")
```

> 合并字符串

```python
str = 'Python'
'Hello, ''Python' 
'Hello, ' + 'Python'
'Hello' + str
# Hello, Python
```

> 字符串运算

```python
3 * 'Hello!'
# Hello!Hello!Hello! 
```

> 拆分长字符串(不能作用于表达式以及变量)

```python
str = ('大家好'
       '这是干什么？')
print(str) # 打印不换行 '大家好这是干什么？'
```

> 字符串支持通过索引下标访问

注意: 负索引从-1开始, -0和0没有区别, 切片会自动处理索引越界

```python
str = "Python"
word[0] # 打印P
word[-1] # 打印n 从末尾开始计数 -2 则是o
word[0:2] # 类似于substring 包前不包后，截取子字符串'Py'
word[:2] # 省略前一个则是为0, 省略后一个则为字符串的最后一个(包含)，截取子字符串'Py'
len(str) # 获取字符串长度
```

## 流程控制

### while

```python
a, b = 0, 1
while a < 10:
    print(a, end=" ")
    a, b = b, a + b
```

### if elif else

```python
score = 96
if score >= 95:
    print("A")
elif score >= 80:
    print("B")
elif score >= 60:
    print("C")
else:
    print("D")
```

### for

> 01-遍历普通集合
```python
strs = ["windows", "mac", "linux"]
for str in strs:
    print(str, len(str), end=" ")

print("\n------------------------")
```

> 02-遍历对象集合
```python
users = {
    "id": 100,
    "name": "猪猪侠",
    "age": 20
}

print("\n------------------------")

for user in users:
    print(user, end=" ") # 获取对象中的所有属性名
    print(users[user], end=" ") # 获取对象中对应属性名的属性值

print("\n------------------------")
```

> 03-遍历对象集合并删除集合中的某个元素
```
for key, value in users.copy().items():
    # print("key > ", key, ", value > ", value, end= " | ") # key >  id , value >  100 | key >  name , value >  猪猪侠 | key >  age , value >  20 |
    if value == 100:
        del users[key]
        print(users)

print("\n------------------------")
```

## 函数

> range()
```python
for i in range(10):
    print(i, end=" ")

print("\n-----------------------")

for i in range(5, 10):
    print(i, end=" ") # 5 6 7 8 9 包前不包后原则

print("\n-----------------------")

print(list(range(0, 10, 3))) # 0 3 6 9 从0到10中获取数值，并且每次都会加3

print("\n-----------------------")

print(list(range(-10, -100, -30))) # [-10, -40, -70]
```

> len()
```
let(arr)
```

## 语句

> pass

语句不执行任何操作。语法上需要一个语句，但程序不实际执行任何动作时，可以使用该语句

创建一个最小的类，没有任何操作：
```
class MyEmptyClass:
    pass
```

死循环
```python
while True: 
    pass
```

## match语句

语句接受一个match表达式并将其值与作为一个或多个 case 块给出的连续模式进行比较。这在表面上类似于 C、Java 或 JavaScript（以及许多其他语言）中的 switch 语句，但它更类似于 Rust 或 Haskell 等语言中的模式匹配。只有匹配的第一个模式被执行，它还可以从值中提取组件（序列元素或对象属性）到变量中。

最简单的形式就是将一个目标值与一个或多个字面值进行比较

```python
def http_error(status):
    match status:
        case 400:
            return "Bad request"
        case 404:
            return "Not found"
        case 418:
            return "I'm a teapot"
        # case _: 等同与Java的switch中的 default
        case _:
            return "Something's wrong with the internet"

print(http_error(404))
```

注意最后一个代码块：“变量名” _ 被作为 通配符 并必定会匹配成功。 如果没有 case 语句匹配成功，则不会执行任何分支。

使用 | （“ or ”）在一个模式中可以组合多个字面值：

```python
match status:
    case 401 | 403 | 404:
        print("Not allowed")
```

模式的形式类似解包赋值，并可被用于绑定变量

## 定义函数

下面代码是创建一个计算指定范围结果值内的`斐波那契数列`组合

```python
# 创建函数
def fib(n):
    a, b = 0, 1
    while n > a:
        print(a, end=' ')
        a, b = b, a+b
    print()

# 执行函数
fib(14)
```

定义 函数使用关键字 def，后跟函数名与括号内的形参列表。函数语句从下一行开始，并且必须缩进。

函数内的第一条语句是字符串时，该字符串就是文档字符串，也称为 docstring，详见 文档字符串。利用文档字符串可以自动生成在线文档或打印版文档，还可以让开发者在浏览代码时直接查阅文档；Python 开发者最好养成在代码中加入文档字符串的好习惯。

函数在 执行 时使用函数局部变量符号表，所有函数变量赋值都存在局部符号表中；引用变量时，首先，在局部符号表里查找变量，然后，是外层函数局部符号表，再是全局符号表，最后是内置名称符号表。因此，尽管可以引用全局变量和外层函数的变量，但最好不要在函数内直接赋值（除非是 global 语句定义的全局变量，或 nonlocal 语句定义的外层函数变量）。

在调用函数时会将实际参数（实参）引入到被调用函数的局部符号表中；因此，实参是使用 按值调用 来传递的（其中的 值 始终是对象的 引用 而不是对象的值）。 1 当一个函数调用另外一个函数时，会为该调用创建一个新的局部符号表。

函数定义在当前符号表中把函数名与函数对象关联在一起。解释器把函数名指向的对象作为用户自定义函数。还可以使用其他名称指向同一个函数对象，并访问访该函数：

```python
from tokenize import Number

def fib(n: Number):
    a, b = 0, 1
    while n > a:
        print(a, end=' ')
        a, b = b, a+b
    print()

fib(14)

define_fun = fib
define_fun(14)
```

fib 不返回值，因此，其他语言不把它当作函数，而是当作过程。事实上，没有 return 语句的函数也返回值，只不过这个值比较是 None （是一个内置名称）。一般来说，解释器不会输出单独的返回值 None ，如需查看该值，可以使用 print()：

```python
print(define_fun(14))
# 输出 None
```

编写不直接输出斐波那契数列运算结果，而是返回运算结果列表的函数也非常简单：
```python
# 将斐波那契数列的结果收集起来并且返回
def fibWithResult(n):
    result = []
    a, b = 0, 1
    while n > a:
        result.append(a)
        a, b = b, a + b
    return result

print(fibWithResult(14))
```

## 函数定义详解

函数定义支持可变数量的参数。这里列出三种可以组合使用的形式。

### 默认值参数

为参数指定默认值是非常有用的方式。调用函数时可以比定义时更好的参数，例如：

```python
# 创建带有默认值的函数
def ask_ok(prompt, retries=4, reminder='Please try again!'):
    while True:
        ok = input(prompt)
        if ok in ('y', 'ye', 'yes'):
            return True
        if ok in ('n', 'no', 'nop', 'nope'):
            return False
        retries = retries - 1
        if retries < 0:
            raise ValueError('invalid user response')
        print(reminder)
```

- 只给出必选实惨：`ask_ok("Do you really want to quit?")`

- 给出一个可选实参：`ask_ok('OK to overwrite the file?', 2)`

- 给出所有实参：`ask_ok('OK to overwrite the file?', 2, 'Come on, only yes or no!')`

本例还使用了关键字 in ，用于确认序列中是否包含某个值。

默认值在 定义 作用域里的函数定义中求值，所以：

```python
i = 5

def f(arg=i):
    print(arg)

i = 6
# 输出5
f() 
```

> 重要警告： 默认值只计算一次。默认值为列表、字典或类实例等可变对象时，会产生与该规则不同的结果。例如，下面的函数会累积后续调用时传递的参数：

```python
def f(a, L=[]):
    L.append(a)
    return L

print(f(1))
print(f(2))
print(f(3))
```

输出结果如下：
```
[1]
[1, 2]
[1, 2, 3]
```

不想在后续调用之间共享默认值时，应以如下方式编写函数：
```python
def f(a, L=None):
    if L is None:
        L = []
    L.append(a)
    return L
```

### 关键字参数

`kwarg=value` 形式的 关键字参数 也可以用于调用函数。函数示例如下：

```python
def parrot(voltage, state='a stiff', action='voom', type='Norwegian Blue'):
    print("-- This parrot wouldn't", action, end=' ')
    print("if you put", voltage, "volts through it.")
    print("-- Lovely plumage, the", type)
    print("-- It's", state, "!")
parrot(state='ggboy', voltage='eeboy')
```

最后一个形参为 `**name` 形式时，接收一个字典（详见 映射类型 --- dict），该字典包含与函数中已定义形参对应之外的所有关键字参数。`**name` 形参可以与 *name 形参（下一小节介绍）组合使用（`*name` 必须在 `**name` 前面）， `*name` 形参接收一个 元组，该元组包含形参列表之外的位置参数。例如，可以定义下面这样的函数：

```python
def cheeseshop(kind, *arguments, **keywords):
    print("-- Do you have any", kind, "?")
    print("-- I'm sorry, we're all out of", kind)
    for arg in arguments:
        print(arg)
    print("-" * 40)
    for kw in keywords:
        print(kw, ":", keywords[kw])
```

该函数可以用如下方式调用：

```python
def cheese_shop(kind, *args, **keyvalues):
    print(f"指定参数kind的值是: {kind}")
    print("-" * 40)
    for arg in args:
        print(f"获取到的集合参数列表值是: {arg}")
    print("-" * 40)
    for key in keyvalues:
        print(f"货渠道的key=value中的键是: {key}, 值是: {keyvalues[key]}")
```

输出结果如下：

```
cheese_shop("ggboy",
            "param1",
            "param2",
            "param3",
            username="ggboy1",
            password="ggboy2")
```

注意，关键字参数在输出结果中的顺序与调用函数时的顺序一致。

### 特殊参数

默认情况下，参数可以按位置或显式关键字传递给 Python 函数。为了让代码易读、高效，最好限制参数的传递方式，这样，开发者只需查看函数定义，即可确定参数项是仅按位置、按位置或关键字，还是仅按关键字传递。

函数定义如下：

```python
def f(pos1, pos2, /, pos_or_kwd, *, kwd1, kwd2):
      -----------    ----------     ----------
        |             |                  |
        |        Positional or keyword   |
        |                                - Keyword only
         -- Positional only
```

`/` 和 `*` 是可选的。这些符号表明形参如何把参数值传递给函数：位置、位置或关键字、关键字。关键字形参也叫作命名形参。

#### 位置或关键字参数

函数定义中未使用 / 和 * 时，参数可以按位置或关键字传递给函数。

#### 仅位置参数

此处再介绍一些细节，特定形参可以标记为 仅限位置。仅限位置 时，形参的顺序很重要，且这些形参不能用关键字传递。仅限位置形参应放在 `/` （正斜杠）前。`/` 用于在逻辑上分割仅限位置形参与其它形参。如果函数定义中没有 `/`，则表示没有仅限位置形参。

`/` 后可以是 位置或关键字 或 仅限关键字 形参。

#### 仅限关键字参数

把形参标记为 仅限关键字，表明必须以关键字参数形式传递该形参，应在参数列表中第一个 仅限关键字 形参前添加 `*`。

#### 函数示例

请看下面的函数定义示例，注意 `/` 和 `*` 标记：

```
>>> def standard_arg(arg):
...     print(arg)
...
>>> def pos_only_arg(arg, /):
...     print(arg)
...
>>> def kwd_only_arg(*, arg):
...     print(arg)
...
>>> def combined_example(pos_only, /, standard, *, kwd_only):
...     print(pos_only, standard, kwd_only)
```

第一个函数定义 standard_arg 是最常见的形式，对调用方式没有任何限制，可以按位置也可以按关键字传递参数：

```python
>>> standard_arg(2)
2

>>> standard_arg(arg=2)
2
```

第二个函数 `pos_only_arg` 的函数定义中有 `/`，仅限使用位置形参：

```python
>>> pos_only_arg(1)
1

>>> pos_only_arg(arg=1)
Traceback (most recent call last):
  File "<stdin>", line 1, in <module>
TypeError: pos_only_arg() got some positional-only arguments passed as keyword arguments: 'arg'
```

第三个函数 kwd_only_args 的函数定义通过 * 表明仅限关键字参数：

```python
>>> kwd_only_arg(3)
Traceback (most recent call last):
  File "<stdin>", line 1, in <module>
TypeError: kwd_only_arg() takes 0 positional arguments but 1 was given

>>> kwd_only_arg(arg=3)
3
```

最后一个函数在同一个函数定义中，使用了全部三种调用惯例：

```python
>>> combined_example(1, 2, 3)
Traceback (most recent call last):
  File "<stdin>", line 1, in <module>
TypeError: combined_example() takes 2 positional arguments but 3 were given

>>> combined_example(1, 2, kwd_only=3)
1 2 3

>>> combined_example(1, standard=2, kwd_only=3)
1 2 3

>>> combined_example(pos_only=1, standard=2, kwd_only=3)
Traceback (most recent call last):
  File "<stdin>", line 1, in <module>
TypeError: combined_example() got some positional-only arguments passed as keyword arguments: 'pos_only'
```

下面的函数定义中，`kwds` 把 `name` 当作键，因此，可能与位置参数 `name` 产生潜在冲突：

```python
def foo(name, **kwds):
    return 'name' in kwds
```

调用该函数不可能返回 `True`，因为关键字 `'name'` 总与第一个形参绑定。例如：

```python
>>> foo(1, **{'name': 2})
Traceback (most recent call last):
  File "<stdin>", line 1, in <module>
TypeError: foo() got multiple values for argument 'name'
>>>
```

加上 `/` （仅限位置参数）后，就可以了。此时，函数定义把 `name` 当作位置参数，`'name'` 也可以作为关键字参数的键：

```python
def foo(name, /, **kwds):
    return 'name' in kwds
>>> foo(1, **{'name': 2})
True
```

换句话说，仅限位置形参的名称可以在 **kwds 中使用，而不产生歧义。

#### 小结

以下用例决定哪些形参可以用于函数定义：

```python
def f(pos1, pos2, /, pos_or_kwd, *, kwd1, kwd2):
```

说明：

- 使用仅限位置形参，可以让用户无法使用形参名。形参名没有实际意义时，强制调用函数的实参顺序时，或同时接收位置形参和关键字时，这种方式很有用。

- 当形参名有实际意义，且显式名称可以让函数定义更易理解时，阻止用户依赖传递实参的位置时，才使用关键字

- 对于 API，使用仅限位置形参，可以防止未来修改形参名时造成破坏性的 API 变动。

### 任意实参列表

调用函数时，使用任意数量的实参是最少见的选项。这些实参包含在元组中（详见 元组和序列 ）。在可变数量的实参之前，可能有若干个普通参数：

