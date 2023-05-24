package com.multiLanguageDB.multilanguageapi.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.multiLanguageDB.multilanguageapi.cartProduct.CartProduct;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="Product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class Product {
    @JsonProperty("_id")
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "PRODUCT_ID", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "PRODUCT_TITLE", nullable = false)
    private String title;

    @Column(name = "PRODUCT_DESCRIPTION", nullable = true)
    private String description;

    @Column(name = "PRODUCT_QUANTITY", nullable = false)
    private int quantity;

    @Column(name = "PRODUCT_PRICE", nullable = false)
    private String price;

    @OneToMany(targetEntity = CartProduct.class, mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartProduct> cartAssoc;
}
