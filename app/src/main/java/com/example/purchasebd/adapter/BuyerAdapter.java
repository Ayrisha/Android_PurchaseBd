package com.example.purchasebd.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.purchasebd.R;
import com.example.purchasebd.UpdateBuyer;
import com.example.purchasebd.bd.Buyer;

import java.io.Serializable;
import java.util.List;

public class BuyerAdapter extends RecyclerView.Adapter<BuyerAdapter.MyViewHolder> {

    private final Context context;
    private List<Buyer> buyersList;

    public BuyerAdapter (Context context){
        this.context = context;
    }

    public void setBuyersList(List<Buyer> buyersList){
        this.buyersList = buyersList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BuyerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.buyer_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BuyerAdapter.MyViewHolder   holder, int position) {
        holder.buyerName.setText(this.buyersList.get(position).buyerName);
        holder.buyerPhone.setText("Phone number: " + this.buyersList.get(position).buyerPhone);
        holder.buyerEmail.setText("E-mail: " + this.buyersList.get(position).buyerEmail);
    }

    @Override
    public int getItemCount() {
        if (buyersList == null)
            return 0;
        return this.buyersList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView buyerName;
        TextView buyerPhone;
        TextView buyerEmail;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            buyerName = itemView.findViewById(R.id.tvBuyerName);
            buyerPhone = itemView.findViewById(R.id.tvBuyerPhone);
            buyerEmail = itemView.findViewById(R.id.tvBuyerEmail);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Buyer buyer = buyersList.get(getBindingAdapterPosition());
            System.out.println("1" + buyer.buyerName);
            Intent intent = new Intent(context, UpdateBuyer.class);
            intent.putExtra("buyer", (Serializable) buyer);
            context.startActivity(intent);
        }
    }
}
