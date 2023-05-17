package com.multiLanguageDB.multilanguageapi.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="Cart")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class Cart {
    @JsonProperty("_id")
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "CART_ID", columnDefinition = "uuid")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "PAYMENT_ID")
    private String payment_id;
}
