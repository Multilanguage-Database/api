package com.multiLanguageDB.multilanguageapi.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.multiLanguageDB.multilanguageapi.address.Address;
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
    @JsonProperty("_idCustomer")
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

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Address address;

    @OneToOne(targetEntity = Customer.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "CART_ID")
    private String cart_id;

    public void setAddress(Address address) {
        if(address == null) {
            if (this.address !=null) {
                this.address.setCustomer(null);
            }
        } else {
            address.setCustomer(this);
        }
        this.address = address;
    }
}
