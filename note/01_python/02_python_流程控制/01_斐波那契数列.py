a, b = 0, 1
while a < 10:
    print(a, end=" ") # end=" "主要用于表达在a变量输出之后，结尾用 " " 类似于字符串append
    a, b = b, a + b
