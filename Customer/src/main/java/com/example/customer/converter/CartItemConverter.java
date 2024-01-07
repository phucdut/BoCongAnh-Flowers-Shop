package com.example.customer.converter;

import com.example.customer.domain.CartItem;
import com.example.customer.entity.CartItemEntity;

public class CartItemConverter {

    public static CartItem toModel(CartItemEntity item) {
        CartItem cartItem = new CartItem();
        cartItem.setId(item.getId());
        cartItem.setProduct(ProductConverter.toModel(item.getProductEntity()));
        cartItem.setCart_Id(item.getCartEntity().getId());
        cartItem.setQuantity(item.getQuantity());
        return cartItem;
    }
}
