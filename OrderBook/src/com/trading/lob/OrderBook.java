package com.trading.lob;

import java.util.*;

public class OrderBook {
    private TreeMap<Integer, ListOfOrders> buyOrders;
    private TreeMap<Integer, ListOfOrders> sellOrders;

    public OrderBook() {
        this.buyOrders = new TreeMap<>();
        this.sellOrders = new TreeMap<>();
    }

    public void addOrder(Order order) {
        if(order.getOrderSide() == Side.BUY) {
            addOrderToTable(order.getId(), order, buyOrders);
        } else if(order.getOrderSide() == Side.SELL) {
            addOrderToTable(order.getId(), order, sellOrders);
        }
    }

    public void addOrderToTable(int id, Order order, TreeMap<Integer, ListOfOrders> sharesTable) {
        if(sharesTable.containsKey(id)) {
            ListOfOrders orders = sharesTable.get(id);
            orders.addOrder(order);
        } else {
            ListOfOrders orders = new ListOfOrders();
            orders.addOrder(order);
            sharesTable.put(id, orders);
        }
    }

    public void removeOrder(Order order) {
        if(order.getOrderSide() == Side.BUY) {
            removeOrderFromTable(order.getId(), order, buyOrders);
        } else if (order.getOrderSide() == Side.SELL) {
            removeOrderFromTable(order.getId(), order, sellOrders);
        }
    }

    public void removeOrderFromTable(int id, Order order, TreeMap<Integer, ListOfOrders> sharesTable) {
            sharesTable.forEach((key, value) -> value.removeOrder(id));
    }

    public Optional<Order> modifyOrderBook(int quantity, Order order) {
        if(order.getOrderSide() == Side.BUY) {
            return modifyOrderBookFromTable(order.getId(), quantity, buyOrders);
        } else if (order.getOrderSide() == Side.SELL) {
            return modifyOrderBookFromTable(order.getId(), quantity, sellOrders);
        }
        return null;
    }

    public Optional<Order> modifyOrderBookFromTable(int id, int quanity, TreeMap<Integer, ListOfOrders> sharesTable) {
        Order modifiedOrder  = null;
        for (Map.Entry<Integer, ListOfOrders> entry : sharesTable.entrySet()) {
            Integer key = entry.getKey();
            ListOfOrders value = entry.getValue();
            modifiedOrder = value.modifyOrder(id, quanity);
        }

        return Optional.of(modifiedOrder);
    }

    public ListOfOrders getBiddingOrderById(int id)  {
        return this.buyOrders.get(id);
    }

    public ListOfOrders getSellingOrderById(int id)  {
        return this.sellOrders.get(id);
    }
    public Set<Map.Entry<Integer, ListOfOrders>> getAllBiddingOrders() {
        return this.buyOrders.entrySet();
    }

    public Set<Map.Entry<Integer, ListOfOrders>> getAllSellingOrders() {
        return this.sellOrders.entrySet();
    }

}
