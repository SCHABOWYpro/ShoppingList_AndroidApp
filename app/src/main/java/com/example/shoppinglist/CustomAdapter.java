package com.example.shoppinglist;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList item_id, item_item, item_amount;

    CustomAdapter(Activity activity,
                    Context context,
                  ArrayList item_id,
                  ArrayList item_item,
                  ArrayList item_amount){
        this.activity = activity;
        this.context = context;
        this.item_id = item_id;
        this.item_item = item_item;
        this.item_amount= item_amount;

    }

    public CustomAdapter(MainActivity activity, ArrayList<String> item_id, ArrayList<String> item_item, ArrayList<String> item_amount) {
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {   //tutaj powinno byc final int position

        holder.item_id_txt.setText(String.valueOf(item_id.get(position)));
        holder.item_item_txt.setText(String.valueOf(item_item.get(position)));
        holder.item_amount_txt.setText(String.valueOf(item_amount.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(item_id.get(position)));
                intent.putExtra("item", String.valueOf(item_item.get(position)));
                intent.putExtra("amount", String.valueOf(item_amount.get(position)));
                //context.startActivity(intent);
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return item_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView item_id_txt;
        TextView  item_item_txt, item_amount_txt;

        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_id_txt = itemView.findViewById(R.id.item_id_txt);
            item_item_txt = itemView.findViewById(R.id.item_item_txt);
            item_amount_txt = itemView.findViewById(R.id.item_amount_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
