# 以脚本方式执行模块

可以用以下方式运行Python模块：

```python
python fibo.py <arguments>
```

这项操作将执行模块里的代码，和导入模块一样，但会把`__name__`赋值为`"__main__"`。也就是把下列代码添加到模块末尾：

```python
if __name__ == "__main__":
    import sys
    fibo(int(sys.argv[1]))
```

既可以把这个文件当脚本使用，也可以用作导入的模块，因为解释命令行的代码只有在模块以`main`文件执行时才会运行

```python
python fibo.py 14
# 0 1 1 2 3 5 8 13
```

导入模块时，不运行这些代码：

```python
import fibo
```

这种操作常用语模块提供便捷用户接口，或用于测试（把模块当作执行测试套件的脚本运行）。