package ru.npcric.asparagus.trainerslog.adapter.web.dto;

import java.util.List;

public record Coach(String name,
                    List<Group> groups) {
}
