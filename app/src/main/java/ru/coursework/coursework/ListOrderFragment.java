package ru.coursework.coursework;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;

import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Anton on 18.10.2015.
 */
public class ListOrderFragment extends Fragment {


    private ViewFragment holder;

    public static final int REQUEST_ORDER = 0;

    private ListView mlistView;
    private ArrayList<Order> Orders;
    Client ExampleClient;
    ArrayList<View> views;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode!= Activity.RESULT_OK)
            return;

        if (requestCode==REQUEST_ORDER){

            ExampleClient.setCurrentOrder((Order) data.getParcelableExtra(MakeOrderFragment.NEW_ORDER_FOR_SAFE));
            UpdateDataOfClient(ExampleClient.getCurrentOrder());



        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.list_order_fragment, menu);


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        убираем вызов меню, т.к. исп ImageView
//        setHasOptionsMenu(true);
    }




    private class OrderAdapter extends ArrayAdapter<Order> {
        public OrderAdapter(ArrayList<Order> orders) {
            super(getActivity(), 0, orders);

        }

        public View getView(int position, View convertView, ViewGroup parent) {
            // Если мы не получили представление, заполняем его
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_order, null);
            }

            Order ord = getItem(position);

            TextView titleOrder = (TextView) convertView.findViewById(R.id.number_of_orderTextView);
            titleOrder.setText(ord.getTitle());

            TextView dateOrder = (TextView) convertView.findViewById(R.id.list_item_order_dateTextView);

            Helper.updateTextViewbyDate(dateOrder, ord.getDate());

            //На время не буду показывать картинку
//            ImageView withIcidentImage = (ImageView) convertView.findViewById(R.id.imageView);
//            if (ord.isWithIncident()) {
//                withIcidentImage.setImageResource(R.drawable.error);
//                //            }
//              withIcidentImage.setVisibility(View.VISIBLE);




            TextView numberMachine = (TextView) convertView.findViewById(R.id.list_item_order_numberMachTextView);
            numberMachine.setText("Стиральная машина №" + ord.getNumber_machine());
            return convertView;


        }

    }





    static class ViewFragment {

        @Bind(R.id.current_order) TextView CurrentOrder;
        @Bind(R.id.number_machine) TextView NumberMachine;
        @Bind(R.id.time_order) TextView TimeOrder;
        @Bind(R.id.date_order) TextView DateOrder;
        @Bind(R.id.listorder_textView_not_have_order) TextView  NotHaveOrder;
        @Bind(R.id.listView) ListView  mlistView;
        @Bind(R.id.current_orders) RelativeLayout currentblock;
        @Bind(R.id.add_order_ImageView) ImageView addOrderImageView;
        @Bind(R.id.imageViewRedact) ImageView imageViewRedact;
        @Bind(R.id.imageViewDelete) ImageView imageViewDelete;




        public ViewFragment(View view) {
            ButterKnife.bind(this, view);
        }
    }


    //
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getActivity().setTitle(R.string.title_ListOrderActivity);

        Orders = OrderLab.Instance(getActivity()).getOrders();

        ExampleClient = new Client();
        ExampleClient.setPastOrder(Orders);


        View view = LayoutInflater.from(getActivity())
                .inflate(R.layout.listorder, null);


        holder = new ViewFragment(view);
        view.setTag(holder);

        if (ExampleClient.getCurrentOrder()!=null)
                UpdateDataOfClient(ExampleClient.getCurrentOrder());


        OrderAdapter adapter = new OrderAdapter(ExampleClient.getPastOrder());


        holder.mlistView.setAdapter(adapter);

//        holder.addOrderImageView.


        //Создадим массис View видимость которых надо будет изменять
        views = new ArrayList<View>();

        views.add(holder.imageViewDelete);
        views.add(holder.imageViewRedact);
        views.add(holder.CurrentOrder);
        views.add(holder.DateOrder);
        views.add(holder.NumberMachine);
        views.add(holder.TimeOrder);



        holder.addOrderImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MakeOrderActivity.class);
                startActivityForResult(i, REQUEST_ORDER);
            }
        });




        holder.currentblock.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                holder.currentblock.setBackgroundColor(getResources().getColor(R.color.forLongSelection));
                holder.imageViewDelete.setVisibility(View.VISIBLE);
                holder.imageViewRedact.setVisibility(View.VISIBLE);


                return false;
            }
        });

        holder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Программируем Layuot для диалогового окна для подтверждения удаления

                TextView question = new TextView(getActivity());
                question.setText("Вы уверены, что хотите отменить текущую запись?");
                question.setGravity(Gravity.CENTER_HORIZONTAL);
                question.setTextSize(25);

                LinearLayout ll = new LinearLayout(getActivity());
                ll.setOrientation(LinearLayout.VERTICAL);
                ll.setGravity(Gravity.CENTER);
                ll.addView(question);


                View DialogView= new View(getActivity());
                DialogView.setId(R.id.deleteOrNo);
                DialogView = ll;

                ;




                //holder.NotHaveOrder.setVisibility(View.VISIBLE);
                Dialog dialog = new AlertDialog.Builder(getActivity())
                        .setView(DialogView)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //скрываем все данные

                                Helper.changeViewVisible(views, View.INVISIBLE);
                                //показываем, что записей нет
                                holder.NotHaveOrder.setVisibility(View.VISIBLE);
                                holder.currentblock.setBackgroundColor(getResources().getColor(R.color.white));

                        }
                        })
                        .setNegativeButton(android.R.string.cancel,null)
                        .create();
                dialog.show();



            }
        });








        return view;


    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_order:
                Intent i = new Intent(getActivity(), MakeOrderActivity.class);
                startActivityForResult(i, REQUEST_ORDER);

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void UpdateDataOfClient ( Order order){

//        View view = LayoutInflater.from(getActivity())
//                .inflate(R.layout.listorder,null);

//        ViewFragment holder = new ViewFragment(view);
//        view.setTag(holder);

        holder.TimeOrder.setText(order.getTitle());
        holder.NumberMachine.setText("Стиральная машина №" + order.getNumber_machine());
        Helper.updateTextViewbyDate(holder.DateOrder, order.getDate());

        holder.CurrentOrder.setVisibility(View.VISIBLE);
        holder.NumberMachine.setVisibility(View.VISIBLE);
        holder.TimeOrder.setVisibility(View.VISIBLE);
        holder.DateOrder.setVisibility(View.VISIBLE);

        holder.NotHaveOrder.setVisibility(View.INVISIBLE);


//        return view;


    }


}





