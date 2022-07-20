package utils

package object OrderUtils {

  enum OrderStatus:
    case Complete, Partial, Rejected

  case class OrderInfo(id: Int, item: String, quantity: Int, status: OrderStatus)
}
