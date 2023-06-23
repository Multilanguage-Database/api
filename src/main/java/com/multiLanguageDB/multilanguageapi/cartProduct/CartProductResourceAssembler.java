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

    //have to retrieve list of products -> not return a CartProduct but return a Cart
    public CartProductResource toResource(CartProduct cartProduct) {
        return CartProductResource.builder()
                .id(cartProduct.getId())
                .cart(cartProduct.getCart().getId())
                .product(cartProduct.getProduct().getId())
                .product_title(cartProduct.getProduct().getTitle().getText())
                .product_price(cartProduct.getProduct().getPrice())
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
