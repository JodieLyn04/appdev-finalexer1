package com.example.finalexer1;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView rvProduct;
    ProductAdapter productAdapter;
    Button btnCart;

    ArrayList<String> cartNames;
    ArrayList<String> cartPrices;
    ArrayList<String> cartQuantity;
    ArrayList<String> cartSubTotal;

    public ProductFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductFragment newInstance(String param1, String param2) {
        ProductFragment fragment = new ProductFragment();
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
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_product,container,false);
        if(getArguments()== null){
            cartNames=new ArrayList<String>();
            cartPrices=new ArrayList<String>();
            cartQuantity=new ArrayList<String>();
            cartSubTotal=new ArrayList<String>();
        }else{
            cartNames=getArguments().getStringArrayList("cartNames");
            cartPrices=getArguments().getStringArrayList("cartPrices");
            cartQuantity=getArguments().getStringArrayList("cartQuantity");
            cartSubTotal=getArguments().getStringArrayList("cartSubTotal");
        }
        rvProduct = root.findViewById(R.id.recyclerViewProduct);
        rvProduct.setLayoutManager(new GridLayoutManager(root.getContext(), 2));
        productAdapter = new ProductAdapter(root.getContext());
        productAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle1 = new Bundle();
                bundle1.putInt("position", position);
                bundle1.putStringArrayList("cartNames", cartNames);
                bundle1.putStringArrayList("cartPrices", cartPrices);
                bundle1.putStringArrayList("cartQuantity", cartQuantity);
                bundle1.putStringArrayList("cartSubTotal", cartSubTotal);
                Navigation.findNavController(view).navigate(R.id.action_productFragment_to_detailFragment,bundle1);

            }
        });
        rvProduct.setAdapter(productAdapter);

        btnCart = root.findViewById(R.id.buttonCart);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("cartNames", cartNames);
                bundle.putStringArrayList("cartPrices", cartPrices);
                bundle.putStringArrayList("cartQuantity", cartQuantity);
                bundle.putStringArrayList("cartSubTotal", cartSubTotal);
                Navigation.findNavController(v).navigate(R.id.action_productFragment_to_cartFragment,bundle);
            }
        });
        return root;
    }
}