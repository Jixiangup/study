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