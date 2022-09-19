# 常用命令

1. 查看端口被那个进程占用的方法

    ```shell
    lsof -i:{端口号}
    ```
    
    > COMMAND     PID USER   FD   TYPE    DEVICE SIZE/OFF NODE NAME
    > 
    > java    2560891 root  355u  IPv6 755241105      0t0  TCP jz-desktop-04:10001 (LISTEN)
    
    ```shell
    netstat -tunlp | greo {端口号}
    ```

    > tcp6       0      0 192.168.1.80:10001      :::*                    LISTEN      2560891/java
    >
    > 进程协议     未知   未知  ip:port             外部可访问机器ip(猜的)       监听中      占用进程号/启动方式

2. 查看进程详情

    ```shell
    ll /proc/{pid}
    ```



