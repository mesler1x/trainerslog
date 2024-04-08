package ru.npcric.asparagus.trainerslog.adapter.web.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ru.npcric.asparagus.trainerslog.adapter.web.validation.GroupNameConstraint;

import java.time.LocalDateTime;
import java.util.List;

public record GroupDTO(@NotNull @NotBlank(message = "Имя группы не может быть пустым")
                    @GroupNameConstraint
                    String groupName,
                       @NotNull List<String> studentUsernames,
                       @Nullable List<LocalDateTime> dates) { // даты могут быть Null пока их не заполнили
}
