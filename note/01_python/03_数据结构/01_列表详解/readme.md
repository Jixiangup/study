# 列表详解

列表数据类型支持很多方法，列表对象的所有方法所示如下：

- append(element)

    在列表末尾添加一个元素，相当于`list[len(list):] = [element]`

- extend(iterable)

    用可迭代对象的元素扩展类表。相当于`list[len(list):] = iterable`

- insert(index, element)

    在指定位置插入元素。第一个参数是插入元素的索引，因此，`list.insert(0, element)`在列表开头插入元素，`list.insert(len(list), element)`等同于`list.append(element)`。

- remove(element)

    从列表中删除第一个值为`element`的元素。未找到元素时，出发`ValueError`异常。

- pop([index])

    删除列表中指定位置的元素，并返回被删除的元素。未指定位置时，`list.pop()`删除并返回列表的最后一个元素。（方法签名中的`index`两边的方括号表示该参数是可选的，不是要求输入方括号。这种表示法常见于`Python`参考库）

- clear()

    删除类表的所有元素，相当于`del list[:]`

- index(element[, start, [,end]])

    返回列表中第一个值为`element`的元素的零基索引。未找到指定元素时触发`ValueError`异常。
    可选参数`start`和`end`是切片符号，用于搜索限制为列表的特定子序列。返回的索引是相对于整个序列的开始计算的，而不是`start`参数

- count(element)

    返回卡类表中元素`element`出现的次数

- sort(*, key=None, reverse=False)

    就地排序列表中的元素(要了解自定义排序参数，详见 [sorted()](https://docs.python.org/zh-cn/3/library/functions.html#sorted))

- reverse()

    翻转列表中的元素

- copy()

    返回列表的浅拷贝。相当于a[:]