year = 2022
event = 'Referendum'
print(f'Results of the {year} {event}') # Results of the 2022 Referendum

yes_votes = 42_572_654
no_votes = 43_132_495
percentage = yes_votes / (yes_votes + no_votes)
print('{:-9} YES votes  {:2.3%}'.format(yes_votes, percentage)) # 42572654 YES votes  49.67%

s = 'Hello world.'
print(str(s)) # Hello world.

print(repr(s)) # 'Hello world.'

print(1/7) # 0.14285714285714285

x = 10 * 3.25
y = 200 * 200
s = 'The value of x is ' + repr(x) + ', and y is ' + repr(y) + '...'
print(s) # The value of x is 32.5, and y is 40000...

hello = 'hello, world\n'
hellos = repr(hello)
print(hellos) # hello, world\n

# repr() 的参数可以是任何 Python 对象
repr((x, y, ('spam', 'eggs'))) # (32.5, 40000, ('spam', 'eggs'))