package ru.npcric.asparagus.trainerslog.adapter.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.attendance.AttendanceDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.attendance.GroupAndDateRequest;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.attendance.AttendanceForGroupResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.attendance.AttendanceResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.attendance.MonthlyAttendanceResponse;
import ru.npcric.asparagus.trainerslog.service.AttendanceService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("/trainerslog/api/v1/attendance")
@Tag(name = "Контроллер Посещаемости", description =
        "Контроллер для управления посещаемости учеников секции айкидо")
public class AttendanceController {
    AttendanceService attendanceService;

    @Operation(
            summary = "Отметка ученика на занятии в указанное время"
    )
    @PostMapping("/markAttendance")
    public ResponseEntity<AttendanceResponse> markAttendance(@RequestBody @Valid AttendanceDTO attendanceDTO) {
        return ResponseEntity.ok().body(attendanceService.markAttendance(attendanceDTO));
    }

    @Operation(
            summary = "Получение посещаемости группы в за неделю",
            description = "Возвращает даты занятий группы и список студентов для каждой даты, которые были на данной тренировке"
    )
    @GetMapping("/getGroupWeekAttendance")
    public List<AttendanceForGroupResponse> getWeekAttendanceForGroup(@RequestBody GroupAndDateRequest groupAndDateRequest) {
        return attendanceService.findStudentsByGroupAndDate(groupAndDateRequest);
    }

    @Operation(
            summary = "Получение посещаемости студента за последние 30 дней",
            description = "Возвращает даты занятий на которых он БЫЛ за последние 30 дней"
    )
    @GetMapping("/monthlyAttendance")
    public MonthlyAttendanceResponse getMonthlyAttendance(@RequestParam String username) {
        return attendanceService.getMonthlyAttendanceByUsername(username);
    }


}
