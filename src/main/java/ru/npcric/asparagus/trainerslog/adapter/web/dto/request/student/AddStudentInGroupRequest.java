package ru.npcric.asparagus.trainerslog.adapter.web.dto.request.student;
//todo - исправить на принятие List<String> studentUserNames
public record AddStudentInGroupRequest(String studentUserName, String groupName) {
}
