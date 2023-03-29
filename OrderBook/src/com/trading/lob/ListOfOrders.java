package com.trading.lob;

import java.util.LinkedList;

public class ListOfOrders {

    private LinkedList<Order> orders;
    int id = 0;

    public ListOfOrders() {
        this.orders = new LinkedList<>();
    }

    public void addOrder(Order order) {
        this.id++;
        order.setId(this.id);
        this.orders.add(order);
    }

    public Order modifyOrder(int id, int quantity) {
        Order modifiedOrder = new Order();
        this.orders.forEach(order -> {
            if(order.getId() == id)  {
                order.setQuantity(quantity);
                modifiedOrder.setOrderSide(order.getOrderSide());
                modifiedOrder.setQuantity(order.getQuantity());
                modifiedOrder.setPrice(order.getPrice());
            }
        });

        this.orders.removeIf(order -> order.getId() == id);

        return modifiedOrder;
    }

    public void removeOrder(int id) {
        this.orders.removeIf(order -> order.getId() == id);
    }

    public boolean isEmpty() {
        return this.orders.isEmpty();
    }

    public Order getFirstOrder() {
        return this.orders.getFirst();
    }

    public Order getLastOrder() {
        return this.orders.getLast();
    }

    public LinkedList<Order> getOrders() {
        return orders;
    }
}
