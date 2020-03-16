package bootstrap
import java.time.LocalDateTime

import org.apache.spark._
import java.time.format.DateTimeFormatter
object SparkSessionFactory {

  //  def getSparkSession(): SparkContext = {
  val appName = "test" + DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm").format(LocalDateTime.now)

  val conf = new SparkConf().setAppName(appName).setMaster("local[4]").
    set("spark.executor.memory", "1g").
    set("spark.scheduler.mode", "FAIR").
    set("spark.scheduler.allocation.file","C:/fairscheduler.xml")
 val sc= new SparkContext(conf)

  val appname=sc.appName

}


