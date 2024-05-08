package ru.npcric.asparagus.trainerslog.adapter.web.dto.response.cheque;

import java.util.List;

public record StudentWithChequesResponse(String username, String fullName, List<String> links) {
}
