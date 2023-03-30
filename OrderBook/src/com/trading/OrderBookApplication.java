package com.trading;
import com.trading.lob.Order;
import com.trading.lob.OrderBook;
import com.trading.lob.Side;

import java.util.Optional;

public class OrderBookApplication {

    static OrderBook orderBook = null;
    static OrderBook orderBooks = null;
    public static void main(String[] args) {
        orderBook = new OrderBook();
        Order bid1 = new Order(9, 40, Side.BUY);
        Order bid2 = new Order(8, 30, Side.BUY);
        Order bid3 = new Order( 7, 50, Side.BUY);
        Order bid4 = new Order( 3, 10, Side.BUY);

        Order sell1 = new Order(10, 5, Side.SELL);
        Order sell2 = new Order(11, 40, Side.SELL);
        Order sell3 = new Order(12, 20, Side.SELL);

        orderBook.addOrder(bid3);
        orderBook.addOrder(bid2);
        orderBook.addOrder(bid1);

        orderBook.addOrder(sell1);
        orderBook.addOrder(sell2);
        orderBook.addOrder(sell3);

        // removing order
//        orderBook.removeOrder(bid3);

        // modify order
//        modifyOrder(48, bid1);

        // adding order
//        orderBook.addOrder(bid4);

        System.out.println("Priority \t Id \t Qty \t Price");

        printBuyingOrders();

        System.out.println("====================================");

        modifyOrder(55, sell2);

        printSellingOrders();
    }

    private static void modifyOrder(int quantity, Order order) {
        Optional<Order> modifiedOrder = orderBook.modifyOrderBook(quantity, order);
        if(modifiedOrder.isPresent()) {
            orderBook.addOrder(modifiedOrder.get());
        } else  {
            System.out.println("Could not modify buying order with id " + order.getId());
        }
    }

    private static void printBuyingOrders() {
        orderBook.getAllBiddingOrders().forEach(order ->
                order.getValue().getOrders().forEach(o ->
                        System.out.println(order.getKey() + "\t\t\t" + o.getId() + "\t\t" + o.getQuantity() + "\t\t" +
                                o.getPrice())));

    }

    private static void printSellingOrders() {
        orderBook.getAllSellingOrders().forEach(order ->
                order.getValue().getOrders().forEach(o ->
                        System.out.println(order.getKey() + "\t\t\t" + o.getId() + "\t\t" + o.getQuantity() + "\t\t" +
                                o.getPrice())));
    }
}
