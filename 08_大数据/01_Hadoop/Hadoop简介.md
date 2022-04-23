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

- 安装rsync(最小软件操作系统则需要安装)

```shell
yum install -y rsync
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

# 拷贝环境到多台机器

- 拷贝Java和Hadoop到目标机器

> 通过hostname拷贝文件或文件夹
```shell
#!/bin/bash

# 工具名称
application_name=cptool

# 定义日志等级
error="[error - $application_name]: "

# 获取需要拷贝的源路径
source_dir=$1

# 文件不存在退出
if [[ -z $source_dir ]]; then
        echo $error source_dir cannot be null
        exit
else
        # 输入文件路径存在，找该文件 文件存在
        if [[ -d $source_dir || -f $source_dir ]]; then
                for node_num in {101..102}
                do
                        node=hadoop$node_num
                        scp -r $source_dir root@$node:$source_dir
                done
        # 文件不存在
        else
                echo $error filepath $source_dir not found
        fi
fi
```

- 配置xsync

```shell
#!/bin/bash
#1. 判断参数个数
if [ $# -lt 1 ]
then
 echo Not Enough Arguement!
 exit;
fi
#2. 遍历集群所有机器
for host in hadoop102 hadoop103 hadoop104
do
 echo ==================== $host ====================
 #3. 遍历所有目录，挨个发送
 for file in $@
 do
 #4. 判断文件是否存在
 if [ -e $file ]
 then
 #5. 获取父目录
 pdir=$(cd -P $(dirname $file); pwd)
 #6. 获取当前文件的名称
 fname=$(basename $file)
 ssh $host "mkdir -p $pdir"
 rsync -av $pdir/$fname $host:$pdir
 else
 echo $file does not exists!
 fi
 done
done

```


- 配置免密登录

```shell
ssh-keygen -t rsa
ssh-copy-id {hostname}
```

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

### 集群配置

#### 核心配置文件

- core-size.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>
<configuration>
    <!-- 指定 NameNode 的地址 -->
    <property>
        <name>fs.defaultFS</name>
        <value>hdfs://hadoop100:8020</value>
    </property>
    <!-- 指定 hadoop 数据的存储目录 -->
    <property>
        <name>hadoop.tmp.dir</name>
        <value>/opt/software/hadoop-3.1.3/data</value>
    </property>
    <!-- 配置 HDFS 网页登录使用的静态用户为 atguigu -->
    <property>
        <name>hadoop.http.staticuser.user</name>
        <value>bnyte</value>
    </property>
</configuration>
```

- hdfs-size.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>
<configuration>
    <!-- nn web 端访问地址-->
    <property>
        <name>dfs.namenode.http-address</name>
        <value>hadoop100:9870</value>
    </property>
    <!-- 2nn web 端访问地址-->
    <property>
        <name>dfs.namenode.secondary.http-address</name>
        <value>hadoop102:9868</value>
    </property>
</configuration>
```

- yarn-size.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>
<configuration>
    <!-- 指定 MR 走 shuffle -->
    <property>
        <name>yarn.nodemanager.aux-services</name>
        <value>mapreduce_shuffle</value>
    </property>
    <!-- 指定 ResourceManager 的地址-->
    <property>
        <name>yarn.resourcemanager.hostname</name>
        <value>hadoop102</value>
    </property>
    <!-- 环境变量的继承 -->
    <property>
        <name>yarn.nodemanager.env-whitelist</name>

        <value>JAVA_HOME,HADOOP_COMMON_HOME,HADOOP_HDFS_HOME,HADOOP_CO
            NF_DIR,CLASSPATH_PREPEND_DISTCACHE,HADOOP_YARN_HOME,HADOOP_MAP
            RED_HOME
        </value>
    </property>
</configuration>
```

- mapred-site.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>
<configuration>
    <!-- 指定 MapReduce 程序运行在 Yarn 上 -->
    <property>
        <name>mapreduce.framework.name</name>
        <value>yarn</value>
    </property>
</configuration>
```

- 将配置好的配置分发给其他的节点

```shell
xsync /opt/software/hadoop3.1.3/etc/hadoop/
```


- 配置/opt/software/hadoop-3.1.3/etc/hadoop/workers
```shell
hadoop100
hadoop101
hadoop102
```

> 注意：这一步配置不可以有任何的空格

#### 群起集群

> 如果是第一次启动集群需要在`hadoop100`初始化`NameNode`(注意：格式
化 NameNode，会产生新的集群 id，导致 NameNode 和 DataNode 的集群 id 不一致，集群找
不到已往数据。如果集群在运行过程中报错，需要重新格式化 NameNode 的话，一定要先停
止 namenode 和 datanode 进程，并且要删除所有机器的 data 和 logs 目录，然后再进行格式
化。)

- 初始化集群(第一次启动需要)

```shell
hdfs namenode -format # 数据会被清空
```

- 启动集群

```shell
sh ${HADOOP_HOME}/sbin/start-dfs.sh
```

> 如果是使用`root`做的操作可能会报如下错

```
Starting namenodes on [hadoop100]
ERROR: Attempting to operate on hdfs namenode as root
ERROR: but there is no HDFS_NAMENODE_USER defined. Aborting operation.
Starting datanodes
ERROR: Attempting to operate on hdfs datanode as root
ERROR: but there is no HDFS_DATANODE_USER defined. Aborting operation.
Starting secondary namenodes [hadoop102]
ERROR: Attempting to operate on hdfs secondarynamenode as root
ERROR: but there is no HDFS_SECONDARYNAMENODE_USER defined. Aborting operation.
```

> 解决方案

- 对于start-dfs.sh和stop-dfs.sh文件，添加下列参数

```shell
HDFS_DATANODE_USER=root
HADOOP_SECURE_DN_USER=hdfs
HDFS_NAMENODE_USER=root
HDFS_SECONDARYNAMENODE_USER=root
```

- 对于start-yarn.sh和stop-yarn.sh文件，添加下列参数

```shell
#!/usr/bin/env bash
YARN_RESOURCEMANAGER_USER=root
HADOOP_SECURE_DN_USER=yarn
YARN_NODEMANAGER_USER=root
```