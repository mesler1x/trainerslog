package ru.npcric.asparagus.trainerslog.adapter.web.dto.response.group;

import java.util.List;

public record GroupAndCoachNameResponse (
        Long id,
        String groupName,
        String coachName,
        List<String> studentNames
){}
