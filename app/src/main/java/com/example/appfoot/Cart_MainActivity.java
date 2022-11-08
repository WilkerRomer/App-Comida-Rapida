package com.example.appfoot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appfoot.Adaptor.AdapterCart;
import com.example.appfoot.Helper.ManagerCart;
import com.example.appfoot.Interfaces.NumberListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Cart_MainActivity extends AppCompatActivity {


    private RecyclerView.Adapter adapter;
    private RecyclerView ListRecycler;
    private ManagerCart managerCart;
    private TextView itemTotal, DeliveryService, TaxTxt, Total, EmptyCart;
    private double tax;
    private ScrollView scrollView;
    //private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart__main);

       Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        managerCart=new ManagerCart(this);

        managerCart();
        initList();
        CarculosCart();
    }



    private void managerCart() {

        ListRecycler=findViewById(R.id.RecyclerView);
        itemTotal=findViewById(R.id.Txt);
        DeliveryService=findViewById(R.id.taxTxt);
        TaxTxt = findViewById(R.id.totalTax);
        Total = findViewById(R.id.totalTxt);
        EmptyCart = findViewById(R.id.Emptytxt);
        scrollView = findViewById(R.id.scrollView2);
        ListRecycler = findViewById(R.id.RecyclerView);


    }
    private void initList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        ListRecycler.setLayoutManager(linearLayoutManager);
        adapter = new AdapterCart(managerCart.getListCart(), this, new NumberListener() {
            @Override
            public void changed() {
                CarculosCart();
            }
        });

        ListRecycler.setAdapter(adapter);
        if (managerCart.getListCart().isEmpty()){
            EmptyCart.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);

        }else{
            EmptyCart.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }

    }
    private void CarculosCart(){
        double percentTax = 0.02;
        double Delivery = 10;

        tax = Math.round((managerCart.getTotalFee() * percentTax) * 100)/100;
        double total = Math.round((managerCart.getTotalFee() + tax + Delivery) * 100) / 100;
        double itemTotales = Math.round(managerCart.getTotalFee()*100)/100;

        itemTotal.setText("$"+itemTotales);
        TaxTxt.setText("$"+tax);
        DeliveryService.setText("$"+Delivery);
        Total.setText("$" + total);
    }

}