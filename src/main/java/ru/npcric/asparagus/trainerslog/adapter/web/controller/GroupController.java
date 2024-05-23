package ru.npcric.asparagus.trainerslog.adapter.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.group.GroupDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.group.UpdateCoachInGroupRequest;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.group.GroupAndCoachNameResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.group.GroupFullResponse;
import ru.npcric.asparagus.trainerslog.domain.user.UserEntity;
import ru.npcric.asparagus.trainerslog.service.GroupService;

@Validated
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/trainerslog/api/v1/group")
@Tag(name = "Контроллер группы", description = "Контроллер для управления группами в филиалах")
@RolesAllowed({"ADMIN", "COACH"})
public class GroupController {
    GroupService groupService;


    @Operation(
            summary = "Создание группы тренером",
            description = "Создание группы, авторизованным тренером"
    )
    @PostMapping("/createGroup")
    public ResponseEntity<GroupFullResponse> createGroup(@RequestBody @Valid GroupDTO groupDTO,
                                                         @AuthenticationPrincipal UserEntity coach) {
        return ResponseEntity.ok().body(groupService.createGroup(groupDTO, coach));
    }

    @Operation(
            summary = "Удаление группы по её имени",
            description = "У всех учеников после удаления их группы, будут ссылки на null"
    )
    @DeleteMapping("/deleteGroup")
    public ResponseEntity<?> deleteGroup(@RequestParam("groupName") String groupName) {
        groupService.deleteGroup(groupName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            summary = "Обновление тренера группы"
    )
    @PutMapping("/updateGroup")
    public ResponseEntity<GroupAndCoachNameResponse> updateCoachOfGroup(@RequestBody UpdateCoachInGroupRequest request) {
        return ResponseEntity.ok().body(groupService.updateCoachOfGroup(request));
    }
}
