package com.multiLanguageDB.multilanguageapi.locale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocaleRepository extends JpaRepository<Locale, String> {
    Optional<Locale> findById(String id);
}
