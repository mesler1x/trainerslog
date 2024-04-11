package ru.npcric.asparagus.trainerslog.adapter.web.dto.response.student;

import java.time.LocalDate;

public record StudentCreateResponse(Long id,

                                    String fullName,

                                    String sex,

                                    LocalDate birthDate,

                                    Integer q,

                                    String phoneNumber,

                                    String parentPhoneNumber,

                                    String parentFullName
) {
}