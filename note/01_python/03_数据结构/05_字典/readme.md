# 字典

字典 （参见 映射类型 --- dict） 也是一种常用的 Python 內置数据类型。其他语言可能把字典称为 联合内存 或 联合数组。与以连续整数为索引的序列不同，字典以 关键字 为索引，关键字通常是字符串或数字，也可以是其他任意不可变类型。只包含字符串、数字、元组的元组，也可以用作关键字。但如果元组直接或间接地包含了可变对象，就不能用作关键字。列表不能当关键字，因为列表可以用索引、切片、append() 、extend() 等方法修改。

可以把字典理解为 键值对 的集合，但字典的键必须是唯一的。花括号 {} 用于创建空字典。另一种初始化字典的方式是，在花括号里输入逗号分隔的键值对，这也是字典的输出方式。

字典的主要用途是通过关键字存储、提取值。用 del 可以删除键值对。用已存在的关键字存储值，与该关键字关联的旧值会被取代。通过不存在的键提取值，则会报错。

对字典执行 list(d) 操作，返回该字典中所有键的列表，按插入次序排列（如需排序，请使用 sorted(d)）。检查字典里是否存在某个键，使用关键字 in。

以下是一些字典的简单示例：

```python
tel = {'jack': 4098, 'sape': 4139}
tel['guido'] = 4127
print(tel) # {'jack': 4098, 'sape': 4139, 'guido': 4127}

print(tel['jack']) # 4098

del tel['sape']
print(tel) # {'jack': 4098, 'guido': 4127}

tel['irv'] = 4127
print(tel) # {'jack': 4098, 'guido': 4127, 'irv': 4127}

# 将字典的所有key集合到一个数组中
print(list(tel)) # ['jack', 'guido', 'irv']

# 将字典中的所有key通过字典排序集合到一个数组中
print(sorted(tel)) # ['guido', 'irv', 'jack']

# 判断指定key是否存在字典对象中的key中存在
print('guido' in tel) # True
# 判断指定key是否存在字典对象中的key中不存在
print('jack' not in tel) # False
```

[dict()](https://docs.python.org/zh-cn/3/library/stdtypes.html#dict) 构造函数可以直接用键值对序列创建字典：

```python
>>> dict([('sape', 4139), ('guido', 4127), ('jack', 4098)])
{'sape': 4139, 'guido': 4127, 'jack': 4098}
```

字典推导式可以用任意键值表达式创建字典：

```python
>>> {x: x**2 for x in (2, 4, 6)}
{2: 4, 4: 16, 6: 36}
```

关键字是比较简单的字符串时，直接用关键字参数指定键值对更便捷：

```python
>>> dict(sape=4139, guido=4127, jack=4098)
{'sape': 4139, 'guido': 4127, 'jack': 4098}
```