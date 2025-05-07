package com.example.finalexer1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class CartViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<Integer>> cartProducts = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<ArrayList<String>> cartNames = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<ArrayList<String>> cartQuantities = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<ArrayList<String>> cartSubtotals = new MutableLiveData<>(new ArrayList<>());

    public LiveData<ArrayList<Integer>> getCartProducts() {
        return cartProducts;
    }

    public LiveData<ArrayList<String>> getCartNames() {
        return cartNames;
    }

    public LiveData<ArrayList<String>> getCartQuantities() {
        return cartQuantities;
    }

    public LiveData<ArrayList<String>> getCartSubtotals() {
        return cartSubtotals;
    }

    public void removeItem(int position) {
        cartProducts.getValue().remove(position);
        cartNames.getValue().remove(position);
        cartQuantities.getValue().remove(position);
        cartSubtotals.getValue().remove(position);

        // Trigger observers
        cartProducts.setValue(cartProducts.getValue());
        cartNames.setValue(cartNames.getValue());
        cartQuantities.setValue(cartQuantities.getValue());
        cartSubtotals.setValue(cartSubtotals.getValue());
    }

    public void addToCart(int productImage, String productName, String quantity, String subtotal) {
        cartProducts.getValue().add(productImage);
        cartProducts.setValue(cartProducts.getValue()); // Trigger observers

        cartNames.getValue().add(productName);
        cartNames.setValue(cartNames.getValue());

        cartQuantities.getValue().add(quantity);
        cartQuantities.setValue(cartQuantities.getValue());

        cartSubtotals.getValue().add(subtotal);
        cartSubtotals.setValue(cartSubtotals.getValue());
    }
}