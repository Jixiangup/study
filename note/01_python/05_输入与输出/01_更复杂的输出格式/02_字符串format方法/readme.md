# 字符串format()方法

[str.format()](https://docs.python.org/zh-cn/3/library/stdtypes.html#str.format)方法的基本用法如下

```python
print('we are the {} who say "{}!"'.format('kinights', 'Ni'))
# we are the kinights who say "Ni!"
```

花括号及之内的字符（称为格式字段）被替换为传递给[str.format()](https://docs.python.org/zh-cn/3/library/stdtypes.html#str.format)方法的对象。花括号中的数字表示传递给[str.format()](https://docs.python.org/zh-cn/3/library/stdtypes.html#str.format)方法的对象所在位置。

```python
print('{0} and {1}'.format('spam', 'eggs'))
# spam and eggs
print('{1} and {0}'.format('spam', 'eggs'))
# eggs and spam
```

[str.format()](https://docs.python.org/zh-cn/3/library/stdtypes.html#str.format)方法中使用关键字参数名引用值

```python
print('this {food} is {adjective}.'.format(food = 'spam', adjective = 'absolutely horrible'))
# this spam is absolutely horrible.
```

位置参数和关键字参数可以任意组合

```python
print('The story of {0}, {1}, and {other}.'.format('Bill', 'Manfred',other='Georg'))
# The story of Bill, Manfred, and Georg.
```

如果不想分拆较长的格式字符串，最好按名称引用变量进行格式化，不要按位置。这项操作可以通过传递字典，并用方括号`'[]'`访问键来完成。

```python
table = {'Sjoerd': 4127, 'Jack': 4098, 'Dcab': 8637678}
print('Jack: {0[Jack]:d}; Sjoerd: {0[Sjoerd]:}; '
      'Dcab: {0[Dcab]:d}'.format(table))
Jack: 4098; Sjoerd: 4127; Dcab: 8637678
```

同时也可以通过将`table`字典作为关键字传递给`**`符号来完成。

```python
table = {'Sjoerd': 4127, 'Jack': 4098, 'Dcab': 8637678}
print('Jack: {Jack:d}; Sjoerd: {Sjoerd:d}; Dcab: {Dcab:d}'.format(**table))
Jack: 4098; Sjoerd: 4127; Dcab: 8637678
```

与内置函数[vars()](https://docs.python.org/zh-cn/3/library/functions.html#vars)结合使用时，这种方式非常实用，可以返回包含所有局部变量的字典。

例如，以下生成一组整齐排列的列，给出整数及其平方和立方体。

```python
for x in range(1, 11):
    print('{0:2d} {1:3d} {2:4d}'.format(x, x * x, x * x * x))
1   1    1
2   4    8
3   9   27
4  16   64
5  25  125
6  36  216
7  49  343
8  64  512
9  81  729
10 100 1000
```

[str.format()](https://docs.python.org/zh-cn/3/library/stdtypes.html#str.format)进行字符串格式化的完整概述详见[格式字符串语法](https://docs.python.org/zh-cn/3/library/string.html#formatstrings)