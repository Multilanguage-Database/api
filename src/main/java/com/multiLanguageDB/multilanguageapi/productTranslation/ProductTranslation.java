package com.multiLanguageDB.multilanguageapi.productTranslation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.multiLanguageDB.multilanguageapi.product.Product;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name="Product_Translation")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductTranslation implements Serializable {
    @JsonProperty("_id")
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "TRANSLATION_ID", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name="LOCALE_ID", nullable = false, length = 5)
    private String locale;

    @Column(name = "TITLE_TRANSLATION", nullable = false)
    private String title;


    @Column(name = "DESCRIPTION_TRANSLATION", nullable = true)
    private String description;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;
}
