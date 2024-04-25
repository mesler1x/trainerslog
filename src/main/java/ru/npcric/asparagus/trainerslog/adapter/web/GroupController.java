package ru.npcric.asparagus.trainerslog.adapter.web;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.group.GroupDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.group.GroupFullResponse;
import ru.npcric.asparagus.trainerslog.domain.user.UserEntity;
import ru.npcric.asparagus.trainerslog.service.GroupService;

@Validated
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/trainerslog/api/v1/group")
//@RolesAllowed("DEFAULT")
public class GroupController {
    GroupService groupService;

    //@RolesAllowed("COACH")
    //todo - поменять на просто create
    @PostMapping("/createGroup")
    public GroupFullResponse createGroup(@RequestBody @Valid GroupDTO groupDTO,
                                         @AuthenticationPrincipal UserEntity coach) {
        return groupService.createGroup(groupDTO, coach);
    }

    @GetMapping("/getById")
    public GroupFullResponse getGroupByID(@RequestParam("groupName") String groupName) {//можно убрать если не надо, писал чтобы проверить
        return groupService.getGroupByName(groupName);
    }

    @DeleteMapping("/deleteGroup")
    public void deleteGroup(@RequestParam("groupName") String groupName) {
        groupService.deleteGroup(groupName);
    }

    //todo - delete group
}
