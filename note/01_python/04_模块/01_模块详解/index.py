# 导入模块
import fibo

# 调用模块中的方法
print(fibo.fibWithReturn(14))
fibo.fibWithPrint(14)

# 赋值模块中的方法并且调用
fibWithResult = fibo.fibWithReturn
print(fibWithResult(14))

# 打印模块名字
print(fibo.__name__) # fibo