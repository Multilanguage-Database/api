package com.multiLanguageDB.multilanguageapi.locale;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path ="/locale")
@RequiredArgsConstructor
public class LocaleController {

    private final LocaleService localeService;

    private final LocaleResourceAssembler localeResourceAssembler;

    @PostMapping
    public ResponseEntity<LocaleResource> createLocale(@RequestBody LocaleRequest localeRequest) {
        Locale locale = localeService.create(localeRequest.toLocale());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .build(locale.getId());

        return ResponseEntity.created(location).body(localeResourceAssembler.toResource(locale));
    }

    @GetMapping
    public ResponseEntity<List<LocaleResource>> getLocales() {
        return ResponseEntity.ok(localeResourceAssembler.toListResource(localeService.findAll()));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<LocaleResource> getLocale(@PathVariable("id") String id) {
        return localeService.findByIdOptional(id)
                .map(localeResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteLocale(@PathVariable("id")Optional<Locale> locale) {
        if(locale.isPresent()) {
            localeService.delete(locale.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
