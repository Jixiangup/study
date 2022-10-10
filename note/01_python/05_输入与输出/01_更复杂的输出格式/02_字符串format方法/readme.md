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

