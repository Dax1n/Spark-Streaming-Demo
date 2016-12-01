package com.sparkstreaming.direct

import kafka.serializer.StringDecoder
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Created by Dax1n on 2016/12/1.
  */
object DirectCreateDstream1 {
  val kafkaParams = Map[String, String](
    "metadata.broker.list" -> "node1:9092,node1:9093,node1:9094",
    "group.id" -> "onlyOneCk1")

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
    conf.setAppName("LocalDirect").setMaster("local[2]")
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")


	
    def createStreamingContext():StreamingContext={
      val ssc = new StreamingContext(sc, Seconds(2))
      ssc.checkpoint("C:\\streamingcheckpoint1")
      val dStream = KafkaUtils.createDirectStream[String,String,StringDecoder,StringDecoder](ssc,kafkaParams,Set("orderNumOnlyOne1"))

      val dStream1 = dStream.map{
        x=>
          x._1+" - "+x._2
      }

      dStream1.print()
      ssc
    }

// 重重注意：对于Spark的Transform和Action都要写在getOrCreate的createStreamingContext函数中，否则报错！！！，此处更多技巧看官方文档
//官网地址：http://spark.apache.org/docs/latest/streaming-programming-guide.html的 Checkpointing 章节
//
    val ssc = StreamingContext.getOrCreate("C:\\streamingcheckpoint1",createStreamingContext _)
//错误信息：
//16/12/01 09:04:38 ERROR streaming.StreamingContext: Error starting the context, marking it as stopped
//org.apache.spark.SparkException: org.apache.spark.streaming.dstream.MappedDStream@4c2a67cc has not been initialized
	
    ssc.start()
    ssc.awaitTermination()






  }

}
