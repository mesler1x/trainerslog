package ru.npcric.asparagus.trainerslog.adapter.web.dto.request.coach;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.filial.FilialDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.validation.PeopleNameConstraint;

import java.time.LocalDate;

public record CoachDTO(@NotBlank(message = "ФИО тренера не может быть пустым")
                       @Size(min = 5, max = 100, message = "ФИО тренера не может быть меньше 5 или 100 символов")
                       @PeopleNameConstraint
                       String name,

                       String email,
                       @NotBlank
                       @Pattern(regexp = "\\+79\\d{9}",
                               message = "Номер телефона должен быть в формате: +79623881729")
                       String phoneNumber,
                       @NotBlank(message = "Пол тренера не может быть пустым")
                       @Pattern(regexp = "м|ж", message = "Пол тренера должен быть в формате: м или ж")
                       @Size(max = 1, message = "Пол тренера должен быть в формате: м или ж")
                       String sex,
                       @NotNull(message = "Дата рождения тренера не может быть пустой")
                       LocalDate birthDate,
                       @NotNull
                       FilialDTO filialDTO,
                       String username) {
}
