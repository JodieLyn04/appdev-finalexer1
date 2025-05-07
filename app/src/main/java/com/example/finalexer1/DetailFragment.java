package com.example.finalexer1;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;
import java.util.ArrayList;
import androidx.lifecycle.ViewModelProvider;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    int position;
    ImageView ivDetail;
    TextView tvDetailName;
    TextView tvDetailPrice;
    TextView tvDescription;
    EditText etDetailQuantity;

    DecimalFormat decimalFormat;
    Button btnAdd;

    public DetailFragment() {}

    public static DetailFragment newInstance(String param1, String param2) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_detail, container, false);

        ivDetail = root.findViewById(R.id.imageViewDetail);
        tvDetailName = root.findViewById(R.id.textDetailName);
        tvDetailPrice = root.findViewById(R.id.textDetailPrice);
        tvDescription = root.findViewById(R.id.textDescription);
        etDetailQuantity = root.findViewById(R.id.editTextQuantity);

        CartViewModel cartViewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);

        position = getArguments().getInt("position", 0);
        ivDetail.setImageDrawable(root.getResources().obtainTypedArray(R.array.productImages).getDrawable(position));
        tvDetailName.setText(root.getResources().getStringArray(R.array.productNames)[position]);
        tvDetailPrice.setText(root.getResources().getStringArray(R.array.productPrices)[position]);

        setProductDescription(position);

        btnAdd = root.findViewById(R.id.button);
        btnAdd.setOnClickListener(v -> {
            if (etDetailQuantity.length() == 0 || Integer.parseInt(etDetailQuantity.getText().toString()) == 0) {
                Snackbar.make(v, "Invalid Quantity", Snackbar.LENGTH_LONG).show();
            } else {
                decimalFormat = new DecimalFormat("#,###.00");
                double amount = Double.parseDouble(etDetailQuantity.getText().toString()) *
                        Double.parseDouble(root.getResources().getStringArray(R.array.productPrices)[position]);

                cartViewModel.addToCart(
                        root.getResources().getIntArray(R.array.productImages)[position],
                        root.getResources().getStringArray(R.array.productNames)[position],
                        etDetailQuantity.getText().toString(),
                        decimalFormat.format(amount)
                );

                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.added_to_cart_layout);
                dialog.show();
            }
        });

        requireActivity().addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.menus, menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.cart) {
                    navigateToCartFragment(root);
                    return true;
                }
                return false;
            }
        }, getViewLifecycleOwner(), Lifecycle.State.RESUMED);

        return root;
    }

    private void navigateToCartFragment(View root) {
        Navigation.findNavController(root).navigate(R.id.action_detailFragment_to_cartFragment);
    }

    private void setProductDescription(int position) {
        switch (position) {
            case 0: tvDescription.setText(R.string.maxchampcap); break;
            case 1: tvDescription.setText(R.string.maxchamppos); break;
            case 2: tvDescription.setText(R.string.maxorangehood); break;
            case 3: tvDescription.setText(R.string.maxpost); break;
            case 4: tvDescription.setText(R.string.maxorangshirt); break;
            case 5: tvDescription.setText(R.string.maxtshirt); break;
            case 6: tvDescription.setText(R.string.beanie); break;
            case 7: tvDescription.setText(R.string.modelboard); break;
            case 8: tvDescription.setText(R.string.whitehood); break;
            case 9: tvDescription.setText(R.string.redbullcap); break;
            case 10: tvDescription.setText(R.string.cap2024); break;
            case 11: tvDescription.setText(R.string.graphict); break;
            case 12: tvDescription.setText(R.string.redbulljacket); break;
            case 13: tvDescription.setText(R.string.redbullpolo); break;
            case 14: tvDescription.setText(R.string.redbullshirt); break;
            case 15: tvDescription.setText(R.string.truckercap); break;
            case 16: tvDescription.setText(R.string.redbulltshirt); break;
            case 17: tvDescription.setText(R.string.whitepolo); break;
        }
    }
}

