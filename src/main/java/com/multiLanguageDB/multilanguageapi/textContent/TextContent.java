package com.multiLanguageDB.multilanguageapi.textContent;

import com.multiLanguageDB.multilanguageapi.locale.Locale;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="Text_Content")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TextContent {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "TEXT_ID", columnDefinition = "BINARY(16)")
    private UUID id;


    @Column(name = "ORIG_TEXT")
    private String text;

    @ManyToOne
    @JoinColumn(name="LOCALE_ID")
    private Locale locale;
}
