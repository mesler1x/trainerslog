package ru.npcric.asparagus.trainerslog.adapter.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record Coach(@NotBlank(message = "ФИО тренера не может быть пустым")
                    @Size(min = 5,max = 40, message = "ФИО тренера не может быть меньше 5 или 40 символов")
        //todo сделать кастомную аннотацию на проверку имени регулярным выражением
                    String name,
                    List<Group> groups) {
}
