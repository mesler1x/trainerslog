package ru.npcric.asparagus.trainerslog.adapter.web.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import ru.npcric.asparagus.trainerslog.adapter.web.validation.PeopleNameConstraint;

import java.util.List;

public record CoachDTO(@NotBlank(message = "ФИО тренера не может быть пустым")
                       @Size(min = 5, max = 40, message = "ФИО тренера не может быть меньше 5 или 40 символов")
                       @PeopleNameConstraint
                       String name,
                       FilialDTO filialDTO) {
}
