package ru.coursework.coursework.UI.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.coursework.coursework.entity.MakeOrderFragmentMemento;
import ru.coursework.coursework.entity.Memento;
import ru.coursework.coursework.helper.Helper;
import ru.coursework.coursework.entity.Order;
import ru.coursework.coursework.R;


/**
 * Created by Anton on 19.11.2015.
 */
public class MakeOrderFragment extends CustomFragment {

    public static final int REQUEST_DATE = 0;

    private static final String DIALOG_DATE = "DialogDate";
    public static final String NEW_ORDER_FOR_SAFE = "NewOrderForSafe";

    //переменные уровня View
    private TextView dateTextView;
    private ListView mlistView;

    //переменные уровня Модель
    private Date date;
    private ArrayList<Order> weekOfOrders;
    private Order order;


    @Override
    public Memento createMemento() {

        return new MakeOrderFragmentMemento();
    }

    public void setMemento(Memento state2){
        //проверку класса на соотвествие с нужным хранителем
        MakeOrderFragmentMemento state = (MakeOrderFragmentMemento)state2;

            this.date = state.getDate();
            this.weekOfOrders = state.getWeekOfOrders();
            this.order = state.getOrder();

    }

    static class ViewHolder {
        @Bind(R.id.timeOrder_textview_listview) TextView TimeOrder;

        @Bind(R.id.number_machine_textview_listview) TextView numberMachine;
        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getActivity().setTitle("");

        if (date == null)
            date= new Date();

    }

    private class OrderAdapter extends ArrayAdapter<Order> {


        public OrderAdapter(ArrayList<Order> orders) {
            super(getActivity(), 0, orders);

        }

        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
            if (convertView != null) {
                holder = (ViewHolder) convertView.getTag();
            } else {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.make_order_listview, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            }

            Order ord = getItem(position);

            holder.TimeOrder.setText(ord.getTitle());
            holder.numberMachine.setText("Стиральная машина №" + ord.getNumber_machine());

            return convertView;
        }






    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.make_order, container, false);


        //Формируем список времени при первом создания Activity ( начальная дата)
        CreateWeekOrders(date);

        OrderAdapter adapter = new OrderAdapter(weekOfOrders);

        mlistView = (ListView) v.findViewById(R.id.listViewTime);

        mlistView.setAdapter(adapter);


        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {

                order = weekOfOrders.get(position);

                View v = LayoutInflater.from(getActivity())
                        .inflate(R.layout.dialog_for_makeorder_listview, null);

                SuccessDialogHolder holder = new SuccessDialogHolder(v);
                v.setTag(holder);

                Calendar calendar = new GregorianCalendar();
                calendar.setTime(date);


                Helper.updateTextViewbyDate(holder.DialogDateOrder, date);
                holder.DialogNumberMashineOrder.setText("Стиральная машина №" + String.valueOf(order.getNumber_machine()));
                holder.DialogTimeOrder.setText(order.getTitle());

                Dialog dialog = new AlertDialog.Builder(getActivity())
                        .setView(v)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent();
                                i.putExtra(NEW_ORDER_FOR_SAFE, order);
                                getActivity().setResult(getActivity().RESULT_OK,i);
                                getActivity().finish();
                            }
                        })
                        .create();
                dialog.show();
            }
        });


        return v;
    }
    static class SuccessDialogHolder {
        @Bind(R.id.dialog_date_textview) TextView DialogDateOrder;
        @Bind(R.id.dialog_time_textview) TextView DialogTimeOrder;


        @Bind(R.id.dialog_number_mashine_textview) TextView DialogNumberMashineOrder;
        public SuccessDialogHolder(View view) {
            ButterKnife.bind(this, view);
        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode!= Activity.RESULT_OK)
        return;

        if (requestCode==REQUEST_DATE){
            date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            Helper.updateTextViewbyDate(dateTextView, date);
            CreateWeekOrders(date); //обовляем список для выбора нового заказа, после выбора новой даты


        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        //Настройка view для иконки
        inflater.inflate(R.menu.make_order, menu);

        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.date_picker_toolbar_item, null);





        dateTextView = (TextView) v.findViewById(R.id.editText_menu);

        //Удобный вывод даты
        Helper.updateTextViewbyDate(dateTextView, date);





        dateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(date);
                dialog.setTargetFragment(MakeOrderFragment.this,REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });





        //Замена иконки на view
        MenuItem text = (MenuItem) menu.findItem(R.id.menu_for_choose_order_date);

        // text.setActionView(v); при API > 11

        MenuItemCompat.setActionView(text, v); //т.к. APP < 11


    }

    private void CreateWeekOrders (Date date){
        weekOfOrders = new ArrayList<Order>();

        for (int i = 1; i <= 7; i++) {
            Order ord = new Order();
            ord.setDate(date);
            ord.setTitle(String.valueOf(9 + i)+":00");
            ord.setNumber_machine(new Random().nextInt(13) + 1);

            weekOfOrders.add(ord);
        }


    }



}
