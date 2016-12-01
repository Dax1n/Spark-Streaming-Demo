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


    val ssc = StreamingContext.getOrCreate("C:\\streamingcheckpoint1",createStreamingContext _)

    // 如果每次都是重新创建StreamingContext 这样不会容错，因为每一次StreamingContext都是新的。
    //我们应该存在StreamingContext的直接使用，不存在的话在根据checkpoint创建
    //  val ssc =  new StreamingContext(sc, Seconds(2))






    ssc.start()
    ssc.awaitTermination()






  }

}
