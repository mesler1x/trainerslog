package ru.npcric.asparagus.trainerslog.adapter.web.dto;

import java.time.LocalDate;
import java.util.Map;

public record Student (Ticket ticket,
                       String name,
                       Coach coach,
                       Map<LocalDate, Boolean> visitStatistics) {

}
