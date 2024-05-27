package ru.npcric.asparagus.trainerslog.adapter.web.dto.request.student;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import ru.npcric.asparagus.trainerslog.adapter.web.validation.PeopleNameConstraint;

import java.time.LocalDate;

public record StudentUpdateRequest (@Nullable
                                    @Size(min = 5, max = 40, message = "ФИО студента не может быть меньше 5 или 40 символов")
                                    String newFullName,

                                    @Nullable
                                    LocalDate newBirthdate,
                                    @Nullable
                                    @Pattern(regexp = "\\+79\\d{9}",
                                            message = "Номер телефона родителя должен быть в формате: +79623881729")
                                    String newParentPhoneNumber,
                                    @Nullable
                                    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$",
                                            message = "Некоректный ввод email")
                                    String eemail,
                                    String username) {
}
