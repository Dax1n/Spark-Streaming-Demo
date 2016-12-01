# Spark-Streaming-Demo
## 本仓库关于Spark Streaming相关项目演示程序
### 涉及Spark Streaming集合Kafka相关问题，例如：
####1、At most once - 每条数据最多被处理一次（0次或1次），这种语义下会出现数据丢失的问题；
####2、At least once - 每条数据最少被处理一次 (1次或更多)，这个不会出现数据丢失，但是会出现数据重复；
####3、Exactly once - 每条数据只会被处理一次，没有数据会丢失，并且没有数据会被多次处理，这种语义是大家最想要的，但是也是最难实现的。
