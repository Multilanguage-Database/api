package com.multiLanguageDB.multilanguageapi.translation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TranslationRepositoy extends JpaRepository<Translation, UUID> {
    Optional<Translation> findById(UUID id);

    @Query("SELECT t FROM Translation t WHERE t.textContent.id = :text_id AND t.locale.id = :locale")
    Optional<Translation> findTranslationByLocaleAndTextContentId(@Param("locale")String locale, @Param("text_id") UUID id);
}
