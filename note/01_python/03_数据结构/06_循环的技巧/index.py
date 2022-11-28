import math


knights = {'gallahad': 'the pure', 'robin': 'the brave'}

for key, value in knights.items():
    print(f'current key: {key}')
    print(f'current value: {value}')

for index, value in enumerate(['tic', 'tac', 'toe']):
    print(f'current item index: {index} and current item value: {value}')

questions = ['name', 'quest', 'favorite color']
answers = ['lancelot', 'the holy grail', 'blue']

for q, a in zip(questions, answers): 
    print('What is your {0}?  It is {1}.'.format(q, a))

for i in reversed(range(1, 10, 2)):
    print(i)

basket = ['apple', 'orange', 'apple', 'pear', 'orange', 'banana']
new_basket = []
for item in sorted(set(basket)):
    new_basket.append(item)
print(new_basket)

raw_data = [56.2, float('NaN'), 51.7, 55.3, 52.5, float('NaN'), 47.8]
filtered_data = []
for value in raw_data:
    # 如果不为nan
    if not math.isnan(value):
        filtered_data.append(value)
print(filtered_data)