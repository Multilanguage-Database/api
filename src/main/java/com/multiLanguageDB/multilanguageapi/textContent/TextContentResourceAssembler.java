package com.multiLanguageDB.multilanguageapi.textContent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Builder
public class TextContentResourceAssembler {

    public TextContentResource toResource(TextContent textContent) {
        return TextContentResource.builder()
                .id(textContent.getId())
                .text(textContent.getText())
                .locale(textContent.getLocale().getId())
                .build();
    }

    public List<TextContentResource> toListResource(List<TextContent> textContents) {
        return textContents.stream().map(this::toResource).collect(Collectors.toList());
    }
}
