package ru.coursework.coursework;


import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Anton on 20.10.2015.
 */
public class Order implements Parcelable  {


    final static String LOG_TAG = "myLogs";
    public static final String ORDER_DATE = "date";

    private String title;
    private int id;
    private int  number_machine;
    private Date date;
    private boolean current; // запись явл. действующей или старой
    private boolean withIncident;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Log.d(LOG_TAG, "writeToParcel");

        dest.writeString(title);
        dest.writeInt(id);
        dest.writeInt(number_machine);
        dest.writeSerializable(date);
        dest.writeValue(current);
        dest.writeValue(withIncident);

    }

    private Order(Parcel in) {
        Log.d(LOG_TAG, "MyObject(Parcel parcel)");

        this.title = in.readString();
        this.id = in.readInt();
        this.number_machine = in.readInt();
        this.date =(Date) in.readSerializable();
        this.current = ( Boolean) in.readValue(null);
        this.withIncident = (Boolean) in.readValue(null);

    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel source) {
            Log.d(LOG_TAG, "createFromParcel");
            return new Order(source);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    public int getNumber_machine() {
        return number_machine;
    }

    public void setNumber_machine(int number_machine) {
        this.number_machine = number_machine;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isWithIncident() {
        return withIncident;
    }

    public void setWithIncident(boolean withIncident) {
        this.withIncident = withIncident;
    }

    @Override
    public String toString() {
        return title;
    }

    public  Order () {

        Log.d(LOG_TAG, "MyObject(String _s, int _i)");
//        id = UUID.randomUUID();



    }
}





