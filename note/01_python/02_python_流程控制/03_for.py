# 遍历普通集合
strs = ["windows", "mac", "linux"]
for str in strs:
    print(str, len(str), end=" ")

print("\n------------------------")

# 01-遍历对象集合
users = {
    "id": 100,
    "name": "猪猪侠",
    "age": 20
}

print("\n------------------------")

for user in users:
    print(user, end=" ") # 获取对象中的所有属性名
    print(users[user], end=" ") # 获取对象中对应属性名的属性值

print("\n------------------------")

# 02-遍历对象集合并删除集合中的某个元素
for key, value in users.copy().items():
    # print("key > ", key, ", value > ", value, end= " | ") # key >  id , value >  100 | key >  name , value >  猪猪侠 | key >  age , value >  20 |
    if value == 100:
        del users[key]
        print(users)

print("\n------------------------")