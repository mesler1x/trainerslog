package ru.npcric.asparagus.trainerslog.adapter.web.dto.response.coach;

import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.group.GroupIdAndNameResponse;

import java.util.List;

public record CoachGroupsResponse(String coachName, List<GroupIdAndNameResponse> groupResponse){
}
