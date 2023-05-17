package com.multiLanguageDB.multilanguageapi.paymentMethod;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="PaymentMethod")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class PaymentMethod {
    @JsonProperty("_id")
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "PAYMENT_ID", columnDefinition = "uuid")
    private UUID id;

    @Column(name ="NAME", nullable = false)
    private String name;

    @Column(name="PAYMENT_DESCRIPTION", nullable = false)
    private String description;
}
