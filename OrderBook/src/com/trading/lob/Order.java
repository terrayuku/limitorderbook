package com.trading.lob;

import java.util.Date;
import java.util.Optional;

public class Order implements Comparable<Order> {
    private double quantity;

    private double price;

    private int id = -1;

    private Side side;

    private final Date dateTimeOfOrder;

    public static class Builder {

        private double quantity;

        private double price;

        private final Side side;

        private Date dateTimeOfOrder;

        public Builder(final Side pSide) {
            this.side = pSide;
        }

        public Builder withQuantity(final double amt) {
            this.quantity = amt;
            return this;
        }

        public Builder atPrice(final double pPrice) {
            this.price = pPrice;
            return this;
        }

        public Order build() {
            return new Order(this.quantity, this.price, this.side,
                    Optional.ofNullable(this.dateTimeOfOrder));
        }

    }

    private Order(final double nAmount, final double nPrice,final Side nSide,
                  final Optional<Date> pDateTimeOfOrder) {

        this.quantity = nAmount;
        this.price = nPrice;
        this.id += 1;
        this.side = nSide;
        this.dateTimeOfOrder = pDateTimeOfOrder.orElse(new Date());
    }

    public double getQuantity() {
        return this.quantity;
    }

    public void setQuantity(final double pAmount) {
        this.quantity = pAmount;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(final double pPrice) {
        this.price = pPrice;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int pId) {
        this.id = pId;
    }

    public Side getSide() {
        return this.side;
    }

    public void setSide(Side pSide) {
        this.side = pSide;
    }

    public Date getDateTimeOfOrder() {
        return this.dateTimeOfOrder;
    }

    @Override
    public int compareTo(Order o) {

        if (Double.compare(this.getPrice(), o.getPrice()) == 0) {
            if (this.getDateTimeOfOrder().before(o.getDateTimeOfOrder())) {
                return -1;
            } else {
                return 1;
            }

        } else {
            return Double.compare(this.getPrice(), o.getPrice());
        }
    }

}
