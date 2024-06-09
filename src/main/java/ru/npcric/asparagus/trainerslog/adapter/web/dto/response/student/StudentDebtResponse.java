package ru.npcric.asparagus.trainerslog.adapter.web.dto.response.student;

import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.cheque.ChequeFullResponse;

import java.util.List;

public record StudentDebtResponse (String fullName, Integer debt, List<ChequeFullResponse> cheques) {
}
