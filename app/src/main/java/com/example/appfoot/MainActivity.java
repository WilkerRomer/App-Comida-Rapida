package com.example.appfoot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;

import com.example.appfoot.Adaptor.adapterRecycle;
import com.example.appfoot.Adaptor.popularAdapter;
import com.example.appfoot.Directorio.Directory;
import com.example.appfoot.Directorio.foodDirectory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclreVCT, recycleViewPopular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewrecycleVCT();
        recycleVCTpopular();
        bottomNavegation();
    }
    private void bottomNavegation(){
        FloatingActionButton floatingActionButton = findViewById(R.id.BotonCart);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Cart_MainActivity.class));
            }
        });

    }



    private void ViewrecycleVCT() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclreVCT = findViewById(R.id.recyclerView);
        recyclreVCT.setLayoutManager(linearLayoutManager);

        ArrayList<Directory> category = new ArrayList<>();
        category.add(new Directory("Pizza", "cat_1"));
        category.add(new Directory("Burger", "cat_2"));
        category.add(new Directory("Hotdog", "cat_3"));
        category.add(new Directory("Bebidas", "cat_4"));
        category.add(new Directory("Rosquilla", "cat_5"));

        adapter = new adapterRecycle(category);
        recyclreVCT.setAdapter(adapter);
    }

    private void recycleVCTpopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recycleViewPopular = findViewById(R.id.recyclerView2);
        recycleViewPopular.setLayoutManager(linearLayoutManager);

        ArrayList<foodDirectory> foodlist = new ArrayList<>();
        foodlist.add(new foodDirectory("Peperoni pizza", "pizza", "slices peperoni, mozarella cheese, fresh oregano, ground black peper, pizza sauce", 9.72));
        foodlist.add(new foodDirectory("Cheese Burger", "pop_2", "beef, Gouda cheese, Special sauce, lettuce, tomato", 8.70));
        foodlist.add(new foodDirectory("Vegetable pizza", "pop_3", "olive oil, Vegetable oil, pitted Kalamata, cherry tomatoes, fresh oregano, basil", 6.3));

        adapter2 = new popularAdapter(foodlist);
        recycleViewPopular.setAdapter(adapter2);
    }

}