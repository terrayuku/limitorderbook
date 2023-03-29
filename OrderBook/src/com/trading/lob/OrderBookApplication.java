package com.trading.lob;

public class OrderBookApplication {

    public static void main(String[] args) {
        Order bid1 = new Order(9, 40, Side.BUY);
        Order bid2 = new Order(8, 30, Side.BUY);
        Order bid3 = new Order( 7, 50, Side.BUY);

        Order sell1 = new Order(10, 5, Side.SELL);
        Order sell2 = new Order(11, 40, Side.SELL);
        Order sell3 = new Order(12, 20, Side.SELL);

        OrderBook orderBook = new OrderBook();
        orderBook.addOrder(bid1);
        orderBook.addOrder(bid2);
        orderBook.addOrder(bid3);

        orderBook.addOrder(sell1);
        orderBook.addOrder(sell2);
        orderBook.addOrder(sell3);

        System.out.println("Priority \t Id \t Qty \t Price \t Side");

//        orderBook.removeOrder(bid1);
        orderBook.modifyOrderBook(48, bid1);
        orderBook.getAllBiddingOrders().forEach(order -> {
            order.getValue().getOrders().forEach(o -> {
                System.out.println(order.getKey() + "\t\t\t" + o.getId() + "\t\t" + o.getQuantity() + "\t\t" + o.getPrice() + "\t\t" + o.getOrderSide());
            });
        });
        System.out.println("================================================");
        orderBook.getAllSellingOrders().forEach(order -> {
            order.getValue().getOrders().forEach(o -> {
                System.out.println(order.getKey() + "\t\t\t" + o.getId() + "\t\t" + o.getQuantity() + "\t\t" + o.getPrice() + "\t\t" + o.getOrderSide());
            });
        });
    }
}
