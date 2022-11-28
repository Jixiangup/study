# 旧字符串格式化方法

`%`运算符（求余符）也可用于字符串格式化。给定`'string' % values`，则`string`中的`%`实例会以零个或多个`values`元素替换。此操作被称为字符串插值。例如：

```python
import math
print('The value of pi is approximately %5.3f.' % math.pi)

# The value of pi is approximately 3.142.
```

[printf 风格的字符串格式化](https://docs.python.org/zh-cn/3/library/stdtypes.html#old-string-formatting)小节介绍更多相关内容。