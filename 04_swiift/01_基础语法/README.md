# 基础数据类型

1. 在swift中可以不用写`;`但是如果你手动给予了，这并不会报错
2. 是隐式创建变量，也就是说系统自动识别了数据类型

## 声明变量

- `let`：用于生成常量

- `var`：用于生成变量

### 隐式声明

```
let count = 1 
var age = 18 
var name = "bnyte"
var password = "bnyte.."
//print(count) // 1
//print(age) // 18
//print(name) // bnyte
// count = 2 报错：Cannot assign to value: 'count' is a 'let' constant
age = 20
//print(age) // 正常运行

let price = 1.8;
//print(price)
```

### 显示声明

```
let amount: Double = 70.1
//print(amount + price)

// print(name + amount) 报错Binary operator '+' cannot be applied to operands of type 'String' and 'Double'

// print(price + age) 报错Binary operator '+' cannot be applied to operands of type 'Double' and 'Int'

//print(name + password) // bnytebnyte..

var loginInfo = "username: \(name), password: \(password)"
//print(loginInfo) // username: bnyte, password: bnyte..

var longString = """
      \(name)
    床前明月光，
    疑是地上霜。
    "举头望明月"，
    低头思故乡。
"""
//print(longString) // 并不会忽略字符串中的换行及空格，并且内部的字符串也不需要特殊的转义等。
```

### 总结

- `print(amount + price) 71.89999999999999`
需要注意的是在swift中的浮点型依然存在精度丢失问题，但是当使用显示数据类型隐式数据类型去做数据的运算并不会报错，这是可以存在的
- print(name + amount) 报错（Binary operator '+' cannot be applied to operands of type 'String' and 'Double'）
- print(price + age) 报错（Binary operator '+' cannot be applied to operands of type 'Double' and 'Int'
总结3.2、3.3：也就是说在swift中，二元运算符不能作用于不同的数据类型
- print(name + password) // bnytebnyte.. 和Java一样，相同类型是可以进行运算的，如果是字符串则会拼串，而如果是数值类型则会进行数学运算
- `字符串拼接`功能：当需要使用字符串以及变量数据进行拼接时，可以直接使用反斜线加括号（`\()`）的方式进行拼接，括号中可以直接输入变量名，这样就可以完成字符串拼接，并不会引起运算出现的问题
- 多行字符串：
    ```
    语法："""字符串内容体"""
    备注：并不会忽略字符串中的换行及空格，并且内部的字符串也不需要特殊的转义等。
    总结：也就是说在这语法之中的所有字符串都将会被作为一个完整的字符串输出，并且不会忽略掉任何数据，并且是不需要对特殊字符进行转译的，同时在这里面依然可以使用字符串拼接功能
    ```

## 数组

### 字符串数组
```
var strArray = ["b", "n", "y", "t", "e",]
//print(strArray) // ["b", "n", "y", "t", "e"]
//print(strArray[0]) // 获取`索引0`的数据
```

### 对象、map数组
```
var objArray = [
    "username": "bnyte",
    "password": "ggboy"
]
//print(objArray) // ["username": "bnyte", "password": "ggboy"]
//print(objArray["username"]) // 输出key为username的数组值  Optional("bnyte")
objArray["gender"] = "男生"
//objArray["age"] = 18 // 报错 Cannot assign value of type 'Int' to type 'String?'
//print(objArray) // ["username": "bnyte", "password": "ggboy", "gender": "男生"] 在素组中可以自动扩容
strArray.append("大帅哥")
//print(strArray) // ["b", "n", "y", "t", "e", "大帅哥"]
```

### 创建空基础类型数组、对象/map类型数组

```
var emptyArray: [String] = [] // 规定值为字符串
var emptyMap: [String: Float] = [:] // 规定key为字符串，value为浮点型

//print(emptyMap) // [:]
//print(emptyArray) // []
var doubleArray: [Float] = []
//doubleArray.append(price) // 报错 Cannot convert value of type 'Double' to expected argument type 'Float'
print(doubleArray)
```
### 总结

> 语法：var $param = [$value, $value]
> 
> 备注：使用`[]`来创建一个数组，每个元素使用`,`分隔，和`Java数组`相同，都是通过$obj[index]获取下标索引
> 
> 总结：
> 1. 语法比较松散，并且语法松散，允许最后一个元素的后面存在或不存在`,`
> 2. 和`Java数组`相同，都是通过$obj[index]获取下标索引
> 3. $obj["$key"] = "$value"使用这种方式可以继续为对象、map类型数组添加数据，并且会自动扩容，如果值key已经存在则会覆盖已经存在的值
> 4. $obj.append($value)，使用这种方式可以为基础数据类型数组添加值，并且会自动扩容
> 5. 即使没有显示声明基础数据类型的数据，但是swift依然会自动识别，即使是double数组也不能添加float数据 如：字符串数组不能添加一个浮点型的数据
