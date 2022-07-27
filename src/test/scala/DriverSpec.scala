import akka.actor.testkit.typed.scaladsl.{LoggingTestKit, ScalaTestWithActorTestKit}
import akka.actor.typed.receptionist.{Receptionist, ServiceKey}
import akka.actor.typed.scaladsl.{Behaviors, Routers}
import org.scalatest.wordspec.AnyWordSpecLike
import utils.OrderUtils.{OrderInfo, OrderStatus}

class DriverSpec extends ScalaTestWithActorTestKit with AnyWordSpecLike {

  "Registered driver actor" must {
    val driverName = "Sam"
    val key = "Accra"
    val orderInfo = OrderInfo(id = 0, item = "bread", quantity = 1, status = OrderStatus.Open)
    testKit.spawn(Driver(key), driverName)

    "be in router group" in {

      LoggingTestKit
        .info(orderInfo.toString)
        .withMessageRegex(s"$driverName asked to deliver order: *")
        .withOccurrences(1)
        .expect {
          val serviceKey = ServiceKey[Driver.Command](key)
          val group = Routers.group(serviceKey)
          val router = testKit.spawn(group, "driver-group-a")
          router ! Driver.DeliverOrder(orderInfo)
        }
    }
  }

  "Deregistered driver actor" must {
    val driverName = "Sambeth"
    val key = "Accra"
    val orderInfo = OrderInfo(id = 0, item = "bread", quantity = 1, status = OrderStatus.Open)
    val driver = testKit.spawn(Driver(key), driverName)

    "log deregister message after receiving message" in {

      LoggingTestKit
        .debug(driver.path.toString)
        .withMessageRegex("Actor was deregistered*")
        .withOccurrences(1)
        .expect {
          val serviceKey = ServiceKey[Driver.Command](key)
          val group = Routers.group(serviceKey)
          val router = testKit.spawn(group, "driver-group-b")
           router ! Driver.DeliverOrder(orderInfo)
        }
    }
  }

}
