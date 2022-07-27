import akka.actor.typed.receptionist.{Receptionist, ServiceKey}
import akka.actor.typed.{ActorRef, Behavior}
import akka.actor.typed.scaladsl.{Behaviors, LoggerOps}
import utils.OrderUtils.OrderInfo

object Driver {
  /**
   * MESSAGES
   * commands: DeliverOrder, Availability
   * responses: OrderComplete
   *
   */
  trait Command
  case class DeliverOrder(orderInfo: OrderInfo) extends Command

  trait Response
  case class OrderComplete(orderInfo: OrderInfo)

  def apply(location: String): Behavior[Command] = Behaviors.setup { context =>
    val locationServiceKey = ServiceKey[Command](location)

    context.system.receptionist ! Receptionist.Register(locationServiceKey, context.self)

    Behaviors.receiveMessage {
      case DeliverOrder(orderInfo) =>
        context.log.info(s"${context.self.path.name} received message: $orderInfo")
        // context.system.receptionist ! Receptionist.Deregister(locationServiceKey, context.self)
        Behaviors.same
      case OrderComplete(orderInfo) => ???
    }
  }

}
