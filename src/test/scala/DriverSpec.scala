import akka.actor.testkit.typed.scaladsl.{LoggingTestKit, ScalaTestWithActorTestKit}
import akka.actor.typed.receptionist.{Receptionist, ServiceKey}
import akka.actor.typed.scaladsl.{Behaviors, Routers}
import org.scalatest.wordspec.AnyWordSpecLike
import utils.OrderUtils.{OrderInfo, OrderStatus}

class DriverSpec extends ScalaTestWithActorTestKit with AnyWordSpecLike {

  "Driver" must {
    val key = "Accra"
    val driverName = "Sam"
    val orderInfo = OrderInfo(id = 0, item = "bread", quantity = 1, status = OrderStatus.Open)
    testKit.spawn(Driver(key), driverName)

    "be in router group" in {

      LoggingTestKit
        .info(driverName)
        .withMessageRegex(s"$driverName asked to deliver order: *")
        .withOccurrences(1)
        .expect {
          val serviceKey = ServiceKey[Driver.Command](key)
          val group = Routers.group(serviceKey)
          val router = testKit.spawn(group, "driver-group")

          router ! Driver.DeliverOrder(orderInfo)
        }
    }

    "receive message as part of group" in {

    }

    "deregister from router group after receiving message" in {

    }

    "receive OrderInfo" in {

    }
  }

}
