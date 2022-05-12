import UIKit
import Darwin

// for 语法：for item in arrays { ... }
var arr: [Int] = [1, 2, 3, 4, 5]
for _ in arr {
//    print(item)
}

/*
 if
 语法：if $term { ... } else if $term { ... } else { ... }
 备注：条件后面的`{}`是不可以省略的
 */
for item in arr {
    if item % 2 == 0 {
//        print("被整除了\(item)") // 2 4
    } else if item == 1 {
//        print("这是1\(item)") // 1
    } else {
//        print("这是其他的数据\(item)") // 5
    }
}

// 创建空值变量 vat $name: TYPE? 如：var nilVar: Int? 或者。var nilVar: Int? = nil
var nilVar: Int?

if nilVar == nil {
//    print("nilVar是一个空值 >> \(nilVar)")
}

// ?问好运算符 "Hi \(nickname ?? fullName)" 选择一个不为空的变量，如果都不为空则默认选择前者，如果 都为空则拼接`字符串默认值""`，这与·Java中的new String();`类似
let nickname: String? = nil
let fullName: String = "John Appleseed"
let informalGreeting = "Hi \(nickname ?? fullName)"
//print(informalGreeting)

// 在swift中使用`==`就可以比较两个值是否相等尽管是字符串，与Java不同的是`Java会比较对象内存地址`
var name = "bnyte"
var password = "bnyte"
//print("密码是否相同\(name == password)") // true

/*
 switch:
    - 必须包含default否则无法通过编译
    - 可以不需要编写break，swift执行一个case之后直接会跳出，而不会继续执行下一个case
    - 可以同时case多个值，多个值之间使用`,`区分
  */
var count: Int = 10
switch count {
    case 1:
        count += 1
    case 2, 3, 4:
        count += 1
    case 5:
        count += 1
    default:
        count += 1
}

/*
 while:
    语法：while ¥term { ... }
    备注：与Java中的`while`没有过多区别
 repeat ... while ...
    语法：repeat {} while $term
    备注：这样的while至少会执行一次，他是先执行在判断，与Java中的`do...while...`相同
 */

while count > 0 {
//    print(count)
    count -= 1
}

count = 10

repeat {
//    print(count)
    count -= 1
} while count > 0

count = 10

/*
 `$start...$end`、`$start..<$end`
 语法：`0...10`从0到10++ 包前且包后，start和end可以是变量
 语法：`0..10`从0到10++，包前但是不包后，start和end可以是变量
 */
for i in 1..<count {
    print(i)
}
