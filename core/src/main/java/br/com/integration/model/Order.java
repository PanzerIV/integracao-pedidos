package br.com.integration.model;

import java.util.Date;
import java.util.List;

public class Order {
    private int orderId;
    private float total;
    private Date date;
    private List<Product> products;
}
