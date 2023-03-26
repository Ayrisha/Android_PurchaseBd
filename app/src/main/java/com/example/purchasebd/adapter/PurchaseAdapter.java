package com.example.purchasebd.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.purchasebd.R;
import com.example.purchasebd.UpdatePurchase;
import com.example.purchasebd.bd.Purchase;

import java.io.Serializable;
import java.util.List;


public class PurchaseAdapter extends RecyclerView.Adapter<PurchaseAdapter.MyViewHolder> {
    private final Context context;
    private List<Purchase> purchaseList;

    public List<Purchase> getPurchaseList() {
        return purchaseList;
    }

    public Context getContext() {
        return context;
    }

    public PurchaseAdapter (Context context){
        this.context = context;
    }

    public void setPurchaseList(List<Purchase> purchaseList){
        this.purchaseList = purchaseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PurchaseAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.purchase_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PurchaseAdapter.MyViewHolder holder, int position) {
        holder.idPurchase.setText("ID: " + String.valueOf(this.purchaseList.get(position).idPurchase));
        holder.nameBuyer.setText("Name buyer: " + this.purchaseList.get(position).buyerName);
        holder.idProduct.setText("Id product: " + String.valueOf(this.purchaseList.get(position).productId));
        holder.productAmount.setText("Amount of product: " + String.valueOf(this.purchaseList.get(position).productAmount));
    }

    @Override
    public int getItemCount() {
        if (purchaseList == null)
            return 0;
        return this.purchaseList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView idPurchase;
        TextView nameBuyer;
        TextView idProduct;
        TextView productAmount;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            idPurchase = itemView.findViewById(R.id.tvBuyerName);
            nameBuyer = itemView.findViewById(R.id.tvBuyerPhone);
            idProduct = itemView.findViewById(R.id.tvBuyerEmail);
            productAmount = itemView.findViewById(R.id.tvAmountProduct);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Purchase purchase = purchaseList.get(getBindingAdapterPosition());

            Intent intent = new Intent(context, UpdatePurchase.class);
            intent.putExtra("purchase", (Serializable) purchase);
            context.startActivity(intent);

        }
    }
}
