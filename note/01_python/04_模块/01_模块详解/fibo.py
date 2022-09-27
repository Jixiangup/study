def fibWithPrint(n):
    a, b = 0, 1
    while a < n:
        print(a, end=' ')
        a, b = b, a + b
    print()

def fibWithReturn(n):
    a, b = 0, 1
    result = []
    while n > a:
        result.append(a)
        a, b = b, a + b
    return result