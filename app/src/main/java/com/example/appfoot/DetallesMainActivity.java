package com.example.appfoot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.appfoot.Directorio.foodDirectory;
import com.example.appfoot.Helper.ManagerCart;

public class DetallesMainActivity extends AppCompatActivity {

    private TextView BtnCart;
    private TextView titleTxt, descriptionTxt, feeTxt, numberOrderTxt;
    private ImageView MasBtn, MenosBtn, picFood;
    private foodDirectory object;
int orderNumber = 1;
    private ManagerCart managerCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_main);

        managerCart = new ManagerCart(this);

        initView();
        getBundle();
    }

    private void getBundle() {
        object= (foodDirectory) getIntent().getSerializableExtra("object");

        int drawableResourceId = this.getResources().getIdentifier(object.getPic(),"drawable",this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(picFood);

        titleTxt.setText(object.getTitle());
        feeTxt.setText("$" +object.getFee());
        descriptionTxt.setText(object.getDescription());
        numberOrderTxt.setText(String.valueOf(orderNumber));

        MasBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderNumber = orderNumber + 1;
                numberOrderTxt.setText(String.valueOf(orderNumber));
            }
        });

        MenosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(orderNumber > 1){
                    orderNumber = orderNumber - 1;
                }
                numberOrderTxt.setText(String.valueOf(orderNumber));
            }
        });

        BtnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                object.setNumberInCart(orderNumber);
                managerCart.insertarFood(object);

                finish();
            }
        });

    }
    

    private void initView() {

        BtnCart=findViewById(R.id.AddToCartBtn);
        titleTxt=findViewById(R.id.titleTxt);
        descriptionTxt=findViewById(R.id.descriptionTxt);
        feeTxt=findViewById(R.id.priceTxt);
        numberOrderTxt=findViewById(R.id.numberOrderTxt);
        MasBtn=findViewById(R.id.imageView8);
        MenosBtn=findViewById(R.id.imageView7);
        picFood=findViewById(R.id.imageView6);
    }


}