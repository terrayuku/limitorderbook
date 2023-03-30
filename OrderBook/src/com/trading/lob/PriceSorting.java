package com.trading.lob;

import java.util.Comparator;

public class PriceSorting implements Comparator<Integer> {
    @Override
    public int compare(Integer order1Price, Integer order2Price) {
        return order2Price.compareTo(order1Price);
    }
}
