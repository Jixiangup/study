[格式化字符串字面值](https://docs.python.org/zh-cn/3/reference/lexical_analysis.html#f-strings)（简称为f-字符串）在字符串前加前缀`f`或`F`，通过`{expression}`表达式，把python表达式的值天价到字符串内。

格式说明符是可选的，写在表达式后面，可以更好的控制格式化值的方式。下列将`PI`舍入到小数点后三位：

```python
import math


print(f'The value of pi is approximately {math.pi:.3f}.') # The value of pi is approximately 3.142.
```

在`':'`后传递证书，为该字段设置最小字符宽度，常用与列对齐：

```python
table = {'Sjoerd': 4127, 'Jack': 4098, 'Dcab': 7678}
for name, phone in table.items():
    print(f'{name:10} ==> {phone:10d}')
'''
Sjoerd     ==>       4127
Jack       ==>       4098
Dcab       ==>       7678
'''
```

还有一些修饰符可以在格式化前转换值。`'!a'`应用[ascii()](https://docs.python.org/zh-cn/3/library/functions.html#ascii)，`'!s'`应用[str()](https://docs.python.org/zh-cn/3/library/stdtypes.html#str)，`'!r'`应用[repr()](https://docs.python.org/zh-cn/3/library/functions.html#repr)

```python
animals = 'eels'
print(f'My hovercraft is full of {animals}.')
# My hovercraft is full of eels.
print(f'My hovercraft is full of {animals!r}.')
# My hovercraft is full of 'eels'.
```

说明`=`符可用于将表达式扩展为表达式的文本、等号，然后是计算表达式的表示

```python
bugs = 'roaches'
count = 13
area = 'living room'
print(f'Debugging {bugs=} {count=} {area=}')
# Debugging bugs='roaches' count=13 area='living room'
```

有关说明符的更多信息，请参阅[自记录表达式](https://docs.python.org/zh-cn/3/whatsnew/3.8.html#bpo-36817-whatsnew)`=`。有关这些格式规范的参考，请参阅[格式最小规格语言](https://docs.python.org/zh-cn/3/library/string.html#formatspec)的参考指南。