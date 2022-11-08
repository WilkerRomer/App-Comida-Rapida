package com.example.appfoot.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appfoot.Directorio.foodDirectory;
import com.example.appfoot.Helper.ManagerCart;
import com.example.appfoot.Interfaces.NumberListener;
import com.example.appfoot.R;

import java.util.ArrayList;

public class AdapterCart extends RecyclerView.Adapter<AdapterCart.ViewHolder> {
    private ArrayList<foodDirectory> foodDirectory;
    private ManagerCart managerCart;
    private NumberListener numberListener;

    public AdapterCart(ArrayList<com.example.appfoot.Directorio.foodDirectory> foodDirectory, Context context, NumberListener numberListener) {
        this.foodDirectory = foodDirectory;
        this.managerCart = new ManagerCart(context);
        this.numberListener = numberListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCart.ViewHolder holder, int position) {
    holder.title.setText(foodDirectory.get(position).getTitle());
    holder.feeEachItem.setText(String.valueOf(foodDirectory.get(position).getFee()));
    holder.TotalEachItem.setText(String.valueOf(Math.round((foodDirectory.get(position).getNumberInCart() * foodDirectory.get(position).getFee()) * 100) / 100));
    holder.num.setText(String.valueOf(foodDirectory.get(position).getNumberInCart()));

    int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(foodDirectory.get(position).getPic()
            , "drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.MasItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managerCart.MasNumberFood(foodDirectory, position, new NumberListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        numberListener.changed();
                    }
                });
            }
        });
        holder.MenosItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managerCart.MenosNumberFood(foodDirectory, position, new NumberListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        numberListener.changed();
                    }
                });
            }
        });

    }



    @Override
    public int getItemCount() {
        return foodDirectory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, feeEachItem;
        ImageView pic, MasItem, MenosItem;
        TextView TotalEachItem, num;
        Toolbar toolbar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.titleTxt);
            feeEachItem=itemView.findViewById(R.id.feeId);
            TotalEachItem=itemView.findViewById(R.id.totalEachItem);
            num = itemView.findViewById(R.id.textView16);
            pic=itemView.findViewById(R.id.picCart);
            MasItem=itemView.findViewById(R.id.masCartBtn);
            MenosItem=itemView.findViewById(R.id.menosCart);


        }
    }
}
