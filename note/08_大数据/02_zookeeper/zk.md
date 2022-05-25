# Zookeeper

> 基于`3.8.0`版本

## 下载安装启动

### 下载源码

- Linux

```shell
# 下载wget
yarn -y install wget

# 下载zookeeper
wget https://dlcdn.apache.org/zookeeper/zookeeper-3.8.0/apache-zookeeper-3.8.0-bin.tar.gz

# 解压
tar -zxvf apache-zookeeper-3.8.0-bin.tar.gz
```

- Windows

[zookeeper-3.8.0](https://dlcdn.apache.org/zookeeper/zookeeper-3.8.0/apache-zookeeper-3.8.0-bin.tar.gz)

直接解压就可以了

### 单机部署

> 修改zoo_sample.cfg

```properties
# 数据存储路径
dataDir=../data
```

> 改名`conf/zoo_sample.cfg`为`zoo.cfg`

```shell script
mv zoo_sample.cfg zoo.cfg
```

> 启动

```shell script
./bin/zkService.sh start
```

> 查看状态

```shell script
./bin/zkService.sh status
```

> 停止

```shell script
./bin/zkService.sh stop
```

### 集群启动

> data目录添加`myid`

```shell script
cd {zookeeper_home}/data
vim myid
```

> 内容如下

注意：不同接节点配置的id不同，会与下面的集群配置对应
```shell script
0
```

> 修改zoo_sample.cfg

```properties
# 数据存储路径
dataDir=../data

# 最下面添加集群配置
# cluster configuration
server.0=hadoop100:2888:3888
server.1=hadoop101:2888:3888
server.2=hadoop102:2888:3888
```

> 参数解读

```
server.A=B:C:D

A 是一个数字，表示这个是第几号服务器；
集群模式下配置一个文件 myid，这个文件在 dataDir 目录下，这个文件里面有一个数据
就是 A 的值，Zookeeper 启动时读取此文件，拿到里面的数据与 zoo.cfg 里面的配置信息比
较从而判断到底是哪个 server。
B 是这个服务器的地址；
C 是这个服务器 Follower 与集群中的 Leader 服务器交换信息的端口；
D 是万一集群中的 Leader 服务器挂了，需要一个端口来重新进行选举，选出一个新的
Leader，而这个端口就是用来执行选举时服务器相互通信的端口。
```

> 改名`conf/zoo_sample.cfg`为`zoo.cfg`

```shell script
mv zoo_sample.cfg zoo.cfg
```

> 启动

```shell script
./bin/zkService.sh start
```

> 查看状态

```shell script
./bin/zkService.sh status
```

> 停止

```shell script
./bin/zkService.sh stop
```
