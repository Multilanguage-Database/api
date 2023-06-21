package com.multiLanguageDB.multilanguageapi.paymentMethodTranslation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.multiLanguageDB.multilanguageapi.paymentMethod.PaymentMethod;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="Payment_Method_Translation")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PaymentMethodTranslation {
    @JsonProperty("_id")
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "TRANSLATION_ID", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name="LOCALE_ID", nullable = false, length = 5, unique = true)
    private String locale;

    @Column(name = "NAME_TRANSLATION", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION_TRANSLATION", nullable = true)
    private String description;

    @ManyToOne
    @JoinColumn(name = "PAYMENT_ID")
    private PaymentMethod paymentMethod;
}
