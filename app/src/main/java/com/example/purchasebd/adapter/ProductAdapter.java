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
import com.example.purchasebd.UpdateProduct;
import com.example.purchasebd.UpdatePurchase;
import com.example.purchasebd.bd.Product;
import com.example.purchasebd.bd.Purchase;

import java.io.Serializable;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    private final Context context;
    private List<Product> productsList;

    public ProductAdapter (Context context){
        this.context = context;
    }

    public void setProductsList(List<Product> productsList){
        this.productsList = productsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.MyViewHolder  holder, int position) {
        holder.idProduct.setText("ID: " + String.valueOf(this.productsList.get(position).idProduct));
        holder.nameProduct.setText("Product's name: " + this.productsList.get(position).productName);
        holder.Price.setText("Prise per one: " + String.valueOf(this.productsList.get(position).productPrice));
    }

    @Override
    public int getItemCount() {
        if (productsList == null)
            return 0;
        return this.productsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView idProduct;
        TextView nameProduct;
        TextView Price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            idProduct = itemView.findViewById(R.id.tvBuyerName);
            nameProduct = itemView.findViewById(R.id.tvBuyerPhone);
            Price = itemView.findViewById(R.id.tvBuyerEmail);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Product product = productsList.get(getBindingAdapterPosition());

            Intent intent = new Intent(context, UpdateProduct.class);
            intent.putExtra("product", (Serializable) product);
            context.startActivity(intent);

        }
    }
}
