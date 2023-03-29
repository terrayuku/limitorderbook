package com.trading.lob;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class OrderBookTests {
    OrderBook orderBook = null;
    Order bid, sell = null;

    @Before
    public void setUpOrderBook() {
        orderBook = new OrderBook();
        bid = new Order(3, 34, Side.BUY);
        sell = new Order(5, 35, Side.SELL);

        orderBook.addOrder(bid);
        orderBook.addOrder(sell);
    }

    @Test
    public void testModifyingBiddingOrderOperation() {
        Optional<Order> modifiedOrder = orderBook.modifyOrderBook(38, bid);

        assertTrue(modifiedOrder.isPresent());
        assertEquals(modifiedOrder.get().getQuantity(), 38);
        assertFalse(orderBook.getAllBiddingOrders().contains(bid));
    }

    @Test
    public void testModifyingSellingOrderOperation() {
        Optional<Order> modifiedOrder = orderBook.modifyOrderBook(48, sell);

        assertTrue(modifiedOrder.isPresent());
        assertEquals(modifiedOrder.get().getQuantity(), 48);
        assertFalse(orderBook.getAllBiddingOrders().contains(sell));
    }

    @Test
    public void testIfBiddingOrderIsAdded() {
        assertNotNull(orderBook.getBiddingOrderById(0));
    }

    @Test
    public void testIfSellingOrderIsAdded() {
        assertNotNull(orderBook.getSellingOrderById(0));
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
