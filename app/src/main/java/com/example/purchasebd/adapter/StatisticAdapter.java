package com.example.purchasebd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.purchasebd.R;
import com.example.purchasebd.bd.Purchase;
import com.example.purchasebd.bd.PurchaseWithProduct;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class StatisticAdapter extends RecyclerView.Adapter<StatisticAdapter.MyViewHolder> {

    private final Context context;
    private List<PurchaseWithProduct> list;

    public StatisticAdapter(Context context) {
        this.context = context;
    }

    public void setStatistic(List<PurchaseWithProduct> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StatisticAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.statistic_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatisticAdapter.MyViewHolder holder, int position) {
        holder.id.setText("ID: " + String.valueOf(this.list.get(position).purchase.idPurchase));
        holder.buyer.setText("Client's name: " + this.list.get(position).purchase.buyerName);
        holder.nameProduct.setText("Product: " + this.list.get(position).statisticProduct.get(0).productName);
        holder.price.setText("Sum: " + String.valueOf(this.list.get(position).purchase.productAmount * this.list.get(position).statisticProduct.get(0).productPrice));
    }

    @Override
    public int getItemCount() {
        if (list == null)
            return 0;
        return this.list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView buyer;
        TextView nameProduct;
        TextView price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.tvBuyerName);
            buyer = itemView.findViewById(R.id.tvBuyerPhone);
            nameProduct = itemView.findViewById(R.id.tv);
            price = itemView.findViewById(R.id.tv1);
        }
    }
}
