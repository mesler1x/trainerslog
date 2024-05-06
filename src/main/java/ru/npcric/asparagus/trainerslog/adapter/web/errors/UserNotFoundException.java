package ru.npcric.asparagus.trainerslog.adapter.web.errors;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {
    private final String code = "NOT_FOUND";

    public UserNotFoundException(String username) {
        super("Not found user with username: " + username);
    }
}
