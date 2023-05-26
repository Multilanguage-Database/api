package com.multiLanguageDB.multilanguageapi.customer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class CustomerCartRequest {
    UUID id;
}
