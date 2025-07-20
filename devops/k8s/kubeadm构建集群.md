> 官方文档：[安装 kubeadm | Kubernetes](https://kubernetes.io/zh-cn/docs/setup/production-environment/tools/kubeadm/install-kubeadm/)

## 一、基础环境准备（所有节点执行）

### 禁用内存交换

```bash
sudo nano /etc/fstab

# 注释此行 /swapfile      none    swap    sw      0       0
# ctrl + O 保存，ctrl + x退出

# 执行临时禁用命令
sudo swapoff -a
```



### 开启ipv4转发

```bash
# 加载模块
sudo modprobe br_netfilter
echo 'br_netfilter' | sudo tee /etc/modules-load.d/k8s.conf
cat <<EOF | sudo tee /etc/sysctl.d/k8s.conf
net.bridge.bridge-nf-call-iptables  = 1
net.bridge.bridge-nf-call-ip6tables = 1
net.ipv4.ip_forward                 = 1
EOF
# 生效
sudo sysctl --system
```



### 安装容器运行时（containerd 阿里云加速）

```bash
# 官方安装文档：https://docs.docker.com/engine/install/ubuntu/
sudo apt-get update
sudo apt-get install ca-certificates curl
sudo install -m 0755 -d /etc/apt/keyrings
# 添加阿里云GPG密钥
sudo mkdir -p /etc/apt/keyrings
curl -fsSL https://mirrors.aliyun.com/docker-ce/linux/ubuntu/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg

# 添加阿里云Docker源
echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] https://mirrors.aliyun.com/docker-ce/linux/ubuntu \
  $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
sudo apt-get update
sudo apt-get install containerd.io -y


#######################  配置 containerd  #######################
# 生成默认配置文件
containerd config default | sudo tee /etc/containerd/config.toml > /dev/null
# 修改
sudo nano /etc/containerd/config.toml

# 1.使用阿里云镜像时修改镜像名
[plugins."io.containerd.grpc.v1.cri"]
  sandbox_image = "registry.aliyuncs.com/google_containers/pause:3.9"
  
# 2.修改cgroup为true
[plugins."io.containerd.grpc.v1.cri".containerd.runtimes.runc.options]
  SystemdCgroup = true

# 3.配置镜像地址
[plugins."io.containerd.grpc.v1.cri".registry]
  config_path = "/etc/containerd/certs.d"

# ctrl + O 保存，ctrl + x退出

# 配置registry.k8s.io镜像
sudo mkdir -p /etc/containerd/certs.d/registry.k8s.io
cat <<EOF | sudo tee /etc/containerd/certs.d/registry.k8s.io/hosts.toml
server = "https://registry.k8s.io"

[host."https://registry.aliyuncs.com"]
  capabilities = ["pull", "resolve"]

[host."registry.aliyuncs.com/google_containers"]
  capabilities = ["pull", "resolve"]
EOF

# 配置docker.io镜像
sudo mkdir -p /etc/containerd/certs.d/docker.io
cat <<EOF | sudo tee /etc/containerd/certs.d/docker.io/hosts.toml
server = "https://docker.io"

[host."https://registry.aliyuncs.com"]
  capabilities = ["pull", "resolve"]

[host."https://docker.m.daocloud.io"]
  capabilities = ["pull", "resolve"]

[host."https://s2eh1oac.mirror.aliyuncs.com"]
  capabilities = ["pull", "resolve"]

[host."https://registry.docker-cn.com"]
  capabilities = ["pull", "resolve"]
EOF

# 重启容器使配置生效
sudo systemctl restart containerd


```



### 安装 kubeadm、kubelet 和 kubectl

```bash
sudo apt-get install -y apt-transport-https ca-certificates curl gpg
#  apt k8s 阿里云镜像配置
# 密钥
curl -fsSL https://mirrors.aliyun.com/kubernetes/apt/doc/apt-key.gpg | sudo gpg --dearmor -o /etc/apt/trusted.gpg.d/kubernetes-apt.gpg
# 仓库
echo "deb [signed-by=/etc/apt/trusted.gpg.d/kubernetes-apt.gpg] https://mirrors.aliyun.com/kubernetes/apt/ kubernetes-xenial main" | sudo tee /etc/apt/sources.list.d/kubernetes.list
sudo apt-get update
sudo apt-get install -y kubelet kubeadm kubectl
# 锁定版本
sudo apt-mark hold kubelet kubeadm kubectl
```





## 二、构建集群

### 控制节点初始化（主）

```bash
# 后续安装 网络插件calico 所需 --pod-network-cidr=192.168.0.0/16
# 拷贝输出的接入指令，工作节点需要执行
sudo kubeadm init   --image-repository registry.aliyuncs.com/google_containers   --pod-network-cidr=192.168.0.0/16  --v=5

非root用户操作执行
mkdir -p $HOME/.kube
sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
sudo chown $(id -u):$(id -g) $HOME/.kube/config
```



### 安装网络插件（calico 3.28）（主）

```bash
# 官方文档：https://docs.tigera.io/calico/3.28/getting-started/kubernetes/quickstart
kubectl create -f https://raw.githubusercontent.com/projectcalico/calico/v3.28.5/manifests/tigera-operator.yaml

kubectl create -f https://raw.githubusercontent.com/projectcalico/calico/v3.28.5/manifests/custom-resources.yaml

# 等到每个 pod 的 STATUS 都变为 Running
watch kubectl get pods -n calico-system
```



### 接入工作节点（从）

```bash
sudo kubeadm join 192.168.31.105:6443 --token tx44mq.guq31itsbuul4f19 \
        --discovery-token-ca-cert-hash sha256:3479dab32e32fcaaffb75420a2d4e35e1195260782c0e3bf0ab8eee25ed9848c
```





## 三、其它常用

### 清理环境

```bash
# 重置kubeadm（会删除所有集群配置）
sudo kubeadm reset -f

# 清理残留文件
sudo rm -rf /etc/kubernetes /var/lib/etcd /var/lib/kubelet

# 重启系统
sudo reboot
```



### 生成新token

```bash
# 默认 token 24 只有24小时的内有效，
kubeadm token create --ttl 168h --print-join-command
```



