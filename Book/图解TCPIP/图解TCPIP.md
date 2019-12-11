

## 第一章    网络基础知识



### 1.1、分组交换

> 在通信过程中，通信双方以分组为单位、使用存储-转发机制实现数据交互的通信方式，被称为分组交换（PS:packet switching）。

![分组交换](./images/1-1.1-1.png)

### 1.2、传输的方式

* **面向有连接型**

  > 在发送数据之前，需要在收发主机之间连接一条通信线路。

  ![有线连接](./images/1-1.2-1.jpg)

* **面向无连接型**

  > 发送端不需要建立和断开连接，发送端可于任何时候自由发送数据。接收端不知道何时从何地收到数据，因此需要时常确认是否收到数据。

  ![无线连接](./images/1-1.2-2.jpg)

### 1.3、单播，广播，多播，任播

> 网络通讯中根据客户端数量的一种分类

![单播，广播，多播，任播](./images/1-1.3-1.jpg)



### 1.4、地址

> 通信传输中，发送端和接收端可以被视为通信的主体。它们都能以一个所谓 “地址” 的信息加以标识起来。例如电话中号码就相当于 “地址”。

特性：

1. 唯一性
2. 层次性





### 1.5、网络构成的要素

![网络构成要素](./images/1-1.5-1.jpg)

| 设备                       | 作用                                    |
| :------------------------- | :-------------------------------------- |
| 网卡                       | 使计算机连网的设备（Network Interface） |
| 中继器（Repeater）         | 从物理层上延长网络的设备                |
| 网桥（Bridge）/2层交换机   | 从数据链路层延长网路的设备              |
| 路由器（Router）/3层交换机 | 通过网络层转发分组数据的设备            |
| 4 ~ 7 层交换机             | 处理传输层以上各层网络传输的设备        |
| 网关（Gateway）            | 转换协议的设备                          |





## 第二章    TCP/IP基础知识

### 2.1、背景及历史

> 由美国国防部主导，希望在通信传输的过程中，即使某些节点被攻击或破坏，也可以经过迂回路线实现最终通信。

![节点故障](./images/2-2.1-1.jpg)

![迂回](./images/2-2.1-2.jpg)



### 2.2、TCP/IP 的具体含义

> TCP/IP 并不只限于 TCP 与 IP 两种协议。具体来说，IP 或 ICMP、TCP 或 UDP、TELNET 或 FTP、以及 HTTP 等都是属于 TCP/IP协议。

![TCP/IP 协议簇](./images/2-2.2-1.jpg)

### 2.3、TCP/IP 与 OSI 参考模型

**开放式系统互联（OSI）**

> **开放系统互联(Open System Interconnection)**意为开放式系统互联，把[网络通信](https://baike.baidu.com/item/网络通信)的工作分为7层,分别是[物理层](https://baike.baidu.com/item/物理层),[数据链路层](https://baike.baidu.com/item/数据链路层),[网络层](https://baike.baidu.com/item/网络层),[传输层](https://baike.baidu.com/item/传输层),会话层,[表示层](https://baike.baidu.com/item/表示层)和[应用层](https://baike.baidu.com/item/应用层)。

**TCP/IP协议**

> TCP/IP（Transmission Control Protocol/Internet Protocol，传输控制协议/网际协议）是指能够在多个不同网络间实现信息传输的协议簇。TCP/IP协议不仅仅指的是[TCP](https://baike.baidu.com/item/TCP/33012) 和[IP](https://baike.baidu.com/item/IP/224599)两个协议，而是指一个由[FTP](https://baike.baidu.com/item/FTP/13839)、[SMTP](https://baike.baidu.com/item/SMTP/175887)、TCP、[UDP](https://baike.baidu.com/item/UDP/571511)、IP等协议构成的协议簇， 只是因为在TCP/IP协议中TCP协议和IP协议最具代表性，所以被称为TCP/IP协议。

![OSI与TCP/IP 参考模型](./images/2-2.3-1.jpg)

### 2.4、物理层（硬件）

> TCP/IP 的最底层是负责数据传输的硬件。如以太网、无线LAN、PPP等。



### 2.4、网络接口层（数据链路层）

> 网络接口层利用以太网中的数据链路层进行通信，因此属于接口层。类似驱动的概念。



### 2.5、互联网层（网络层）





### 2.6、传输层

### 2.8、应用层（会话层以上的分层）



