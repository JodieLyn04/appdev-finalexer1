package com.example.finalexer1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

   Context myContext;
   ArrayList<String> cartNames;
   ArrayList<String> cartPrices;
   ArrayList<String> cartQuantity;
   ArrayList<String> cartSubTotal;

    public CartAdapter(Context context, ArrayList<String> mcartNames, ArrayList<String> mcartPrices, ArrayList<String> mcartQuantity, ArrayList<String> mcartSubTotal) {
        myContext = context;
        cartNames = mcartNames;
        cartPrices = mcartPrices;
        cartQuantity = mcartQuantity;
        cartSubTotal = mcartSubTotal;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(myContext).inflate(R.layout.cart_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvCartName.setText(cartNames.get(position));
        holder.tvCartPrice.setText(cartPrices.get(position));
        holder.tvCartQuantity.setText(cartQuantity.get(position));
        holder.tvSubTotal.setText(cartSubTotal.get(position));


    }

    @Override
    public int getItemCount() {
        if (cartNames==null)
        return 0;
        else
            return cartNames.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvCartName;
        TextView tvCartPrice;
        TextView tvCartQuantity;
        TextView tvSubTotal;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCartName= itemView.findViewById(R.id.textCartName);
            tvCartPrice= itemView.findViewById(R.id.textCartPrice);
            tvCartQuantity= itemView.findViewById(R.id.textCartQuantity);
            tvSubTotal= itemView.findViewById(R.id.textSubTotal);

        }
    }

    private class string {
    }
}
