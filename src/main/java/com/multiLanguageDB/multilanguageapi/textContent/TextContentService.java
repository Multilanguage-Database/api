package com.multiLanguageDB.multilanguageapi.textContent;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class TextContentService {

    @NonNull
    private final TextContentRepository textContentRepository;

    public TextContent create(TextContent transientEntity) {
        return textContentRepository.saveAndFlush(transientEntity);
    }

    public List<TextContent> findAll() {
        return textContentRepository.findAll();
    }

    public TextContent update(TextContent entity) {
        return textContentRepository.saveAndFlush(entity);
    }

    public void delete(TextContent entity) {
        textContentRepository.delete(entity);
    }
}
