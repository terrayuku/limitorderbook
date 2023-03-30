package com.trading.lob;

import java.util.Objects;

public class Order {
    private int id = 0;

    private int quantity;
    private int price;
    private Side orderSide;

    public Order() {
    }

    public Order(int price, int quantity, Side orderSide) {
        this.price = price;
        this.quantity = quantity;
        this.orderSide = orderSide;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Side getOrderSide() {
        return orderSide;
    }

    public void setOrderSide(Side orderSide) {
        this.orderSide = orderSide;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", price=" + price +
                ", orderSide=" + orderSide +
                '}';
    }
}
