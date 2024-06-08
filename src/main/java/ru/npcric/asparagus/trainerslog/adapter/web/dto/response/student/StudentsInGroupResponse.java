package ru.npcric.asparagus.trainerslog.adapter.web.dto.response.student;

import java.time.LocalDate;
import java.util.List;

public record StudentsInGroupResponse(String studentName, LocalDate birthDate, Integer age, String parentPhoneNumber){
}
