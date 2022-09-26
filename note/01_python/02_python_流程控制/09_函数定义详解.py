def ask_ok(prompt, retries=4, reminder='Please try again!'):
    while True:
        ok = input(prompt)
        if ok in ('y', 'ye', 'yes'):
            return True
        if ok in ('n', 'no', 'nop', 'nope'):
            return False
        retries = retries - 1
        if retries < 0:
            raise ValueError('invalid user response')
        print(reminder)

ask_ok("Do you really want to quit?")

i = 5

def f(arg=i):
    print(arg)

i = 6
# 输出5
f() 

def f1(a, L=[]):
    L.append(a)
    return L

print(f1(1))
print(f1(2))
print(f1(3))

# 关键字参数
def parrot(voltage, state='a stiff', action='voom', type='Norwegian Blue'):
    print("-- This parrot wouldn't", action, end=' ')
    print("if you put", voltage, "volts through it.")
    print("-- Lovely plumage, the", type)
    print("-- It's", state, "!")
parrot(state='ggboy', voltage='eeboy')

print("*" * 40 + "\n")

def cheese_shop(kind, *args, **keyvalues):
    print(f"指定参数kind的值是: {kind}")
    print("-" * 40)
    for arg in args:
        print(f"获取到的集合参数列表值是: {arg}")
    print("-" * 40)
    for key in keyvalues:
        print(f"货渠道的key=value中的键是: {key}, 值是: {keyvalues[key]}")
    
cheese_shop("ggboy",
            "param1",
            "param2",
            "param3",
            username="ggboy1",
            password="ggboy2")

print("*" * 40 + "\n")