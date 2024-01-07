package com.example.customer.service.Impl;

import com.example.customer.converter.CartConverter;
import com.example.customer.domain.Cart;
import com.example.customer.domain.CartItem;
import com.example.customer.entity.CartEntity;
import com.example.customer.entity.CartItemEntity;
import com.example.customer.entity.ProductEntity;
import com.example.customer.repository.CartItemRepository;
import com.example.customer.repository.CartRepository;
import com.example.customer.repository.CustomerRepository;
import com.example.customer.repository.ProductRepository;
import com.example.customer.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;
    @Override
    public Cart getCartByUsernameCustomer(String name) {
        return CartConverter.toModel(cartRepository.findByCustomerEntity(customerRepository.findByUsername(name).orElseThrow()));
    }

    @Override
    public void addItem(String name, Long productId) {
        // Tìm giỏ hàng của khách hàng
        CartEntity cartEntity = cartRepository.findByCustomerEntity(
                customerRepository.findByUsername(name).orElseThrow()
        );

        // Kiểm tra xem mục có tồn tại trong giỏ hàng không
        Optional<CartItemEntity> optionalCartItem = cartEntity.getCartItemEntities().stream()
                .filter(cartItem -> cartItem.getProductEntity().getId().equals(productId))
                .findFirst();

        if (optionalCartItem.isPresent()) {
            // Tăng số lượng nếu sản phẩm đã tồn tại trong giỏ hàng
            CartItemEntity cartItem = optionalCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        } else {
            // Tạo mới CartItemEntity và thêm vào giỏ hàng
            ProductEntity productEntity = productRepository.findById(productId).orElseThrow();
            CartItemEntity newCartItem = new CartItemEntity();
            newCartItem.setProductEntity(productEntity);
            newCartItem.setQuantity(1);
            newCartItem.setCartEntity(cartEntity);

            // Thêm mục mới vào danh sách giỏ hàng
            cartEntity.getCartItemEntities().add(newCartItem);
        }

        // Cập nhật giỏ hàng
        cartRepository.save(cartEntity);
    }


    @Override
    public boolean updateQuantityItem(String name, CartItem item) {
        if (item.getQuantity() == 0) {
            deleteItem(name, item.getId());
            return true;
        }
        if (checkCartItemByCustomer(name, item.getId())) {
            CartItemEntity cartItemEntity = cartItemRepository.findById(item.getId()).orElseThrow();
            cartItemEntity.setQuantity(item.getQuantity());
            cartItemRepository.save(cartItemEntity);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteItem(String name, Long id) {
        if (checkCartItemByCustomer(name, id)){
            CartItemEntity cartItemEntity = cartItemRepository.findById(id).orElseThrow();
            cartItemRepository.delete(cartItemEntity);
            return true;
        }
        return false;
    }

    private boolean checkCartItemByCustomer(String name, Long id) {
        List<CartItemEntity> cartItemEntities = cartItemRepository.findAllByCartEntity(
                cartRepository.findByCustomerEntity(
                        customerRepository.findByUsername(name).orElseThrow()
                )
        );
        for (CartItemEntity cartItemEntity: cartItemEntities) {
            if (cartItemEntity.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
