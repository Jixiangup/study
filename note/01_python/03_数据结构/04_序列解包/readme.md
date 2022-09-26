# 集合

`Python` 还支持 `集合` 这种数据类型。集合是由不重复元素组成的无序容器。基本用法包括成员检测、消除重复元素。集合对象支持合集、交集、差集、对称查分等数学运算。

创建集合用花括号或`set()`函数。注意创建空集合只能用`set()`，不能用`{}`，`{}`创建的是空`字典`

以下是一些简单的示例

```python
basket = {'apple', 'orange', 'apple', 'pear', 'orange', 'banana'}
print(basket) # {'apple', 'orange', 'apple', 'pear', 'orange', 'banana'}

print('orange' in basket) # True

print('origin' in basket) # False

a = set('abracadabra')
b = set('alacazam')
print(a) # {'a', 'b', 'c', 'r', 'd'}
print(a) # {'l', 'a', 'c', 'z', 'm'}

print(a - b) # {'b', 'r', 'd'}

print(a | b) # {'r', 'm', 'c', 'l', 'b', 'd', 'a', 'z'}

print(a & b) # {'a', 'c'}

print(a ^ b) # {'d', 'l', 'r', 'z', 'b', 'm'}
```

与 列表推导式 类似，集合也支持推导式

```python
>>> a = {x for x in 'abracadabra' if x not in 'abc'}
>>> a
{'r', 'd'}
```