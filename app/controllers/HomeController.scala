package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import bootstrap._
import org.apache.spark._
import scala.util.{Success, Failure}
import scala.concurrent.{ExecutionContext, Future}

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
//@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index(""))

  }

  def userJob() = Action { implicit request: Request[AnyContent] =>
    implicit val ec = ExecutionContext.global
    val future=Future {
      val sc=SparkSessionFactory.sc

      sc.setJobGroup("userJob","low priority Job")
      sc.setLocalProperty("spark.scheduler.pool", "production")
      val input1 = sc.parallelize(1 to 1000000, 5)
      val evenNumbers = input1.filter(nr => {
        val isEven = nr % 2 == 0
        if (nr == 180000) {
          Thread.sleep(180000)
        }
        isEven
      })
    val evenNumberCount=   evenNumbers.count()
      evenNumberCount
    }
    future.onComplete{
      case Success(even)=> Ok(views.html.index("user Job +"+even))
      case Failure(exception) =>Ok(views.html.index("user Job +"+exception))
    }

//    new Thread(new Runnable() {
//      override def run(): Unit={
//        val sc=SparkSessionFactory.sc
//
//        sc.setJobGroup("userJob","low priority Job")
//        sc.setLocalProperty("spark.scheduler.pool", "production")
//        val input1 = sc.parallelize(1 to 1000000, 5)
//        val evenNumbers = input1.filter(nr => {
//          val isEven = nr % 2 == 0
//          if (nr == 180000) {
//            Thread.sleep(180000)
//          }
//          isEven
//        })
//        evenNumbers.count()
//
//
//      }
//    }).start()

    Ok(views.html.index("user Job"))

  }

  def adminJob() = Action { implicit request: Request[AnyContent] =>
    implicit val ec = ExecutionContext.global
    val future=Future {
      val sc=SparkSessionFactory.sc

      sc.setJobGroup("adminJob","high priority Job")
      sc.setLocalProperty("spark.scheduler.pool", "test")
      val input1 = sc.parallelize(1 to 1000000, 5)
      val evenNumbers = input1.filter(nr => {
        val isEven = nr % 2 == 0
        if (nr == 180000) {
         // Thread.sleep(60000)
        }
        isEven
      })
      val evenNumberCount=   evenNumbers.count()
      evenNumberCount
    }
    future.onComplete{
      case Success(even)=> Ok(views.html.index("admin Job +"+even))
      case Failure(exception) =>Ok(views.html.index("admin Job +"+exception))
    }



//    new Thread(new Runnable() {
//      override def run(): Unit={
//        val sc=SparkSessionFactory.sc
//        sc.setJobGroup("adminJob","high priority Job")
//        sc.setLocalProperty("spark.scheduler.pool", "test")
//        val input2 = sc.parallelize(1000000 to 2000000, 5)
//        val oddNumbers = input2.filter(nr => {
//          val isOdd = nr % 1000000 != 0
//          if (nr == 1050000) {
//            Thread.sleep(60000)
//          }
//          isOdd
//        })
//        oddNumbers.count()
//
//      }
//    }).start()
    Ok(views.html.index("admin Job"))

  }



}
