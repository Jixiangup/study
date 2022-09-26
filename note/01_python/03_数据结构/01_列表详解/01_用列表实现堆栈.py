list = [1, 2, 3, 4]
# 末尾添加元素
list.append(5)

print(list)

list_extend = []
list_extend.extend(list)
print(list_extend)

list.insert(len(list), 6)
print(list)

list.remove(6)
print(list)

list.pop()
print(list)

list.pop(len(list) - 1)
print(list)

print(list.index(2, 0, 2))

print(list.count(2))

list.clear()
print(list)