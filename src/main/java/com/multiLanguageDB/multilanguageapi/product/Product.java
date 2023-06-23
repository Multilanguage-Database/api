package com.multiLanguageDB.multilanguageapi.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.multiLanguageDB.multilanguageapi.cartProduct.CartProduct;
import com.multiLanguageDB.multilanguageapi.textContent.TextContent;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="Product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Product {
    @JsonProperty("_id")
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "PRODUCT_ID", columnDefinition = "BINARY(16)")
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name="TITLE", referencedColumnName = "TEXT_ID")
    private TextContent title;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name="DESCRIPTION", referencedColumnName = "TEXT_ID")
    private TextContent description;

    @Column(name = "PRODUCT_QUANTITY", nullable = false)
    private int quantity;

    @Column(name = "PRODUCT_PRICE", nullable = false)
    private String price;

    @OneToMany(targetEntity = CartProduct.class, mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private Set<CartProduct> cartAssoc;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
