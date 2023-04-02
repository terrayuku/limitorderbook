package com.trading.lob;

public class Trade {

    private int buyersOrderId;

    private int sellersOrderId;

    private double amount;

    private double price;

    public Trade(int pTakerOrderId, int pMakerOrderId,
                 final double pAmount, final double pPrice) {
        super();
        this.buyersOrderId = pTakerOrderId;
        this.sellersOrderId = pMakerOrderId;
        this.amount = pAmount;
        this.price = pPrice;
    }

    public int getBuyersOrderId() {
        return this.buyersOrderId;
    }

    public void setBuyersOrderId(int pTakerOrderId) {
        this.buyersOrderId = pTakerOrderId;
    }

    public int getSellersOrderId() {
        return this.sellersOrderId;
    }

    public void setSellersOrderId(int pMakerOrderId) {
        this.sellersOrderId = pMakerOrderId;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(final double pAmount) {
        this.amount = pAmount;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(final double pPrice) {
        this.price = pPrice;
    }
}
