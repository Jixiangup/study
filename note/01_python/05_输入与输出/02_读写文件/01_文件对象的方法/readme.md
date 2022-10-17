# 文件对象的方法

本节下文中的例子假定已创建`f`文件对象。

`f.read(size)`可用于读取文件内容，他会读取一些数据，并返回字符串（文本模式），或字符串对象（在二进制模式下）。`size`是可选的数值参数。省略`size`或`size`为负数时，读取并返回整个文件的内容；文件大小时内存的两倍时，会出现问题。`size`取其他值时，读取并返回最多`size`个字符（文本模式）或`size`个字节（二进制模式）。如已到达文件末尾，`f.read()`返回空字符串(`''`)。

```python
f.read()
'This is the entire file.\n'
f.read()
''
```

`f.readline()`是从文件中读取单行数据；字符串末尾保留换行符（`\n`），只有在文件不以换行结尾时，文件的最后一行才会省略换行符。这种方式让返回值清晰明确；只要`f.readline()`返回空字符串，就表示已经到达了文件末尾，空行使用`\n`表示，该字符串只包含一个换行符。

```python
f.readline()
'This is the first line of the file.\n'
f.readline()
'Second line of the file\n'
f.readline()
''
```

从文件中读取多行时，可以用循环遍历整个文件对象。这种操作能高效利用内存，快速，且代码简单：

```python
for line in f:
    print(line, end='')
# This is the first line of the file.
# Second line of the file
```

如需以列表形式读取文件中的所有行，可以用`list(f)`或`f.readlines()`。

`f.write(string)`吧string的内容写入文件，并返回写入的字符数。

```python
f.write('This is a test\n')
15
```

写入其他类型的对象前，要先把它们转化为字符串（文本模式）或字节对象（二进制模式）：

```python
value = ('the answer', 42)
s = str(value)  # convert the tuple to string
f.write(s)
# 18
```

`file.tell()`返回证书，给出文件对象在文件中的当前位置，表示为二进制模式下时从文件来时的字节数，以及文本模式下的意义不明的数字。

`f.seek(offset, whence)`可以改变文件对象的位置。通过向参考点添加 offset 计算位置；参考点由 whence 参数指定。 whence 值为 0 时，表示从文件开头计算，1 表示使用当前文件位置，2 表示使用文件末尾作为参考点。省略 whence 时，其默认值为 0，即使用文件开头作为参考点。

```python
f = open('workfile', 'rb+')
f.write(b'0123456789abcdef')
# 16
f.seek(5)      # Go to the 6th byte in the file
# 5
f.read(1)
# b'5'
f.seek(-3, 2)  # Go to the 3rd byte before the end
# 13
f.read(1)
# b'd'
```

在文本文件（模式字符串未使用 `b` 时打开的文件）中，只允许相对于文件开头搜索（使用 `seek(0, 2)` 搜索到文件末尾是个例外），唯一有效的 offset 值是能从 `f.tell()` 中返回的，或 0。其他 offset 值都会产生未定义的行为。

文件对象还支持 isatty() 和 truncate() 等方法，但不常用；文件对象的完整指南详见库参考。