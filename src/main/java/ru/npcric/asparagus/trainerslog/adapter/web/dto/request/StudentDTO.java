package ru.npcric.asparagus.trainerslog.adapter.web.dto.request;

import jakarta.validation.constraints.*;
import ru.npcric.asparagus.trainerslog.adapter.web.validation.PeopleNameConstraint;

import java.time.LocalDate;

public record StudentDTO(TicketDTO ticketDTO,
                         @NotBlank(message = "ФИО студента не может быть пустым")
                       @Size(min = 5, max = 40, message = "ФИО студента не может быть меньше 5 или 40 символов")
                       @PeopleNameConstraint
                       String fullName,
                         @NotBlank(message = "Пол студента не может быть пустым")
                       @Pattern(regexp = "м|ж", message = "Пол студента должен быть в формате: м или ж")
                       @Size(max = 1, message = "Пол студента должен быть в формате: м или ж")
                       String sex,
                         @NotNull(message = "Дата рождения студента не может быть пустой")
                       LocalDate birthDate,
                         @Positive(message = "Кю не может быть отрицательным")
                       int q,// уровень владения айкидо
                         @NotBlank
                       @Pattern(regexp = "\\+79\\d{9}",
                       message = "Номер телефона студента должен быть в формате: +79623881729")
                       String phoneNumber,
                         @NotBlank
                       @Pattern(regexp = "\\+79\\d{9}",
                       message = "Номер телефона родителя должен быть в формате: +79623881729")
                       String parentPhoneNumber,
                         @NotBlank(message = "ФИО родителя не может быть пустым")
                       @Size(min = 5, max = 40, message = "ФИО родителя не может быть меньше 5 или 40 символов")
                       @PeopleNameConstraint
                       String parentFullName,
                         GroupDTO group
                       ) {
}
