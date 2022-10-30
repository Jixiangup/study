# 环境准备

```
# 关闭防火墙
systemctl stop firewalld # 临时关闭
systemctl disable firewalld # 永久关闭

# 关闭selinux
sed -i 's/enforcing/disabled/' /etc/selinux/config  # 永久
setenforce 0  # 临时

# 关闭swap
swapoff -a  # 临时
sed -ri 's/.*swap.*/#&/' /etc/fstab    # 永久

# 在master添加hosts
cat >> /etc/hosts << EOF
192.168.44.146 k8smaster
192.168.44.145 k8snode1
192.168.44.144 k8snode2
EOF

# 将桥接的IPv4流量传递到iptables的链
cat > /etc/sysctl.d/k8s.conf << EOF
net.bridge.bridge-nf-call-ip6tables = 1
net.bridge.bridge-nf-call-iptables = 1
EOF
sysctl --system  # 生效

# 时间同步
yum install ntpdate -y
ntpdate time.windows.com
```

# 在所有节点安装`Docker`/`Kubeadm`/`Kubelet`

K8s默认CRI(容器运行时)为Docker，因此先安装docker

## 安装docker

> 如果`wget报错`则执行`yum -y install wget`进行`wget安装`

```
wget https://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo -O /etc/yum.repos.d/docker-ce.repo

yum -y install docker-ce-18.06.1.ce-3.el7

systemctl enable docker && systemctl start docker

docker --version
```

> 配置docker代理源

```
cat > /etc/docker/daemon.json << EOF
{
  "registry-mirrors": ["https://b9pmyelo.mirror.aliyuncs.com"]
}
EOF
```

## 添加阿里云yum软件源

```
cat > /etc/yum.repos.d/kubernetes.repo << EOF
[kubernetes]
name=Kubernetes
baseurl=https://mirrors.aliyun.com/kubernetes/yum/repos/kubernetes-el7-x86_64
enabled=1
gpgcheck=0
repo_gpgcheck=0
gpgkey=https://mirrors.aliyun.com/kubernetes/yum/doc/yum-key.gpg https://mirrors.aliyun.com/kubernetes/yum/doc/rpm-package-key.gpg
EOF
```

## 安装kubeadm、kubelet、kubectl

由于版本更新频繁，这里指定版本号部署：

```
yum install -y kubelet-1.18.0 kubeadm-1.18.0 kubectl-1.18.0
systemctl enable kubelet
```

# 部署k8s的master

> 在master执行， 需要注意的是把IP改一下，并且在执行完这条语句时会一直处于等待状态，因为他在拉取docker镜像

```
kubeadm init \
  --apiserver-advertise-address=192.168.1.150 \
  --image-repository registry.aliyuncs.com/google_containers \
  --kubernetes-version v1.18.0 \
  --service-cidr=52.66.0.0/12 \
  --pod-network-cidr=39.44.0.0/16
```

> 参数介绍

```
apiserver-advertise-address：当前主节点的IP地址
image-repository：拉取docker镜像时的代理仓库
kubernetes-version：k8s的版本
service-cidr：其中service的ip，没有特定要求，不重复就可以了
pod-network-cidr：其中pod的ip，没有特定要求，不重复就可以了
```

使用kubectl工具执行，需要注意的是这些语句在完成`kubeadm init`之后会在控制台提示：

```bash
mkdir -p $HOME/.kube

sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config

sudo chown $(id -u):$(id -g) $HOME/.kube/config

kubectl get nodes # 查看当前节点，此时是没有节点的
```

# 加入k8s node节点

> 在所有子节点执行`kubeadm init`控制台提示的`kubeadm join`语句

如：

```
kubeadm join 192.168.1.150:6443 --token pllvsc.yobmkxi2e35qi6rm \
    --discovery-token-ca-cert-hash sha256:0bd5acc61daf10cf067e9f461f1b88f879d23268656aa1798c3f93400f8099c5
```

> 默认token有效期为24小时，当过期之后，该token就不可用了。这时就需要重新创建token，操作如下：

```
kubeadm token create --print-join-command
```

> 查看子节点是否加入成功, 该语句需要在master执行

```
kubectl get nodes
```

# 部署CNI网络插件（master执行）

> 因为k8s需要配置`cni网络插件`才能正常启动，此时`kubectl get nodes`是可以看到所有节点都是`NotReady`，所以需要配置`cni网络插件`

- 方式一

```
wget https://raw.githubusercontent.com/coreos/flannel/master/Documentation/kube-flannel.yml # 下载配置文件到本地

kubectl apply -f <配置文件本地地址> # 应用配置文件
```

> 文件内容

