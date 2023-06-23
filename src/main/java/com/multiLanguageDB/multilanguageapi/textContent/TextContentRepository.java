package com.multiLanguageDB.multilanguageapi.textContent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TextContentRepository extends JpaRepository<TextContent, UUID> {
    Optional<TextContent> findById(UUID id);
}
