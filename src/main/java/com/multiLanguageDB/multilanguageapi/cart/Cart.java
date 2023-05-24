package com.multiLanguageDB.multilanguageapi.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.multiLanguageDB.multilanguageapi.cartProduct.CartProduct;
import com.multiLanguageDB.multilanguageapi.customer.Customer;
import com.multiLanguageDB.multilanguageapi.paymentMethod.PaymentMethod;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
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

    @ManyToOne()
    @JoinColumn(name = "PAYMENT_ID")
    private PaymentMethod paymentMethod;

    @OneToOne
    private Customer customer;

    @OneToMany(mappedBy = "cart")
    private Set<CartProduct> products = new HashSet<CartProduct>();

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
