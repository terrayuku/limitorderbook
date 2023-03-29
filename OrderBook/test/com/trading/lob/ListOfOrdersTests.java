package com.trading.lob;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ListOfOrdersTests {

    ListOfOrders orders = null;
    Order order = null;

    @Before
    public void setup() {
        orders = new ListOfOrders();
        order = new Order(9, 40, Side.BUY);

        orders.addOrder(order);
    }

    @Test
    public void testAddingOrders() {
        assertEquals(orders.getOrders().getFirst().getId(), 1);
    }

    @Test
    public void testRemovingOrder() {
        orders.removeOrder(1);
        assertTrue(orders.isEmpty());
    }

    @Test
    public void testIfModifiedOrderIsRemovedFromCurrentList() {
        orders.modifyOrder(1, 200);
        assertTrue(orders.isEmpty());
    }

    @Test
    public void testGettingFirstOrder() {
        assertEquals(orders.getFirstOrder().getId(), 1);
    }

    @Test
    public void testGettingLastOrder() {
        Order o = new Order(3, 12, Side.BUY);
        orders.addOrder(o);

        assertEquals(orders.getLastOrder().getId(), 2);
    }

    @Test
    public void testGettingAllOrders() {
        assertEquals(orders.getOrders().size(), 1);
    }
}
