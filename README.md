# kafka-test

这是一个使用scala编写的kafka的测试用例。

还包含了scala的基础的一些语言知识。



## 0.	准备工作

### 0.1.	安装配置并且启动kafka

启动zookeeper：

```shell
bin/zookeeper-server-start.sh -daemon config/zookeeper.properties
```

启动kafka服务：

```shell
bin/kafka-server-start.sh -daemon config/server.properties
```

启动kafka，并且创建topic: test1

```shell
bin/kafka-topics.sh --create --topic test1 --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
```

### 0.2.	查看主题：

```shell
bin/kafka-topics.sh --list --bootstrap-server localhost:9092
```