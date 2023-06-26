package com.multiLanguageDB.multilanguageapi.translation;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class TranslationService {

    @NonNull
    private TranslationRepositoy translationRepositoy;

    public Translation create(Translation transientEntity) {
        return translationRepositoy.saveAndFlush(transientEntity);
    }

    public List<Translation> findAll() {
        return translationRepositoy.findAll();
    }

    public Optional<Translation> findById(UUID id) {
        return translationRepositoy.findById(id);
    }

    public Optional<Translation> findTranslationByLocaleAndTextContentId(String locale, UUID id) {
        return translationRepositoy.findTranslationByLocaleAndTextContentId(locale, id);
    }

    public Translation update(Translation entity) {
        return translationRepositoy.saveAndFlush(entity);
    }

    public void delete(Translation entity) {
        translationRepositoy.delete(entity);
    }
}
