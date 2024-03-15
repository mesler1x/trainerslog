package ru.npcric.asparagus.trainerslog.adapter.web.dto;

import java.time.LocalDate;
import java.util.List;

public record Group(String groupName,
                    List<Student> students,
                    List<LocalDate> dates) {
}
