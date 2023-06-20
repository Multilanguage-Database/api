package com.multiLanguageDB.multilanguageapi.paymentMethod;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.multiLanguageDB.multilanguageapi.cart.Cart;
import com.multiLanguageDB.multilanguageapi.paymentMethodTranslation.PaymentMethodTranslation;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="Payment_Method")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PaymentMethod {
    @JsonProperty("_id")
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "PAYMENT_ID", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name ="NAME", nullable = false)
    private String name;

    @Column(name="PAYMENT_DESCRIPTION", nullable = false)
    private String description;

    @OneToMany(targetEntity = Cart.class, mappedBy = "paymentMethod", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cart> carts;

    @OneToMany(targetEntity = PaymentMethodTranslation.class, mappedBy = "paymentMethod", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private Set<PaymentMethodTranslation> paymentMethodTranslationsAssoc;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentMethod that = (PaymentMethod) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
