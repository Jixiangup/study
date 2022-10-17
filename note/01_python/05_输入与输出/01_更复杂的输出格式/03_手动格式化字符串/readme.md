# 手动格式化字符串

下面是使用手动格式化方式实现同一个平方和立方的表：

```python
for x in range(1, 11):
    print(repr(x).rjust(2), repr(x * x).rjust(3), end = ' ')
    print(repr(x * x * x).rjust(4))

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

(注意，每列之间的空格是通过使用[print()](https://docs.python.org/zh-cn/3/library/functions.html#print)添加的：它总在其参数间添加空格。)

字符串对象的[str.rjust()](https://docs.python.org/zh-cn/3/library/stdtypes.html#str.rjust)方法通过在左侧填充空格，对给定宽度字段中的字符串进行右对齐。同类方法还有[str.ljust()](https://docs.python.org/zh-cn/3/library/stdtypes.html#str.ljust)和[str.center()](https://docs.python.org/zh-cn/3/library/stdtypes.html#str.center)。这些方法不写入任何内容，只返回一个新字符串，如果输入的字符串太长，它们不会截断字符串，而是原样返回；虽然这种方式会弄乱布局，但也比另一种方法好，后者在显示时可能不准确（如果真的想截断字符串，可以使用`x.ljust(n)[]:n`这样的切片操作。）

另一种方法是[str.zfill()](https://docs.python.org/zh-cn/3/library/stdtypes.html#str.zfill)，该方法在数字字符串左边填充零，切能识别正负号：

```python
'12'.zfill(5) # '00012'
'-3.14'.zfill(7) # '-003.14'
'3.14159265359'.zfill(5) # '3.14159265359'
```