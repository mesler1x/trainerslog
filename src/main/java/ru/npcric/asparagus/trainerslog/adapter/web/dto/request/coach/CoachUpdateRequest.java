package ru.npcric.asparagus.trainerslog.adapter.web.dto.request.coach;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import ru.npcric.asparagus.trainerslog.adapter.web.validation.PeopleNameConstraint;

import java.time.LocalDate;

public record CoachUpdateRequest (@NotNull
                                  @Size(min = 5, max = 40, message = "ФИО тренера не может быть меньше 5 или 40 символов")
                                  @PeopleNameConstraint
                                  String newName,
                                  @NotNull
                                  LocalDate newBirthDate,
                                  @NotNull
                                  @Pattern(regexp = "\\+79\\d{9}",
                                          message = "Номер телефона должен быть в формате: +79623881729")
                                  String newPhoneNumber,
                                  @NotNull
                                  @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$",
                                          message = "Некоректный ввод email")
                                  String newEmailAddress,
                                  String username) {
}
