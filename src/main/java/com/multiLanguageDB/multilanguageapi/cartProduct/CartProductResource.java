package com.multiLanguageDB.multilanguageapi.cartProduct;

import com.multiLanguageDB.multilanguageapi.cart.Cart;
import com.multiLanguageDB.multilanguageapi.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode(callSuper = false)
@Builder
public class CartProductResource {

    CartProductPK cartProductPK;

    Cart cart;

    Product product;

    int quantity;
}
