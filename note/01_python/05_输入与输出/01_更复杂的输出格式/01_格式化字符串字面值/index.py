import math


print(f'The value of pi is approximately {math.pi:.3f}.')

table = {'Sjoerd': 4127, 'Jack': 4098, 'Dcab': 7678}
for name, phone in table.items():
    print(f'{name:10} ==> {phone:10d}')
'''
Sjoerd     ==>       4127
Jack       ==>       4098
Dcab       ==>       7678
'''

animals = 'eels'
print(f'My hovercraft is full of {animals}.')
# My hovercraft is full of eels.
print(f'My hovercraft is full of {animals!r}.')
# My hovercraft is full of 'eels'.

bugs = 'roaches'
count = 13
area = 'living room'
print(f'Debugging {bugs=} {count=} {area=}')
# Debugging bugs='roaches' count=13 area='living room'