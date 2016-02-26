package ru.coursework.coursework.entity.Memento;

import java.util.ArrayList;

import ru.coursework.coursework.entity.Client;
import ru.coursework.coursework.entity.Order;

/**
 * Created by Anton on 21.02.2016.
 */
public class ListOrderFragmentIMemento implements IMemento {

    private ArrayList<Order> orders;
    private Client client;

    /**
     * Constructs a new instance of {@code Object}.
     */
    public ListOrderFragmentIMemento(ArrayList<Order> orders, Client client) {
        this.orders = orders;
        this.client = client;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
