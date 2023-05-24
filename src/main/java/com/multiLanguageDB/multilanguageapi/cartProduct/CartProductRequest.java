package com.multiLanguageDB.multilanguageapi.cartProduct;

import com.multiLanguageDB.multilanguageapi.cart.Cart;
import com.multiLanguageDB.multilanguageapi.product.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class CartProductRequest {

    Cart cart;

    Product product;

    int quantity;

    public CartProduct toCartProduct () {
        return CartProduct.builder()
                .cart(cart)
                .product(product)
                .quantity(quantity)
                .build();
    }
}
