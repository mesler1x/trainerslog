package ru.npcric.asparagus.trainerslog.adapter.web.dto.request.ticket;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.student.StudentDTO;

public record TicketDTO(@NotNull(message = "Абонемент не может быть создан без студента") String studentUsername,
                        String startDate,
                        String endDate,
                        Boolean isExpired,
                        @Min(value = 0, message = "Сумма к оплате не может быть отрицательной") @NotNull Integer paidAmount) {
}
