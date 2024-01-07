package com.example.customer.service;

import com.example.customer.domain.Cart;
import com.example.customer.domain.CartItem;

import java.util.List;

public interface CartService {
    Cart getCartByUsernameCustomer(String name);

    void addItem(String name, Long id);

    boolean updateQuantityItem(String username, CartItem cartItem);

    boolean deleteItem(String name, Long id);

}
