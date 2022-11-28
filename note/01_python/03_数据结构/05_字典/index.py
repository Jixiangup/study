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

# {'sape': 4139, 'guido': 4127, 'jack': 4098}
print(dict([('sape', 4139), ('guido', 4127), ('jack', 4098)]))