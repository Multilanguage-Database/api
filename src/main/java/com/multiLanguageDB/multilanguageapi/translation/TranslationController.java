package com.multiLanguageDB.multilanguageapi.translation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/translation")
@RequiredArgsConstructor
public class TranslationController {

    private final TranslationService translationService;

    private final TranslationResourceAssembler translationResourceAssembler;

    @GetMapping
    public ResponseEntity<List<TranslationResource>> getTranslations() {
        return ResponseEntity.ok(translationResourceAssembler.toListResource(translationService.findAll()));
    }
}
