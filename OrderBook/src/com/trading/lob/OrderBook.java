package com.trading.lob;

import java.util.*;

public class OrderBook {
    private final TreeMap<Integer, ListOfOrders> buyOrders;
    private final TreeMap<Integer, ListOfOrders> sellOrders;

    public OrderBook() {
        this.buyOrders = new TreeMap<>(new PriceSorting());
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
            removeOrderFromTable(order.getId(), buyOrders);
        } else if (order.getOrderSide() == Side.SELL) {
            removeOrderFromTable(order.getId(), sellOrders);
        }
    }

    public void removeOrderFromTable(int id, TreeMap<Integer, ListOfOrders> sharesTable) {
            sharesTable.forEach((key, value) -> value.removeOrder(id));
    }

    public Optional<Order> modifyOrderBook(int quantity, Order order) {
        if(order.getOrderSide() == Side.BUY) {
            return modifyOrderBookFromTable(order.getId(), quantity, buyOrders);
        } else if (order.getOrderSide() == Side.SELL) {
            return modifyOrderBookFromTable(order.getId(), quantity, sellOrders);
        }
        return Optional.empty();
    }

    public Optional<Order> modifyOrderBookFromTable(int id, int quanity, TreeMap<Integer, ListOfOrders> sharesTable) {
        Order modifiedOrder  = null;
        for (Map.Entry<Integer, ListOfOrders> entry : sharesTable.entrySet()) {
            ListOfOrders value = entry.getValue();
            modifiedOrder = value.modifyOrder(id, quanity);
        }

        assert modifiedOrder != null;
        return Optional.of(modifiedOrder);
    }

    public ListOfOrders getBiddingOrderById(int priority)  {
        return this.buyOrders.get(priority);
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
