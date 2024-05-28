package ru.npcric.asparagus.trainerslog.domain.user;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ROLE_DEFAULT,
    ROLE_ADMIN,
    ROLE_COACH,
    ROLE_STUDENT;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