```yaml
---
kind: ClusterRole
apiVersion: rbac.authorization.k8s.io/v1beta1
metadata:
  name: flannel
rules:
  - apiGroups:
      - ""
    resources:
      - pods
    verbs:
      - get
  - apiGroups:
      - ""
    resources:
      - nodes
    verbs:
      - list
      - watch
  - apiGroups:
      - ""
    resources:
      - nodes/status
    verbs:
      - patch
---
kind: ClusterRoleBinding
apiVersion: rbac.authorization.k8s.io/v1beta1
metadata:
  name: flannel
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: ClusterRole
  name: flannel
subjects:
- kind: ServiceAccount
  name: flannel
  namespace: kube-system
---
apiVersion: v1
kind: ServiceAccount
metadata:
  name: flannel
  namespace: kube-system
---
kind: ConfigMap
apiVersion: v1
metadata:
  name: kube-flannel-cfg
  namespace: kube-system
  labels:
    tier: node
    app: flannel
data:
  cni-conf.json: |
     {
     "name":"cni0",
     "cniVersion":"0.3.1",
     "plugins":[
       {
         "type":"flannel",
         "delegate":{
           "forceAddress":false,
           "hairpinMode": true,
           "isDefaultGateway":true
         }
       },
       {
         "type":"portmap",
         "capabilities":{
           "portMappings":true
         }
       },
     {
       "name": "mytuning",  
       "type": "tuning",
       "sysctl": {
               "net.core.somaxconn": "65535",
               "net.ipv4.ip_local_port_range": "1024 65535",
               "net.ipv4.tcp_keepalive_time": "600",
               "net.ipv4.tcp_keepalive_probes": "10",
               "net.ipv4.tcp_keepalive_intvl": "30"
       }
     }
     ]
     }
  net-conf.json: |
    {
      "Network": "10.80.0.0/12",  # pod cird 根据自己定义修改
      "Backend": {
        "Type": "VXLAN",
        "Directrouting": true # 开启路由混合模式 云环境不能开启
      }
    }
---
apiVersion: apps/v1
kind: DaemonSet
metadata:
  name: kube-flannel-ds-amd64
  namespace: kube-system
  labels:
    tier: node
    app: flannel
spec:
  selector:
    matchLabels:
      app: flannel
  template:
    metadata:
      labels:
        tier: node
        app: flannel
    spec:
      hostNetwork: true
      nodeSelector:
        beta.kubernetes.io/arch: amd64
      tolerations:
      - operator: Exists
        effect: NoSchedule
      serviceAccountName: flannel
      initContainers:
      - name: install-cni
        image: quay.io/coreos/flannel:v0.12.0-amd64
        command:
        - cp
        args:
        - -f
        - /etc/kube-flannel/cni-conf.json
        - /etc/cni/net.d/10-flannel.conflist
        volumeMounts:
        - name: cni
          mountPath: /etc/cni/net.d
        - name: flannel-cfg
          mountPath: /etc/kube-flannel/
      containers:
      - name: kube-flannel
        image: quay.io/coreos/flannel:v0.12.0-amd64
        command:
        - /opt/bin/flanneld
        args:
        - --ip-masq
        - --kube-subnet-mgr
        resources:
          requests:
            cpu: "100m"
            memory: "50Mi"
          limits:
            cpu: "100m"
            memory: "50Mi"
        securityContext:
          privileged: true
        env:
        - name: POD_NAME
          valueFrom:
            fieldRef:
              fieldPath: metadata.name
        - name: POD_NAMESPACE
          valueFrom:
            fieldRef:
              fieldPath: metadata.namespace
        volumeMounts:
        - name: run
          mountPath: /run
        - name: flannel-cfg
          mountPath: /etc/kube-flannel/
      volumes:
        - name: run
          hostPath:
            path: /run
        - name: cni
          hostPath:
            path: /apps/cni/etc/net.d # 改成kubelet cni 配置路径
        - name: flannel-cfg
          configMap:
            name: kube-flannel-cfg
```

- 方式二

```
kubectl apply -f https://raw.githubusercontent.com/coreos/flannel/master/Documentation/kube-flannel.yml # 从网络选择配置文件应用
```

> 再次使用`kubectl get nodes`查看节点全部为`Ready`则表示`k8s启动成功`, 注意这段时间需要稍微等待一下，因为`k8s会检测当前节点是否启动成功才会让他Ready`

# 报错解决

- 报错输出

> /etc/kubernetes/pki/ca.crt already exists

- 解决方案

```
在子节点执行 kubeadm reset
```

- 报错输出

> The connection to the server raw.githubusercontent.com was refused - did you specify the right host or port?

- 解决方案

```
在`http://ip.tool.chinaz.com/`中输入域名`raw.githubusercontent.com`解析ip，然后将ip及域名添加到 `/etc/hosts`中对应即可
```

# 测试k8s集群(master执行)

> 在k8s集群中创建一个`pod`，验证是否正常运行：

```
kubectl create deployment nginx --image=nginx

kubectl expose deployment nginx --port=80 --type=NodePort

kubectl get pod, svc # 查看pod及端口等信息
```

> 执行`kubectl get pod`查看已经启动的pod, 或者直接浏览器访问`<NodeIp>:<port>`
