package org.example
import java.util.{Collections, Properties}

import org.apache.kafka.clients.consumer.{ConsumerRecords, KafkaConsumer}

object KafkaConsumerDemo {
  def main(args: Array[String]): Unit = {
    // 配置信息
    val prop = new Properties
    prop.put("bootstrap.servers", "192.168.3.41:9092")
    // 指定消费者组
    prop.put("group.id", "group01")
    // 指定消费位置: earliest(从起始位置读取分区记录)/latest(从最新的位置开始), 在读取一个没有偏移量的分区或者偏移量无效的情况下如何处理
    prop.put("auto.offset.reset", "earliest")
    // 指定消费的key的反序列化方式
    prop.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    // 指定消费的value的反序列化方式
    prop.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
//    自动提交
    prop.put("enable.auto.commit", "true")
    prop.put("session.timeout.ms", "30000")
    // 得到Consumer实例
    val kafkaConsumer = new KafkaConsumer[String, String](prop)
    // 首先需要订阅topic
    kafkaConsumer.subscribe(Collections.singletonList("test2"))
//    kafkaConsumer subscribe "test.*"
//    kafkaConsumer subscribe "test1"

    // 开始消费数据
    while (true) {
//       如果Kafak中没有消息，会隔timeout这个值读一次。比如上面代码设置了2秒，也是就2秒后会查一次。
//       如果Kafka中还有消息没有消费的话，会马上去读，而不需要等待。
      val msgs: ConsumerRecords[String, String] = kafkaConsumer.poll(20)
      // println(msgs.count())
//      msgs.iterator()
      val it = msgs.iterator()
      //it.forEachRemaining(println(s"${it.partition()}"))
      while (it.hasNext) {
        val msg = it.next()
        println(s"partition: ${msg.partition()}, offset: ${msg.offset()}, key: ${msg.key()}, value: ${msg.value()}")
      }
    }
  }
}
