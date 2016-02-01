package ru.coursework.coursework;

import android.content.Context;

import java.sql.Time;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.UUID;

/**
 * Created by Anton on 22.10.2015.
 */
public class OrderLab {
    private static  OrderLab sOrderLab;
    private Context mAppContex;

    private ArrayList<Order> Orders;

    private OrderLab (Context appContext){
        mAppContex = appContext;
        Orders = new ArrayList<Order>();

        for (int i = 1; i < 100; i++) {
            Order ord = new Order();
            Date date = new GregorianCalendar(2015,i % 12, i % 30).getTime();
            ord.setDate(date);
            ord.setTitle("Запись №" + i);

            ord.setWithIncident(i % 5 == 1);
            ord.setNumber_machine(new Random().nextInt(13)+1);

            Orders.add(ord);


        }
    }

    public static OrderLab Instance(Context c){
        if (sOrderLab == null) sOrderLab = new OrderLab(c.getApplicationContext());

        return sOrderLab;
    }

    public ArrayList<Order> getOrders() {
        return  Orders;
    }

//    public Order getOrder (int id){
//        for (Order ord : Orders)
//            if (ord.getId().equals(id))
//                return ord;
//        return null;
//    }


}

