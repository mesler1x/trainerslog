package ru.npcric.asparagus.trainerslog.domain.user;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    DEFAULT,
    ADMIN,
    COACH,
    STUDENT;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
