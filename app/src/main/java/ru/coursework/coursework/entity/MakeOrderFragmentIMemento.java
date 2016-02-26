package ru.coursework.coursework.entity;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Anton on 21.02.2016.
 */
public class MakeOrderFragmentIMemento implements IMemento {

    private Date date;
    private ArrayList<Order> weekOfOrders;
    private Order order;


    public MakeOrderFragmentIMemento(Date date, ArrayList<Order> weekOfOrders, Order order) {
        this.date=date;
        this.weekOfOrders=weekOfOrders;
        this.order=order;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<Order> getWeekOfOrders() {
        return weekOfOrders;
    }

    public void setWeekOfOrders(ArrayList<Order> weekOfOrders) {
        this.weekOfOrders = weekOfOrders;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
