# Hive

> 文中例子基于Hive`3.1.2`, Hadoop`3.1.3`, jdk`1.8`版本撰写。

## 什么是hive

## 工作原理

# 安装和启动

## 安装

- 下载hive

[hive下载页](https://dlcdn.apache.org/hive/) 选择版本下载

如

```shell
curl https://dlcdn.apache.org/hive/hive-3.1.2/apache-hive-3.1.2-bin.tar.gz 
```

- 解压hive

```shell
tar -zxvf apache-hive-3.1.2-bin.tar.gz -C /opt/module
```

- 环境变量

> 配置`HIVE_HOME/bin`即可

### derby启动

> 注意启动时如果使用的`derby`作为源数据那么初始化命令和启动脚本命令必须在同一个目录下执行, 一般在`HIVE_HOME/bin`执行即可

```shell
# 初始化源数据
schematool -dbType derby -initSchema
# ls 之后可以看到metastore_db目录即为成功
# 启动hive客户端
hive
```

#### 测试

```shell
show databases;

# 输出以下为搭建成功
OK
default
Time taken: 0.574 seconds, Fetched: 1 row(s)

# 退出数据库客户端
exit;
```

### MySQL启动

> MySQL安装忽略~随便什么方式安装。hive能通信就行了

#### 配置

- 配置`${HIVE_HOME}/conf/hive-site.xml`

```xml
<?xml version="1.0"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>
<configuration>
    <!-- jdbc 连接的 URL -->
    <property>
        <name>javax.jdo.option.ConnectionURL</name>
        <value>jdbc:mysql://hadoop100:3306/hive?useSSL=false</value>
    </property>
    
    <!-- jdbc 连接的 Driver-->
    <property>
        <name>javax.jdo.option.ConnectionDriverName</name>
        <value>com.mysql.cj.jdbc.Driver</value>
    </property>
    
    <!-- jdbc 连接的 username-->
    <property>
        <name>javax.jdo.option.ConnectionUserName</name>
        <value>root</value>
    </property>
    
    <!-- jdbc 连接的 password -->
    <property>
        <name>javax.jdo.option.ConnectionPassword</name>
        <value>123456</value>
    </property>
    
    <!-- Hive 元数据存储版本的验证 -->
    <property>
        <name>hive.metastore.schema.verification</name>
        <value>false</value>
    </property>
    
    <!--元数据存储授权-->
    <property>
        <name>hive.metastore.event.db.notification.api.auth</name>
        <value>false</value>
    </property>
    
    <!-- Hive 默认在 HDFS 的工作目录 -->
    <property>
        <name>hive.metastore.warehouse.dir</name>
        <value>/user/hive/warehouse</value>
    </property>
    
    <!-- 指定存储元数据要连接的地址,如果不配置源数据访问则忽略改配置, 注释掉即可 -->
    <property>
        <name>hive.metastore.uris</name>
        <value>thrift://hadoop100:9083</value>
    </property>
    
    <!-- 指定 hiveserver2 连接的 host, 如果不配置jdbc链接hive忽略即可 -->
    <property>
        <name>hive.server2.thrift.bind.host</name>
        <value>hadoop100</value>
    </property>
    
    <!-- 指定 hiveserver2 连接的端口号, 如果不配置jdbc链接hive忽略即可 -->
    <property>
        <name>hive.server2.thrift.port</name>
        <value>10000</value>
    </property>
    
    <!-- 解决root启动无法使用jdbc远程连接问题, 如果不配置jdbc链接hive忽略即可 -->
    <property>
        <name>hive.server2.enable.doAs</name>
        <value>false</value>
        <description>
            Setting this property to true will have HiveServer2 execute
            Hive operations as the user making the calls to it.
        </description>
    </property>
    
    <!-- 指定标头, 如查询是会返回数据对应的那个字段 -->
    <property>
        <name>hive.cli.print.header</name>
        <value>true</value>
    </property>
    
    <!-- 是否在控制台显示当前所在db -->
    <property>
        <name>hive.cli.print.current.db</name>
        <value>true</value>
    </property>
</configuration>
```

- 初始化MySQL

```sql
create database hive;
```

> 这一行在hive的MySQL数据库没有被初始化的时候第一次执行，后面无需执行
```shell
 schematool -initSchema -dbType mysql -
verbose
```

#### 启动测试

```shell
# 启动
hive 
# 测试
show databases;
```

#### 配置源数据服务

```shell
# &表示后台启动
hive --service metastore &
```

#### 配置jdbc方式访问

```shell
# &表示后台启动
hive --service hiveserver2 &

# 测试jdbc链接
beeline -u jdbc:hive2://hadoop102:10000
```

#### 也可以使用其他的客户端链接测试


# 常用命令和配置

## -e

> 不进入hive交互界面执行hive语句

```shell
hive -e 'select * from user_db.t_user;'
```

## -f

> 执行脚本中的sql语句

- 创建脚本文件

```shell
mkdir -p /opt/workspaces/hive/sql
touch /opt/workspaces/hive/sql/hive-sql-00.sql
echo >> /opt/workspaces/hive/sql/hive-sql-00.sql
hive -f /opt/workspaces/hive/sql/hive-sql-00.sql
```

- 执行文件中的sql语句并将结果写入到文件中

```shell
mkdir -p /opt/output/hive
hive -f /opt/workspaces/hive/sql/hive-sql-00.sql > /opt/output/hive/hive-sql-00
```

## dfs -[operating]

> 在hive中使用(操作)hdfs

```shell
# 查看/下的路径
dfs -ls /
```

## hive历史命令

```shell
cd ~
cat .hivehistory
```

## hive属性配置

- 运行日志

```shell
# 默认log文件存放在/tmp/${username}/hive.log
cp ${HIVE_HOME}/conf/hive-log4j2-properties.template ${HIVE_HOME}/conf/hive-log4j2-properties
# 配置属性项
hive.log.dir=/opt/documentation/logs/hive/
```

- 打印当前库和表头

> 编辑hive-site.xml
```shell
    <property>
        <name>hive.cli.print.header</name>
        <value>true</value>
    </property>
    <property>
        <name>hive.cli.print.current.db</name>
        <value>true</value>
    </property>
```

## 参数配置方式

1. 查看当前所有的配置信息
```shell
set;
```
2. 参数的三种配置方式
```markdown
# 文件配置方式

> 用户自定义配置会覆盖默认配置, 另外Hive也会读入Hadoop的配置, 因为Hive是作为Hadoop的客户端启动的, Hive的配置会覆盖Hadoop的配置.配置文件的设定对本机启动的所有hive有效

默认配置文件: ${HIVE_HOME}/conf/hive-default.xml
用户自定义配置文件: ${HIVE_HOME}/conf/hive-site.xml

# 命令行参数方式

> 启动hive时可以在命令行添加-hiveconf param=value来设定参数

如:

hive -hiveconf mapred.reduce.tasks=10

> 这种方式只对本次hive启动有效

查看参数

> set mapred.reduce.tasks;

可以在 HQL 中使用 SET 关键字设定参数,仅对本次 hive 启动有效。

> set mapred.reduce.tasks=100;
```

# 数据类型

## 基本数据类型
| Hive      | Java    | 长度                         | 语法示例                                  |
|-----------|---------|----------------------------|---------------------------------------|
| TINYINT   | byte    | 1byte 有符号整数                | 20                                    |
| SMALINT   | short   | 2byte 有符号整数                | 20                                    |
| INT       | int     | 4byte 有符号整数                | 20                                    |
| BIGINT    | long    | 8byte 有符号整数                | 20                                    |
| BOOLEAN   | boolean | 布尔类型，true 或者false          | TRUE FALSE                            |
| FLOAT     | float   | 单精度浮点数                     | 3.14159                               |
| DOUBLE    | double  | 双精度浮点数                     | 3.14159                               |
| STRING    | string  | 字符系列。可以指定字符集。可以使用单引号或者双引号。 | ‘ now is the time ’“for all good men” |
| TIMESTAMP |         | 时间类型                       |                                       |
| BINARY    |         | 字节数组                       |                                       |

> 对于 Hive 的 String 类型相当于数据库的 varchar 类型，该类型是一个可变的字符串，不
过它不能声明其中最多能存储多少个字符，理论上它可以存储 2GB 的字符数。

## 集合数据类型

| 数据类型   | 描述                                                                                                                  | 语法示例                                           |
|--------|---------------------------------------------------------------------------------------------------------------------|------------------------------------------------|
| STRUCT | 和 c 语言中的 struct 类似，都可以通过“点”符号访问元素内容。例如，如果某个列的数据类型是 STRUCT{first STRING, last STRING},那么`第 1 个元素可以通过字段.first 来 引用。`  | struct()例 如 struct<street:string, city:string> |
| MAP    | MAP 是一组键-值对元组集合，使用数组表示法可以访问数据。例如，如果某个列的数据类型是 MAP，其中键 ->值对是’first’->’John’和’last’->’Doe’，那么可以`通过字段名[‘last’]获取最后一个元素` | struct()例 如 struct<street:string, city:string> |
| ARRAY  | 数组是一组具有相同类型和名称的变量的集合。这些变量称为数组的元素，每个数组元素都有一个编号，编号从 零开始。例如，数组值为[‘John’, ‘Doe’]，`那么第 2 个 元素可以通过数组名[1]进行引用。`            | Array()例如 array<string>                        |

> Hive 有三种复杂数据类型 ARRAY、MAP 和 STRUCT。ARRAY 和 MAP 与 Java 中的 Array
和 Map 类似，而 STRUCT 与 C 语言中的 Struct 类似，它封装了一个命名字段集合，复杂数据
类型允许任意层次的嵌套。

### 案例实操

- 假设某表有如下一行，用`JSON`格式来表示数据结构。

```
{
  "name": "ggboy",
  "friends": ["zhuzhuxia", "eeboy"],  // Array
  "children": { // Map
    "xiaoGgboy": 18,
    "xiaoEeboy": 19
  },
  "address": { // 结构Struct
    "street": "HuaiHua",
    "city": "HuNan"
  }
}
```

- 在 Hive 里创建对应的表，并导入数据。

> 在宿主机创建文件,

```shell
touch "/opt/workspaces/hive/import_data_type_test"
vim /opt/workspaces/hive/import_data_type_test
```

> 输入需要导入的数据

```
ggboy,zhuzhuxia_eeboy,xiaoGgboy:18_xiaoEeboy:19,HuaiHua_HuNan
ggboy1,zhuzhuxia1_eeboy1,xiaoGgboy1:181_xiaoEeboy1:191,HuaiHua1_HuNan1
```

> 在hive导入数据

```hiveql
-- 创建数据库
create database if not exists data_type_test_db;
-- 使用数据库
use data_type_test_db;
-- 建表
create table test(
    name string,
    friends array<string>,
    children map<string, int>,
    address struct<street:string, city:string>
)
row format delimited fields terminated by ',' -- 列数据之间的分割符号
collection items terminated by '_' -- 集合数据之间的分割符号
map keys terminated by ':' -- map的key和value之间的分隔符
lines terminated by '\n' -- 每行数据的分割符号
;
-- 加载数据
load data local inpath '/opt/workspaces/hive/import_data_type_test' into table test;
-- 查询结果确认
Select * from test;
```

```
-- 以下是查询之后确认结果的数据输出结果
hive (data_type_test_db)> select * from test;
OK
test.name       test.friends    test.children   test.address
ggboy   ["zhuzhuxia","eeboy"]   {"xiaoGgboy":18,"xiaoEeboy":19} {"street":"HuaiHua","city":"HuNan"}
ggboy1  ["zhuzhuxia1","eeboy1"] {"xiaoGgboy1":181,"xiaoEeboy1":191}     {"street":"HuaiHua1","city":"HuNan1"}
Time taken: 0.187 seconds, Fetched: 2 row(s)
```

> 如果配置了源数据启动，就必须启动源数据服务`hive --service metastore
