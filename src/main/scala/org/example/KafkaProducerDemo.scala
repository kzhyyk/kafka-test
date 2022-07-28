package org.example

import java.util.Properties
import org.apache.kafka.clients.producer.{Callback, KafkaProducer, ProducerRecord, RecordMetadata}

/**
 * 实现producer
 */
object KafkaProducerDemo {

  def main(args: Array[String]): Unit = {
    val prop = new Properties
    // 指定请求的kafka集群列表
    prop.put("bootstrap.servers", "192.168.137.135:9092") // 指定响应方式
    //prop.put("acks", "0")
    prop.put("acks", "all")
    // 请求失败重试次数
    //prop.put("retries", "3")
    // 指定key的序列化方式, key是用于存放数据对应的offset
    prop.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    // 指定value的序列化方式
    prop.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    // 配置超时时间
    prop.put("request.timeout.ms", "60000")
    //prop.put("batch.size", "16384")
    //prop.put("linger.ms", "1")
    //prop.put("buffer.memory", "33554432")
//    同步发送
//    prop.put("producer.type", "sync")
//    异步方式发送
    prop.put("producer.type", "async")

    // 得到生产者的实例
    val producer = new KafkaProducer[String, String](prop)

//    模拟一些数据并发送给kafka
//    同步发送消息
    for (i <- 21 to 40) {
      val rmd = producer.send(new ProducerRecord[String, String]("test1", "hello" + i, "value:" + i)).get()
      println(rmd.toString)
      Thread.sleep(10)
    }


//    异步发送消息
    for (i <- 0 to 21) {
      val producerRecord = new ProducerRecord[String,String]("test1", "hello:" + i, "value:" + i)
      producer.send(producerRecord, new TrainCallback(producerRecord))
    }
    producer.close()
  }

//  回调类
  class TrainCallback(producerRecord: ProducerRecord[String,String]) extends Callback{
    override def onCompletion(recordMetadata: RecordMetadata, e: Exception) = {
      if (recordMetadata != null){
        val partitionNum = recordMetadata.partition()
        val keySize = recordMetadata.serializedKeySize()
        val valueSize = recordMetadata.serializedValueSize()
        val offset = recordMetadata.offset()
//        println("Callback:{\"partitionNum\"=" + partitionNum +
//          ",\"keySize\"=" + keySize +
//          ",\"valueSize\"=" + valueSize +
//          ",\"offset\"=" + offset +
//          ",\"key\"=" + producerRecord.key +
//          ",\"value\"=" + producerRecord.value + "}")
        println("ok")
        if (e != null){
          e.printStackTrace()
        }
      }
    }
  }

}