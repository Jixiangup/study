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

