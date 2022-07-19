```mermaid
  graph TD;
      Facility-->OrderManager;
      OrderManager-->Order;
      Order-->OrderManager;
      Order-->DriverManager;
      DriverManager-->Driver;
      Driver-->Facility;
      OrderManager-->Facility;
      DriverManager-->Order;
      Driver-->DriverManager;
      Facility-->Driver;
```