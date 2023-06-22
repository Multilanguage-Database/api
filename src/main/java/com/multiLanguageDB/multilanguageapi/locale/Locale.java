package com.multiLanguageDB.multilanguageapi.locale;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Locale")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Locale {

    @Id
    @Column(name = "LOCALE_ID", nullable = false, length = 5, unique = true)
    private String id;

    @Column(name = "LOCALE_NAME", nullable = false)
    private String name;
}