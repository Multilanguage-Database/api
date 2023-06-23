package com.multiLanguageDB.multilanguageapi.textContent;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/text")
@RequiredArgsConstructor
public class TextContentController {

    private final TextContentService textContentService;

    private final TextContentResourceAssembler textContentResourceAssembler;

    @GetMapping
    public ResponseEntity<List<TextContentResource>> getTextContents() {
        return ResponseEntity.ok(textContentResourceAssembler.toListResource(textContentService.findAll()));
    }
}
