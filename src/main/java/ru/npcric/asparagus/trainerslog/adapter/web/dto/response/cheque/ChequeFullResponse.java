package ru.npcric.asparagus.trainerslog.adapter.web.dto.response.cheque;

import java.time.LocalDateTime;

public record ChequeFullResponse(LocalDateTime dateTime, Integer currentDebt, Integer studentBalance, Integer chequeAmount, String link, Boolean isChecked, String studentName) {
}
