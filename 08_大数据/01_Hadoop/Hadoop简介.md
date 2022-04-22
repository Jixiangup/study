# Hadoop为什么而生

Hadoop的出生是为了解决`海量`的数据`计算和存储`而生

# Hadoop的版本介绍

| 组件\版本 | V1.x | V2.x | V3.x |
|  ----  | ---- | ---- | ---- |
| 资源调度  | MapReduce | YARN | YARN |
| 计算组件 | MapReduce | MapReduce | MapReduce |
| 存储组件 | HDFS | HDFS | HDFS |
| 辅助组件 | COMMON | COMMON | COMMON |

# YARN

yarn作为资源调度组件

# 安装Hadoop集群

## 准备工作

- 安装epel-release

```shell
yum install -y epel-release
```

- 安装net-tool(最小软件操作系统则需要安装)

```shell
yum install -y net-tool
```

- 安装vim(最小软件操作系统则需要安装)

```shell
yum install -y vim
```

- 关闭防火墙

```shell
systemctl stop firewalld
systemctl disable firewalld.service
```

> 为方便开发最好配置hosts、配置高可用集群克隆多台模板机器运行并配置网卡

- 安装JDK

略

- 安装HadoopV3.0.3

> 配置Hadoop的`bin`和`sbin`到path即可

# 运行Hadoop

## 本地启动

> 本地启动仅作为体验用 我发使用hdfs yarn等组件(单机)

### 案例(wordcount)

- 在`${HADOOP_HOME}`创建`input/word.txt`文件夹及文件并输入字符
```text
bnyte
liujixiang
liuyang
shuaige
wozuishuaiqi
```
- 统计每个名字出现的次数

执行计算

```
hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-examples-3.1.3.jar wordcount wcinput/ wcoutput
```

`wordcount`: 统计字符次数

`wcinput/`: 指定需要统计的文件所在的目录

`wcoutput`: 指定结果输出目录(如果文件已经存在则会直接报错),`_SUCCESS`文件用作标识本次执行成功,没有其他数据.

## 伪集群启动

> 伪集群可以使用Hadoop的所有功能且使用`SFTP`作为存储组件(单机)

## 集群启动

> 使用所有Hadoop组件功能并且支持多节点、高可用(集群模式)

