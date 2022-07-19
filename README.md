```mermaid
  graph TD;
      Facility-->OrderManager;
      OrderManager-->Order;
      Order-->OrderManager;
      Order-->DriverManager;
      DriverManager-->Driver;
      Driver-->Facility;
      OrderManager-->Facility;
      DriverManager-->OrderManager;
      Driver-->DriverManager;
      Facility-->Driver;
```