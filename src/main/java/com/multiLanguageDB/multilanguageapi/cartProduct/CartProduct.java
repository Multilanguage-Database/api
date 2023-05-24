package com.multiLanguageDB.multilanguageapi.cartProduct;

import com.multiLanguageDB.multilanguageapi.cart.Cart;
import com.multiLanguageDB.multilanguageapi.product.Product;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Cart_Product")
@IdClass(CartProductId.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class CartProduct implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    @Id
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
