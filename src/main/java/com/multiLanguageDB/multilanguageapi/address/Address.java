package com.multiLanguageDB.multilanguageapi.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="Address")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class Address {

    @JsonProperty("_id")
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "address_id", columnDefinition = "uuid")
    private UUID id;

    @Column(name="STREET", nullable = true)
    private String street;

    @Column(name="HOUSE", nullable = true)
    private String house;

    @Column(name = "zip", nullable = true)
    private int zip;

    @Column(name = "city", nullable = true)
    private String city;

    @Column(name = "country", nullable = true)
    private String country;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private String customer_id;
}
