package com.multiLanguageDB.multilanguageapi.translation;

import com.multiLanguageDB.multilanguageapi.locale.Locale;
import com.multiLanguageDB.multilanguageapi.textContent.TextContent;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="Translation_Content")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Translation {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "TRANSLATION_ID", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "TRANSLATION")
    private String translation;

    @ManyToOne
    @JoinColumn(name="LOCALE_ID")
    private Locale locale;

    @ManyToOne
    @JoinColumn(name = "TEXT_ID")
    private TextContent textContent;
}
