import akka.actor.typed.{ActorRef, Behavior}
import akka.actor.typed.scaladsl.Behaviors
import utils.OrderUtils.OrderInfo

object Driver {
  /**
   * MESSAGES
   * commands: DeliverOrder, Availability
   * responses: OrderComplete
   *
   */
  trait Command
  case class DeliverOrder(orderInfo: OrderInfo, replyTo: ActorRef[Command]) extends Command
  case object Availability extends Command

  trait Response
  case class OrderComplete(orderInfo: OrderInfo)

  def apply(): Behavior[Command] = Behaviors.receive[Command] { (context, message) =>
    message match {
      case DeliverOrder(orderInfo, replyTo) => ???
      case Availability => ???
      case OrderComplete(orderInfo) => ???
    }
  }

}
