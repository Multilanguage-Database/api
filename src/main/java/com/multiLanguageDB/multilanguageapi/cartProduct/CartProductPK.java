package com.multiLanguageDB.multilanguageapi.cartProduct;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class CartProductPK implements Serializable {
    @Column(name = "CART_ID")
    private UUID cart_id;

    @Column(name = "PRODUCT_ID")
    private UUID product_id;
}
