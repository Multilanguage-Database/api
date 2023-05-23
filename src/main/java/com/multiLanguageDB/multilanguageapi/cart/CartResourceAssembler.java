package com.multiLanguageDB.multilanguageapi.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Builder
public class CartResourceAssembler {

    public CartResource toResource(Cart cart) {
        return CartResource.builder()
                .id(cart.getId())
                .build();
    }

    public List<CartResource> toListResource(List<Cart> carts) {
        return carts.stream().map(this::toResource).collect(Collectors.toList());
    }
}
