package com.trading.lob;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class OrderBookTests {
    OrderBook orderBook = null;
    Order bid, sell = null;

    ArrayList<Order> buyOrders, sellOrders = null;

    @Before
    public void setUpOrderBook() {
        orderBook = new OrderBook();
        buyOrders = new ArrayList<>();
        sellOrders = new ArrayList<>();

        bid = new Order.Builder(Side.BUY).atPrice(3).withQuantity(35).build();
        sell = new Order.Builder(Side.SELL).atPrice(5).withQuantity(35).build();

        buyOrders.add(bid);
        orderBook.setBuyOrders(buyOrders);

        sellOrders.add(sell);
        orderBook.setSellOrders(sellOrders);
    }

    @Test
    public void testModifyingBiddingOrderOperation() {
        Optional<Order> modifiedOrder = orderBook.modifyOrderBook(38, bid);

        assertTrue(modifiedOrder.isPresent());
        assertFalse(orderBook.getAllBiddingOrders().contains(bid));
    }

    @Test
    public void testModifyingSellingOrderOperation() {
        Optional<Order> modifiedOrder = orderBook.modifyOrderBook(48, sell);

        assertTrue(modifiedOrder.isPresent());
        assertFalse(orderBook.getAllBiddingOrders().contains(sell));
    }

    @Test
    public void testIfBiddingOrderIsAdded() {
        assertNotNull(orderBook.getAllBiddingOrders());
    }

    @Test
    public void testIfSellingOrderIsAdded() {
        assertNotNull(orderBook.getAllSellingOrders());
    }

    @Test
    public void testGetAllBiddingOrders() {
        assertEquals(orderBook.getAllBiddingOrders().size(), 1);
    }

    @Test
    public void testGetAllSellingOrders() {
        assertEquals(orderBook.getAllSellingOrders().size(), 1);
    }
}
