package com.example.finalexer1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
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

    ArrayList<String> cartNames;
    ArrayList<String> cartPrices;
    ArrayList<String> cartQuantity;
    ArrayList<String> cartSubTotal;

    public DetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
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
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_detail, container, false);
        ivDetail = root.findViewById(R.id.imageViewDetail);
        tvDetailName = root.findViewById(R.id.textDetailName);
        tvDetailPrice = root.findViewById(R.id.textDetailPrice);
        tvDescription = root.findViewById(R.id.textDescription);
        etDetailQuantity = root.findViewById(R.id.editTextQuantity);

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

        position = getArguments().getInt("position", 0);
        ivDetail.setImageDrawable(root.getResources().obtainTypedArray(R.array.productImages).getDrawable(position));
        tvDetailName.setText(root.getResources().getStringArray(R.array.productNames)[position]);
        tvDetailPrice.setText(root.getResources().getStringArray(R.array.productPrices)[position]);
        switch (position){
            case 0:
                tvDescription.setText(R.string.maxchampcap);
                break;

            case 1:
                tvDescription.setText(R.string.maxchamppos);
                break;

            case 2:
                tvDescription.setText(R.string.maxorangehood);
                break;

            case 3:
                tvDescription.setText(R.string.maxpost);
                break;

            case 4:
                tvDescription.setText(R.string.maxorangshirt);
                break;

            case 5:
                tvDescription.setText(R.string.maxtshirt);
                break;

            case 6:
                tvDescription.setText(R.string.beanie);
                break;

            case 7:
                tvDescription.setText(R.string.modelboard);
                break;

            case 8:
                tvDescription.setText(R.string.whitehood);
                break;

            case 9:
                tvDescription.setText(R.string.redbullcap);
                break;

            case 10:
                tvDescription.setText(R.string.cap2024);
                break;

            case 11:
                tvDescription.setText(R.string.graphict);
                break;

            case 12:
                tvDescription.setText(R.string.redbulljacket);
                break;

            case 13:
                tvDescription.setText(R.string.redbullpolo);
                break;

            case 14:
                tvDescription.setText(R.string.redbullshirt);
                break;

            case 15:
                tvDescription.setText(R.string.truckercap);
                break;

            case 16:
                tvDescription.setText(R.string.redbulltshirt);
                break;

            case 17:
                tvDescription.setText(R.string.whitepolo);
                break;
        }

        btnAdd = root.findViewById(R.id.button);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etDetailQuantity.length()==0||Integer.parseInt(etDetailQuantity.getEditableText().toString())==0){
                    Snackbar.make(v, "Invalid Quantity", Snackbar.LENGTH_LONG).show();
                }else {
                    cartNames.add(root.getResources().getStringArray(R.array.productNames)[position]);
                    cartPrices.add(root.getResources().getStringArray(R.array.productPrices)[position]);
                    cartQuantity.add(etDetailQuantity.getEditableText().toString());
                    decimalFormat = new DecimalFormat("#,###.00");
                    double amount =  Double.parseDouble(etDetailQuantity.getEditableText().toString()) *
                           Double.parseDouble(root.getResources().getStringArray(R.array.productPrices)[position]);
                    cartSubTotal.add(decimalFormat.format(amount));
                    Bundle bundle2 = new Bundle();
                    bundle2.putStringArrayList("cartNames", cartNames);
                    bundle2.putStringArrayList("cartPrices", cartPrices);
                    bundle2.putStringArrayList("cartQuantity", cartQuantity);
                    bundle2.putStringArrayList("cartSubTotal", cartSubTotal);
                    Navigation.findNavController(v).navigate(R.id.action_detailFragment_to_cartFragment, bundle2);
                }
            }
        });

        return root;
    }
}