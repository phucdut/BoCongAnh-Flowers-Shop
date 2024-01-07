package com.example.admin.Converter;

import com.example.admin.Domain.Cart;
import com.example.admin.Entity.CartEntity;

public class CartConverter {
    public static Cart toModel(CartEntity cartEntity) {
        Cart cart = new Cart();
        cart.setId(cartEntity.getId());
        cart.setCustomer(CustomerConverter.toModel(cartEntity.getCustomerEntity()));
        cart.setCartItems(cartEntity.getCartItemEntities().stream().map(CartItemConverter::toModel).toList());
        return cart;
    }
}