# 更复杂的输出格式

至此，我们已经学习了两种写入值的方法：`表达式语句`和[print()](https://docs.python.org/zh-cn/3/library/functions.html#print)函数。第三种方法是使用文件对象的`write()`方法；标准输出文件称为`sys.stdout`。

对输出格式的控制不只是打印空格分隔的值，还需要更多方式。格式化输出包括以下几种方法。

- 使用[格式化字符串字面值](https://docs.python.org/zh-cn/3/tutorial/inputoutput.html#tut-f-strings)，要在字符串开头的引号/三引号前添加`f`或`F`。在这种字符串中可以在`{`和`}`字符之间输入引用的变量，或字面值的python表达式

    ```python
    year = 2022
    event = 'Referendum'
    print(f'Results of the {year} {event}') # Results of the 2022 Referendum
    ```

- 字符串的[str.format()](https://docs.python.org/zh-cn/3/library/stdtypes.html#str.format)方法需要更多手动操作。该方法也用`{`和`}`标记替换变量的位置，虽然这种方法支持详细的格式化指令，但需要提供格式化信息。

    ```python
    yes_votes = 42_572_654
    no_votes = 43_132_495
    percentage = yes_votes / (yes_votes + no_votes)
    print('{:-9} YES votes  {:2.2%}'.format(yes_votes, percentage)) 
    # 42572654 YES votes  49.67%
    ```

    > `{:-9}`: 其中`{:}`表示占位符，右边的值表示输出形式不会对结果产生影响 如这里我猜测是最大显示9位

- 最后，还可以用字符串切片和合并操作完成字符串处理操作，创建任何排版布局。字符串类型还支持将字符串按给定列宽进行填充，这些方法也很有用。

如果不需要花哨的输出，只想快速显示变量进行调试，可以用[repr()](https://docs.python.org/zh-cn/3/library/functions.html#repr)或[str()](https://docs.python.org/zh-cn/3/library/stdtypes.html#str)函数把值转化为字符串。

[str()](https://docs.python.org/zh-cn/3/library/stdtypes.html#str)函数返回供人阅读的值，[repr()](https://docs.python.org/zh-cn/3/library/functions.html#repr)则生成适于解释器读取的值（如果没有等效的语法，则强制执行[SyntaxError](https://docs.python.org/zh-cn/3/library/exceptions.html#SyntaxError)）。对于没有支持供人阅读展示的对象，[str()](https://docs.python.org/zh-cn/3/library/stdtypes.html#str)返回与[repr()](https://docs.python.org/zh-cn/3/library/functions.html#repr)相同的值。一般情况下，数字、列表或字典等数据结构的值，使用这两个函数输出的表现形式是一样的。字符串有两种不同的表现形式。

示例如下：

```python
s = 'Hello world.'
print(str(s)) # Hello world.

print(repr(s)) # 'Hello world.'

print(1/7) # 0.14285714285714285

x = 10 * 3.25
y = 200 * 200
s = 'The value of x is ' + repr(x) + ', and y is ' + repr(y) + '...'
print(s) # The value of x is 32.5, and y is 40000...

hello = 'hello, world\n'
hellos = repr(hello)
print(hellos) # hello, world\n

# repr() 的参数可以是任何 Python 对象
repr((x, y, ('spam', 'eggs'))) # (32.5, 40000, ('spam', 'eggs'))
```

[string](https://docs.python.org/zh-cn/3/library/string.html#module-string)模块包含[Template](https://docs.python.org/zh-cn/3/library/string.html#string.Template)类，提供了将值替换为字符串的另一种方法。该类使用`$x`占位符，并用字典的值进行替换，但对格式控制的支持比较有限

## [格式化字符串字面值](./01_%E6%A0%BC%E5%BC%8F%E5%8C%96%E5%AD%97%E7%AC%A6%E4%B8%B2%E5%AD%97%E9%9D%A2%E5%80%BC)

## [字符串format()方法](./02_%E5%AD%97%E7%AC%A6%E4%B8%B2format()%E6%96%B9%E6%B3%95/)