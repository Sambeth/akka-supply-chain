package utils

package object OrderUtils {

  enum OrderStatus:
    case Open, Complete, Partial, Rejected

  case class OrderInfo(id: Int, item: String, quantity: Int, status: OrderStatus)
}
