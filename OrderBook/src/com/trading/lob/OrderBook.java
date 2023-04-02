package com.trading.lob;

import java.util.*;

public class OrderBook {
    private List<Order> buyOrders;

    private List<Order> sellOrders;

    private double lastSalePrice;

    public OrderBook() {
        this.buyOrders = new ArrayList<>();
        this.sellOrders = new ArrayList<>();
    }

    public synchronized List<Trade> process(final Order pOrder) {
        if (pOrder.getSide() == Side.BUY) {
            return this.processLimitBuy(pOrder);
        } else {
            return this.processLimitSell(pOrder);
        }
    }

    private synchronized List<Trade> processLimitBuy(final Order order) {
        final ArrayList<Trade> trades = new ArrayList<>();

        if (this.sellOrders.size() != 0) {
            if (this.sellOrders.get(this.sellOrders.size() - 1).getPrice() <= order.getPrice()) {

                while (true) {
                    final Order sellOrder = this.sellOrders.get(0);
                    if (sellOrder.getPrice() > order.getPrice()) {
                        break;
                    }
                    if (sellOrder.getQuantity() >= order.getQuantity()) {
                        trades.add(new Trade(order.getId(), sellOrder.getId(),
                                order.getQuantity(), sellOrder.getPrice()));
                        sellOrder.setQuantity(
                                sellOrder.getQuantity() - order.getQuantity());
                        if (sellOrder.getQuantity() == 0) {
                            this.removeSellOrder(0);
                        }
                        this.setLastSalePrice(sellOrder.getPrice());
                        return trades;
                    }

                    if (sellOrder.getQuantity() < order.getQuantity()) {
                        trades.add(new Trade(order.getId(), sellOrder.getId(),
                                sellOrder.getQuantity(), sellOrder.getPrice()));
                        order.setQuantity(
                                order.getQuantity() - sellOrder.getQuantity());
                        this.removeSellOrder(0);
                        this.setLastSalePrice(sellOrder.getPrice());
                        continue;
                    }
                }
            }
        }

        this.buyOrders.add(order);

        Collections.sort(this.buyOrders);

        return trades;
    }

    private synchronized List<Trade> processLimitSell(final Order order) {

        final ArrayList<Trade> trades = new ArrayList<>();

        if (this.buyOrders.get(this.buyOrders.size() - 1).getPrice() >= order.getPrice()) {
            for (int i = 0; i >= 0; i++) {
                final Order buyOrder = this.buyOrders.get(0);

                if (buyOrder.getQuantity() >= order.getQuantity()) {
                    trades.add(new Trade(order.getId(), buyOrder.getId(),
                            order.getQuantity(), buyOrder.getPrice()));
                    buyOrder.setQuantity(
                            buyOrder.getQuantity() - order.getQuantity());
                    if (buyOrder.getQuantity() == 0) {
                        this.removeBuyOrder(0);
                    }
                    this.setLastSalePrice(buyOrder.getPrice());
                    return trades;
                }

                if (buyOrder.getQuantity() < order.getQuantity()) {
                    trades.add(new Trade(order.getId(), buyOrder.getId(),
                            buyOrder.getQuantity(), buyOrder.getPrice()));
                    order.setQuantity(order.getQuantity() - buyOrder.getQuantity());
                    this.removeBuyOrder(0);
                    this.setLastSalePrice(buyOrder.getPrice());
                    continue;
                }
            }
        }
        this.sellOrders.add(order);

        Collections.sort(this.sellOrders);

        return trades;
    }

    public Optional<Order> modifyOrderBook(int quantity, Order order) {
        if(order.getSide() == Side.BUY) {
            return modifyOrderBookFromTable(order.getId(), quantity, Side.BUY, this.buyOrders);
        } else if (order.getSide() == Side.SELL) {
            return modifyOrderBookFromTable(order.getId(), quantity, Side.SELL, this.sellOrders);
        }
        return Optional.empty();
    }

    public Optional<Order> modifyOrderBookFromTable(int id, int quantity, Side side, List<Order> sharesTable) {
        Order modifiedOrder  = new Order.Builder(side).build();
        sharesTable.forEach(order -> {
            if(order.getId() == id) {
                order.setQuantity(quantity);
            }

            modifiedOrder.setId(order.getId());
            modifiedOrder.setPrice(order.getPrice());
            modifiedOrder.setSide(order.getSide());
            modifiedOrder.setQuantity(quantity);

        });

        sharesTable.removeIf(order -> order.getId() == id);

        return Optional.of(modifiedOrder);
    }

    private synchronized void removeBuyOrder(final int index) {
        this.buyOrders.remove(index);
    }

    private synchronized void removeSellOrder(final int index) {
        this.sellOrders.remove(index);
    }

    public List<Order> getAllBiddingOrders() {
        return this.buyOrders;
    }

    public synchronized void setBuyOrders(final ArrayList<Order> pBuyOrders) {
        this.buyOrders = pBuyOrders;
    }

    public synchronized List<Order> getAllSellingOrders() {
        return this.sellOrders;
    }

    public synchronized void setSellOrders(final ArrayList<Order> pSellOrders) {
        this.sellOrders = pSellOrders;
    }

    public synchronized double getLastSalePrice() {
        return this.lastSalePrice;
    }

    public synchronized void setLastSalePrice(final double pLastSalePrice) {
        this.lastSalePrice = pLastSalePrice;
    }

}
