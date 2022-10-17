# 读写文件

[open()](https://docs.python.org/zh-cn/3/library/functions.html#open)返回一个[file object](https://docs.python.org/zh-cn/3/glossary.html#term-file-object)，最常使用的是两个位置参数和一个关键字参数: `open(filename, mode, encoding=None)`

```python
readme_file = open('readme.md', 'r+', encoding='utf-8')
```

第一个实参是文件名字符串。第二个实参是包含描述文件使用方式的字符串。`mode`的值包括`'r'`，表示文件只能读取；`'w'`表示只能写入（现有同名文件会被覆盖）；`'a'`表示打开文件并追加内容，任何写入的数据会自动添加到文件末尾。`'r+'`表示打开文件进行读写。`mode`实参是可选的，省略时的默认值为`'r'`。

通常情况下，文件是以`text mode`打开的，也就是说，你从文件中读写字符串，这些字符串是以特定的`encoding`编码的。如果没有指定`encoding`，默认的是与平台有关的（见[open()](https://docs.python.org/zh-cn/3/library/functions.html#open)返回一个[file object](https://docs.python.org/zh-cn/3/glossary.html#term-file-object)）。因为`UTF-8`是现代事实上的标准，除非你知道你需要使用一个不同的编码，否则建议使用`encoding="utf-8"`。在模式后面加上一个`'b'`,可以用`binary mode`打开文件。二进制模式的数据以[bytes](https://docs.python.org/zh-cn/3/library/stdtypes.html#bytes)对象的形式读写的。在二进制模式下打开文件时，你不能指定`encoding`

在文本模式下读取文件时，默认吧平台特定的行结束符（Unix上为`\n`，Windows上为`\r\n`转换为`\n`。在文本模式下写入数据时，默认吧`\n`转换回平台特定结束符。这种操作方式在后台修改文件数据对文本文件来说没有问题，但会破坏`JPEG`或`EXE`等二进制文件中的数据。注意，在读写此类文件时，一定要使用二进制模式。

在处理文件对象时，最好使用[with](https://docs.python.org/zh-cn/3/reference/compound_stmts.html#with)关键字。优点是，子句体结束后，文件会正确关闭，即便触发异常也可以。而且使用with相比等效的`[try](https://docs.python.org/zh-cn/3/reference/compound_stmts.html#try)-[finally](https://docs.python.org/zh-cn/3/reference/compound_stmts.html#finally)`

```python
readme_file = open('/Users/bnyte/workspaces/tome/backend/study/note/01_python/05_输入与输出/02_读写文件/readme.md', 'r+', encoding='utf-8')
with (readme_file):
    read_data = readme_file.readline()
    print(read_data)

# 我们可以检查该文件是否已自动关闭。
print(readme_file.closed)
```

如果没有使用[with](https://docs.python.org/zh-cn/3/reference/compound_stmts.html#with)关键字，则应调用`readme_file.close()`关闭该文件，则可释放文件占用的系统资源。

> 警告：调用`f.write()`时，未使用`with`关键字，或未调用`f.close()`，即使程序正常退出，也可能导致`f.write()`的参数没有完全写入磁盘。

通过[with](https://docs.python.org/zh-cn/3/reference/compound_stmts.html#with)语句，或调用`f.close()`关闭文件对象后，再次使用该文件对象将会失败。

```python
f.close()
f.read()
Traceback (most recent call last):
  File "<stdin>", line 1, in <module>
ValueError: I/O operation on closed file.
```

## [文件对象的方法](./01_%E6%96%87%E4%BB%B6%E5%AF%B9%E8%B1%A1%E7%9A%84%E6%96%B9%E6%B3%95/)

