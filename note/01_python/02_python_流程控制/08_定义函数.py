from tokenize import Number

# 直接输出的斐波那契数列
def fib(n: Number):
    a, b = 0, 1
    while n > a:
        print(a, end=' ')
        a, b = b, a+b
    print()

fib(14)

# 将函数赋值给变量
define_fun = fib
define_fun(14)

# 输出函数的返回值 (如果该函数没有返回值则会返回：None)
print(define_fun(14))

# 将斐波那契数列的结果收集起来并且返回
def fibWithResult(n):
    result = []
    a, b = 0, 1
    while n > a:
        result.append(a)
        a, b = b, a + b
    return result

print(fibWithResult(14))