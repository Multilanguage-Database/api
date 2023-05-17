package com.multiLanguageDB.multilanguageapi.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.multiLanguageDB.multilanguageapi.customer.Customer;
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
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "CART_ID", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne(targetEntity = Customer.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "PAYMENT_ID")
    private String payment_id;
}
