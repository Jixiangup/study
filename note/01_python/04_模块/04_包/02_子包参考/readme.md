# 子包参考

包中含有多个子包时（与示例中的sound包一样），可以使用绝对导入引用兄弟包中的子模块。例如：要在`sound.filters.vocoder`中使用`sound.effects`包的`echo`模块时，可以用`from sound.effects import echo`导入。

还可以用import语句的`from module import name`形式执行相对导入。这些导入语句使用前导句点表示相对导入中的当前包和父包。例如，相对于surround模块，可以使用

```python
from . import echo
from .. import formats
from ..filters import equalizer
```

注意，相对导入基于当前模块名，因为主模块名时`"__main__"`，所以Python程序的主模块必须始终使用绝对导入。