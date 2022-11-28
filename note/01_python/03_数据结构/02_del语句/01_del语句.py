a = [-1, 1, 66.25, 333, 333, 1234.5]
# 删除索引为0的数据
del a[0]
print(a)

# 包前不包后
del a[2:4]
print(a)

# 清空列表
del a[:]
print(a)
