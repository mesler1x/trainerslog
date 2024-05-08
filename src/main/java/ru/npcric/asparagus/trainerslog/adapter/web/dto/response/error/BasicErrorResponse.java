package ru.npcric.asparagus.trainerslog.adapter.web.dto.response.error;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BasicErrorResponse(String code, String field, String message) {

    public BasicErrorResponse(String code, String message) {
        this(code, null, message);
    }
}
