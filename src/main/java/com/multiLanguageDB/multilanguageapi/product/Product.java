package com.multiLanguageDB.multilanguageapi.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "PRODUCT_ID", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "PRODUCT_TITLE", nullable = false)
    private String title;

    @Column(name = "PRODUCT_DESCRIPTION", nullable = true)
    private String description;

    @Column(name = "PRODUCT_QUANTITY", nullable = false)
    private int quantity;

    @Column(name = "PRODUCT_PRICE", nullable = false)
    private double price;
}
