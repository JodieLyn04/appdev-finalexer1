package com.example.finalexer1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    Context context;
    ArrayList<Integer> cartProduct;
    ArrayList<String> cartNames;
    ArrayList<String> cartQuantity;
    ArrayList<String> cartSubTotal;
    OnDeleteClickListener deleteClickListener;

    public interface OnDeleteClickListener {
        void onDelete(int position);
    }

    public CartAdapter(Context context,
                       ArrayList<Integer> cartProduct,
                       ArrayList<String> cartNames,
                       ArrayList<String> cartQuantity,
                       ArrayList<String> cartSubTotal,
                       OnDeleteClickListener deleteClickListener) {
        this.context = context;
        this.cartProduct = cartProduct;
        this.cartNames = cartNames;
        this.cartQuantity = cartQuantity;
        this.cartSubTotal = cartSubTotal;
        this.deleteClickListener = deleteClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.ivCartImage.setImageResource(cartProduct.get(position));
        holder.tvCartName.setText(cartNames.get(position));
        holder.tvCartQuantity.setText("Quantity: " + cartQuantity.get(position));
        holder.tvSubTotal.setText("$" + cartSubTotal.get(position));

        holder.btnDelete.setOnClickListener(v -> {
            if (deleteClickListener != null) {
                deleteClickListener.onDelete(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartNames != null ? cartNames.size() : 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView ivCartImage;
        TextView tvCartName;
        TextView tvCartQuantity;
        TextView tvSubTotal;
        Button btnDelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCartImage = itemView.findViewById(R.id.imageViewCartProduct);
            tvCartName = itemView.findViewById(R.id.textCartName);
            tvCartQuantity = itemView.findViewById(R.id.textCartQuantity);
            tvSubTotal = itemView.findViewById(R.id.textSubTotal);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}