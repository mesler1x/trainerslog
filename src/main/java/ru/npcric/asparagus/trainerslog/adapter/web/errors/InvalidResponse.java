package ru.npcric.asparagus.trainerslog.adapter.web.errors;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record InvalidResponse(String code, String field, String message) {
    public InvalidResponse(String code, String message) {
        this(code, null, message);
    }
}
