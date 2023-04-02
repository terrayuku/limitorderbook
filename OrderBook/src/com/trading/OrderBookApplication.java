package com.trading;
import com.trading.lob.Order;
import com.trading.lob.OrderBook;
import com.trading.lob.Side;

import java.util.ArrayList;
import java.util.Optional;

public class OrderBookApplication {

    static OrderBook orderBook = null;
    static OrderBook orderBooks = null;
    public static void main(String[] args) {
        orderBook = new OrderBook();
        System.out.println("Id \t Qty \t Price");

        addBuyers();
        System.out.println("=============Buying Orders==============");
        printBuyingOrders();

        System.out.println("=============Sellers Orders============");
//
//        modifyOrder(55, sell2);

        addSellers();
//
        printSellingOrders();

        Order newBid = new Order.Builder(Side.BUY)
                .atPrice(10)
                .withQuantity(20).build();

        orderBook.process(newBid);
        System.out.println("=======Buying Order and Matching===========");

        printSellingOrders();

        Order newSell = new Order.Builder(Side.SELL)
                .atPrice(15)
                .withQuantity(20).build();

        orderBook.process(newSell);
        System.out.println("=======Selling Order and Matching===========");

        printBuyingOrders();


        Order newOrder = new Order.Builder(Side.BUY).atPrice(6).withQuantity(100).build();
        Optional<Order> modifiedOrder = orderBook.modifyOrderBook(0, newOrder);
        modifiedOrder.ifPresent(order -> orderBook.process(order));


//    }
//
//    private static void modifyOrder(int quantity, Order order) {
//        Optional<Order> modifiedOrder = orderBook.modifyOrderBook(quantity, order);
//        if(modifiedOrder.isPresent()) {
//            orderBook.addOrder(modifiedOrder.get());
//        } else  {
//            System.out.println("Could not modify buying order with id " + order.getId());
//        }
    }

    private static void addBuyers() {
        ArrayList<Order> buyers = new ArrayList<>();
        Order bid1 = new Order.Builder(Side.BUY)
                .atPrice(20)
                .withQuantity(55).build();
        buyers.add(bid1);

        Order bid2 = new Order.Builder(Side.BUY)
                .atPrice(8)
                .withQuantity(30).build();
        buyers.add(bid2);

        Order bid3 = new Order.Builder(Side.BUY)
                .atPrice(6)
                .withQuantity(50).build();
        buyers.add(bid3);

        orderBook.setBuyOrders(buyers);

    }

    private static void addSellers() {
        ArrayList<Order> sellers = new ArrayList<>();
        Order sell1 = new Order.Builder(Side.SELL)
                .atPrice(3)
                .withQuantity(70).build();
        sellers.add(sell1);

        Order sell2 = new Order.Builder(Side.SELL)
                .atPrice(8)
                .withQuantity(10).build();
        sellers.add(sell2);

        Order sell3 = new Order.Builder(Side.SELL)
                .atPrice(6)
                .withQuantity(30).build();
        sellers.add(sell3);

        orderBook.setSellOrders(sellers);

    }
    private static void printBuyingOrders() {
        orderBook.getAllBiddingOrders().forEach(order ->
                System.out.println(order.getId() + "\t" + order.getQuantity() + "\t" +
                        order.getPrice()));

    }
    private static void printSellingOrders() {
        orderBook.getAllSellingOrders().forEach(order ->
                System.out.println(order.getId() + "\t" + order.getQuantity() + "\t" +
                        order.getPrice()));
    }
}
