# 搭建python开发环境

- 直接进入python下载3.x之后的版本即可
- 唤出`CMD`输入`py`或`python`进入python的编译器, 如显示python详细信息则表示安装成功, 退出输入`quit()`

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
```
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

可用于语法需要时但是是一个空的字段，如下创建一个最简单的类

```
class MyEmptyClass:
    pass
```