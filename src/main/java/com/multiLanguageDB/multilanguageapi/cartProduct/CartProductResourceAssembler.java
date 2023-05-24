package com.multiLanguageDB.multilanguageapi.cartProduct;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Builder
public class CartProductResourceAssembler {

    public Optional<CartProductResource> toResource(Optional<CartProduct> cartProduct) {
        return cartProduct.map(this::toResource);
    }

    public CartProductResource toResource(CartProduct cartProduct) {
        return CartProductResource.builder()
                .cart(cartProduct.getCart())
                .product(cartProduct.getProduct())
                .quantity(cartProduct.getQuantity())
                .build();
    }

    public List<CartProductResource> toListResource(List<CartProduct> cartProducts) {
        return cartProducts.stream().map(this::toResource).collect(Collectors.toList());
    }

    public List<Optional<CartProductResource>> toListResourceOptional(List<Optional<CartProduct>> cartProducts) {
        return cartProducts.stream().map(this::toResource).collect(Collectors.toList());
    }
}
