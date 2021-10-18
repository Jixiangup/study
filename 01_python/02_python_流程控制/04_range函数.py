for i in range(10):
    print(i, end=" ")

print("\n-----------------------")

for i in range(5, 10):
    print(i, end=" ") # 5 6 7 8 9 包前不包后原则

print("\n-----------------------")

print(list(range(0, 10, 3))) # 0 3 6 9 从0到10中获取数值，并且每次都会加3

print("\n-----------------------")

print(list(range(-10, -100, -30))) # [-10, -40, -70]
