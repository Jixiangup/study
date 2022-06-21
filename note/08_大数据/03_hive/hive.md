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


> 如果配置了源数据启动，就必须启动源数据服务`hive --service metastore
>
> 