t = 1, 2, 3, 4, 5
print(t)

# 取元组中的第一条数据
print(t[0])

# 不会合成一个而是两个元组
u = t, (6, 7, 8, 9, 10)
print(u)

# t[0] = 0报错，元祖是不可变的，不能通过索引修改值只能读
# t[0] = 0

empty = ()
singleton = 'hello',
print(len(empty))
# 0
print(len(singleton))
# 1

t = 1, 2, 3
a, b, c = t
print(f"a: {a}, b: {b}, c: {c}")