## 一、IOC（DI）

### 1.1 功能分析

ioc的前提的bean容器，获取容器的代码如下：

```java
BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanFactoryTest.xml"));
```

如同把大象放进冰箱一样，这段代码无非完成以下几点：

* 读取配置文件 beanFactoryTest.xml。
* 根据 beanFactoryTest.xml 中的配置找到对应的类的配置，并实例化。
* 调用实例化后的实例。

![image-20210331225716747](image/核心类DefaultListableBeanFactory.png)





## 二、AOP

