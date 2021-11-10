# 准备工作

- 安装nacos

- 安装mysql

# 开始安装配置

## 修改`file.conf`，`registry.conf`配置文件内容

> `file.conf`修改之后

```
## transaction log store, only used in seata-server
store {
  ## store mode: file、db、redis
  mode = "db"

  ## file store property
  file {
    ## store location dir
    dir = "sessionStore"
    # branch session size , if exceeded first try compress lockkey, still exceeded throws exceptions
    maxBranchSessionSize = 16384
    # globe session size , if exceeded throws exceptions
    maxGlobalSessionSize = 512
    # file buffer size , if exceeded allocate new buffer
    fileWriteBufferCacheSize = 16384
    # when recover batch read size
    sessionReloadReadSize = 100
    # async, sync
    flushDiskMode = async
  }

  ## database store property
  db {
    ## the implement of javax.sql.DataSource, such as DruidDataSource(druid)/BasicDataSource(dbcp)/HikariDataSource(hikari) etc.
    datasource = "druid"
    ## mysql/oracle/postgresql/h2/oceanbase etc.
    dbType = "mysql"
    driverClassName = "com.mysql.jdbc.Driver"
    url = "jdbc:mysql://192.168.1.150:3306/seata"
    user = "root"
    password = "000000"
    minConn = 5
    maxConn = 100
    globalTable = "global_table"
    branchTable = "branch_table"
    lockTable = "lock_table"
    queryLimit = 100
    maxWait = 5000
  }

  ## redis store property
  redis {
    host = "127.0.0.1"
    port = "6379"
    password = ""
    database = "0"
    minConn = 1
    maxConn = 10
    maxTotal = 100
    queryLimit = 100
  }

}
```

> `registry.conf`修改之后

```
registry {
  # file 、nacos 、eureka、redis、zk、consul、etcd3、sofa
  type = "nacos"
  loadBalance = "RandomLoadBalance"
  loadBalanceVirtualNodes = 10

  nacos {
    application = "seata-server"
    serverAddr = "192.168.1.1:8848"
    group = "DEFAULT_GROUP"
    namespace = ""
    cluster = "default"
    username = ""
    password = ""
  }
  eureka {
    serviceUrl = "http://localhost:8761/eureka"
    application = "default"
    weight = "1"
  }
  redis {
    serverAddr = "localhost:6379"
    db = 0
    password = ""
    cluster = "default"
    timeout = 0
  }
  zk {
    cluster = "default"
    serverAddr = "127.0.0.1:2181"
    sessionTimeout = 6000
    connectTimeout = 2000
    username = ""
    password = ""
  }
  consul {
    cluster = "default"
    serverAddr = "127.0.0.1:8500"
  }
  etcd3 {
    cluster = "default"
    serverAddr = "http://localhost:2379"
  }
  sofa {
    serverAddr = "127.0.0.1:9603"
    application = "default"
    region = "DEFAULT_ZONE"
    datacenter = "DefaultDataCenter"
    cluster = "default"
    group = "SEATA_GROUP"
    addressWaitTime = "3000"
  }
  file {
    name = "file.conf"
  }
}

config {
  # file、nacos 、apollo、zk、consul、etcd3
  type = "nacos"

  nacos {
    serverAddr = "192.168.1.1:8848"
    namespace = ""
    group = "DEFAULT_GROUP"
    username = ""
    password = ""
  }
  consul {
    serverAddr = "127.0.0.1:8500"
  }
  apollo {
    appId = "seata-server"
    apolloMeta = "http://192.168.1.204:8801"
    namespace = "application"
    apolloAccesskeySecret = ""
  }
  zk {
    serverAddr = "127.0.0.1:2181"
    sessionTimeout = 6000
    connectTimeout = 2000
    username = ""
    password = ""
  }
  etcd3 {
    serverAddr = "http://localhost:2379"
  }
  file {
    name = "file.conf"
  }
}
```

## 下载config.txt与nacos-config.sh文件

1.4版本`config.txt`下载地址：
https://github.com/seata/seata/blob/1.4.0/script/config-center/config.txt

1.4版本`nacos-config.sh`下载地址：https://github.com/seata/seata/blob/1.4.0/script/config-center/nacos/nacos-config.sh

>这两个文件的作用：
>   config.txt就是seata各种详细的配置，执行 nacos-config.sh
>   即可将这些配置导入到nacos，这样就不需要将file.conf和registry.conf放到我们的项目中了，需要什么配置就直接从nacos中读取。

> 配置`config.txt`

```
store.db.url=jdbc:mysql://192.168.1.1:3306/seata?useUnicode=true
store.db.user=root
store.db.password=000000
```

- 输入命令行，将这些配置导入到nacos的seata命名空间中：

```
sh nacos-config.sh -h localhost -p 8848 -g DEFAULT_GROUP -t ae1a9bfd-4954-42cb-b60c-f72f51ec0d97 -u nacos -w nacos
```

命令解析：

- -h -p 指定nacos的端口地址；
- -g 指定配置的分组，注意，是配置的分组；
- -t 指定命名空间id；
- -u -w指定nacos的用户名和密码，同样，这里开启了nacos注册和配置认证的才需要指定。

> 注意：`config.txt`在当前目录的../seata根目录上

- 下载sql文件

```
https://github.com/seata/seata/blob/1.4.0/script/server/db/mysql.sql
```

## 项目配置

1. 依赖
```
<!--seata 1.4版本依賴 -->
 <dependency>
     <groupId>io.seata</groupId>
     <artifactId>seata-spring-boot-starter</artifactId>
     <version>1.4.0</version>
 </dependency>
 <dependency>
     <groupId>com.alibaba.cloud</groupId>
     <artifactId>spring-cloud-starter-alibaba-seata</artifactId>
     <version>2.2.1.RELEASE</version>
     <exclusions>
         <exclusion>
             <groupId>io.seata</groupId>
             <artifactId>seata-spring-boot-starter</artifactId>
         </exclusion>
     </exclusions>
 </dependency>
```

2. 增加application.yml配置：

```
server:
  port: 1000
spring:
  datasource:
    username: root
    password: 000000
    url: jdbc:mysql://192.168.1.150:3306/test
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848
seata:
  enabled: true
  application-id: ${spring.application.name}
  tx-service-group: spring-cloud-demo    #此处配置自定义的seata事务分组名称
  enable-auto-data-source-proxy: true    #开启数据库代理
  config:
    type: nacos
    nacos:
      server-addr: ${spring.cloud.nacos.discovery.server-addr}
      namespace: ${spring.cloud.nacos.discovery.namespace}
      group: DEFAULT_GROUP
  registry:
    type: nacos
    nacos:
      application: seata-server
      server-addr: ${spring.cloud.nacos.discovery.server-addr}
      namespace: ${spring.cloud.nacos.discovery.namespace}
```