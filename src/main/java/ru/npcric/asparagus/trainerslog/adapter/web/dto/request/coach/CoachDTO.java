package ru.npcric.asparagus.trainerslog.adapter.web.dto.request.coach;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.filial.FilialDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.validation.PeopleNameConstraint;

public record CoachDTO(@NotBlank(message = "ФИО тренера не может быть пустым")
                       @Size(min = 5, max = 40, message = "ФИО тренера не может быть меньше 5 или 40 символов")
                       @PeopleNameConstraint
                       String name,
                       FilialDTO filialDTO,
                       String username) {
}
