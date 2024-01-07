package com.example.customer.converter;

import com.example.customer.domain.Cart;
import com.example.customer.domain.CartItem;
import com.example.customer.entity.CartEntity;
import com.example.customer.entity.CartItemEntity;

import java.util.ArrayList;
import java.util.List;

public class CartConverter {
    public static Cart toModel(CartEntity cartEntity) {
        Cart cart = new Cart();
        cart.setId(cartEntity.getId());
        cart.setCustomer(CustomerConverter.toModel(cartEntity.getCustomerEntity()));
        List<CartItem> cartItems = new ArrayList<>();
        for (CartItemEntity cartItem: cartEntity.getCartItemEntities()) {
            if (cartItem.getQuantity() != 0) {
                cartItems.add(CartItemConverter.toModel(cartItem));
            }
        }
        cart.setCartItems(cartItems);
        return cart;
    }
}
