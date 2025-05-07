package com.example.finalexer1;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CartFragment extends Fragment {

    RecyclerView rvCart;
    CartAdapter cartAdapter;
    TextView tvTotal;
    Button btnList;

    CartViewModel cartViewModel;

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cart, container, false);

        rvCart = root.findViewById(R.id.recyclerViewCart);
        rvCart.setLayoutManager(new LinearLayoutManager(root.getContext()));

        tvTotal = root.findViewById(R.id.textTotal);
        btnList = root.findViewById(R.id.buttonList);

        cartViewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);

        // Observe data changes
        cartViewModel.getCartNames().observe(getViewLifecycleOwner(), names -> {
            cartAdapter.notifyDataSetChanged();
            updateTotal(cartViewModel.getCartSubtotals().getValue());
        });

        cartAdapter = new CartAdapter(
                getContext(),
                cartViewModel.getCartProducts().getValue(),
                cartViewModel.getCartNames().getValue(),
                cartViewModel.getCartQuantities().getValue(),
                cartViewModel.getCartSubtotals().getValue(),
                position -> {

                    cartViewModel.removeItem(position);
                }
        );

        rvCart.setAdapter(cartAdapter);
        updateTotal(cartViewModel.getCartSubtotals().getValue());

        btnList.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_cartFragment_to_productFragment);
        });

        return root;
    }

    private void updateTotal(ArrayList<String> subtotals) {
        double totalAmount = 0;
        if (subtotals != null) {
            for (String subtotal : subtotals) {
                try {
                    totalAmount += Double.parseDouble(subtotal.replace(",", ""));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }

        NumberFormat format = NumberFormat.getNumberInstance(Locale.US);
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);
        tvTotal.setText(format.format(totalAmount));
    }
}