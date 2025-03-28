package com.ceos21.knowledgein.user.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {
    USER("ROLE_USER"), ADMIN("ROLE_ADMIN");

    private final String value;
}
