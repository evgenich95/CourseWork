package ru.coursework.coursework;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Anton on 05.12.2015.
 */
public class Client {

    private int id;
    private int NumberOfRoom;
    private Order CurrentOrder;
    private ArrayList<Order> PastOrder;
    private String FIO;


    public Client(){
//        CurrentOrder = (new Order());
//        CurrentOrder.setDate(new Date());
//        CurrentOrder.setNumber_machine(13);
//        CurrentOrder.setTitle("18:00");

        FIO = "Петров Иван Сергеевич";

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfRoom() {
        return NumberOfRoom;
    }

    public void setNumberOfRoom(int numberOfRoom) {
        NumberOfRoom = numberOfRoom;
    }

    public Order getCurrentOrder() {
        return CurrentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        CurrentOrder = currentOrder;
    }


    public ArrayList<Order> getPastOrder() {
        return PastOrder;
    }

    public void setPastOrder(ArrayList<Order> pastOrder) {
        PastOrder = pastOrder;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }
}
