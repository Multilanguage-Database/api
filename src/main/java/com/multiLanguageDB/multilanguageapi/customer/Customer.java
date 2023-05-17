package com.multiLanguageDB.multilanguageapi.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="Customer")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class Customer {
    @JsonProperty("_id")
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "CUSTOMER_ID", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name="FIRSTNAME", nullable = false)
    private String firstName;

    @Column(name="LASTNAME", nullable = false)
    private String lastName;

    @Column(name="EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name="PASSWORD", nullable = false)
    private String password;

    @OneToOne(targetEntity = Customer.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "CART_ID")
    private String cart_id;
}
