package com.multiLanguageDB.multilanguageapi.paymentMethodTranslation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.multiLanguageDB.multilanguageapi.locale.Locale;
import com.multiLanguageDB.multilanguageapi.paymentMethod.PaymentMethod;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
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

    @Column(name = "NAME_TRANSLATION", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION_TRANSLATION", nullable = true)
    private String description;

    @ManyToOne
    @JoinColumn(name="LOCALE_ID")
    private Locale locale;

    @ManyToOne
    @JoinColumn(name = "PAYMENT_ID")
    private PaymentMethod paymentMethod;

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        if(paymentMethod == null) {
            if(this.paymentMethod != null) {
                this.paymentMethod.setPaymentMethodTranslationsAssoc(null);
            }
        } else {
            Map<Locale, PaymentMethodTranslation> translations = paymentMethod.getPaymentMethodTranslationsAssoc();
            if(translations == null) {
                translations = new HashMap<>();
            }
            translations.put(locale, this);

            System.out.println(translations);

            paymentMethod.setPaymentMethodTranslationsAssoc(translations);
        }
        this.paymentMethod = paymentMethod;
    }
}