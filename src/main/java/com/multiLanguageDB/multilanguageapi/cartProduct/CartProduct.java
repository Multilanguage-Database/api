package com.multiLanguageDB.multilanguageapi.cartProduct;

import com.multiLanguageDB.multilanguageapi.cart.Cart;
import com.multiLanguageDB.multilanguageapi.product.Product;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Cart_Product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class CartProduct implements Serializable {

    @EmbeddedId
    private CartProductPK id;

    @ManyToOne
    @MapsId("cart_id")
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    public void setCartProductPK(CartProductPK cartProductPK) {
        this.id = cartProductPK;
    }
}
