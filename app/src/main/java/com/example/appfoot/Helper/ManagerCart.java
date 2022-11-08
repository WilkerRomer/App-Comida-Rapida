package com.example.appfoot.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.appfoot.Directorio.foodDirectory;
import com.example.appfoot.Interfaces.NumberListener;

import java.util.ArrayList;

public class ManagerCart {

    private Context context;
    private TinyDB tinyDB;

    public ManagerCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertarFood(foodDirectory item) {
        ArrayList<foodDirectory> listFood = getListCart();
        boolean existAlreade = false;
        int n = 0;
        for (int i = 0; i < listFood.size(); i++) {
            if (listFood.get(i).getTitle().equals(item.getTitle())) {
                existAlreade = true;
                n = i;
                break;
            }

        }

        if (existAlreade) {
            listFood.get(n).setNumberInCart(item.getNumberInCart());
        } else {
            listFood.add(item);
        }
        tinyDB.putListObject("CartList", listFood);
        Toast.makeText(context, "Pedido gregado al carrito!", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<foodDirectory> getListCart() {
        return tinyDB.getListObject("CartList");
    }

    public void MasNumberFood(ArrayList<foodDirectory> listfood, int position, NumberListener numberListener) {
        listfood.get(position).setNumberInCart(listfood.get(position).getNumberInCart() + 1);
        tinyDB.putListObject("CartList", listfood);
        numberListener.changed();
    }

    public void MenosNumberFood(ArrayList<foodDirectory> listfood, int position, NumberListener numberListener) {
        if (listfood.get(position).getNumberInCart() == 1) {
            listfood.remove(position);
        } else {
            listfood.get(position).setNumberInCart(listfood.get(position).getNumberInCart() - 1);

        }
        tinyDB.putListObject("CartList", listfood);
        numberListener.changed();
        Toast.makeText(context, "Se a eliminado este pedido", Toast.LENGTH_SHORT).show();
    }

    public double getTotalFee() {
        ArrayList<foodDirectory> listFood = getListCart();
        double fee = 0;
        for (int i = 0; i < listFood.size(); i++) {

            fee = fee + (listFood.get(i).getFee() * listFood.get(i).getNumberInCart());
        }
        return fee;
    }
}
