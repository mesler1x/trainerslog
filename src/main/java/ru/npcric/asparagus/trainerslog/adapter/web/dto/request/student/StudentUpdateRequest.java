package ru.npcric.asparagus.trainerslog.adapter.web.dto.request.student;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record StudentUpdateRequest (@NotNull
                                    @Size(min = 5, max = 100, message = "ФИО студента не может быть меньше 5 или 100 символов")
                                    String newFullName,

                                    @NotNull
                                    LocalDate newBirthDate,
                                    @NotNull
                                    @Pattern(regexp = "\\+79\\d{9}",
                                            message = "Номер телефона родителя должен быть в формате: +79623881729")
                                    String newParentPhoneNumber,
                                    @NotNull
                                    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$",
                                            message = "Некоректный ввод email")
                                    String newEmail) {
}
