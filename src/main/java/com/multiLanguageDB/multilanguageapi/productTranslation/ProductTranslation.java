package com.multiLanguageDB.multilanguageapi.productTranslation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.multiLanguageDB.multilanguageapi.locale.Locale;
import com.multiLanguageDB.multilanguageapi.product.Product;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="Product_Translation")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductTranslation {
    @JsonProperty("_id")
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "TRANSLATION_ID", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "TITLE_TRANSLATION", nullable = false)
    private String title;

    @Column(name = "DESCRIPTION_TRANSLATION", nullable = true)
    private String description;

    @ManyToOne
    @JoinColumn(name = "LOCALE_ID")
    private Locale locale;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    public void setProduct(Product product) {
        if(product == null) {
            if(this.product != null) {
                this.product.setProductTranslationAssoc(null);
            }
        } else {
            Set<ProductTranslation> translations = product.getProductTranslationAssoc();
            if(translations == null) {
                translations = new HashSet<>();
            }
            translations.add(this);

            System.out.println(translations);

            product.setProductTranslationAssoc(translations);
        }
        this.product = product;
    }
}
