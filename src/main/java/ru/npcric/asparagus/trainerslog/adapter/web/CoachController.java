package ru.npcric.asparagus.trainerslog.adapter.web;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.coach.CoachDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.filial.FilialDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.coach.*;
import ru.npcric.asparagus.trainerslog.domain.user.UserEntity;
import ru.npcric.asparagus.trainerslog.service.CoachService;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/trainerslog/api/v1/coach")
//@RolesAllowed("DEFAULT")
public class CoachController {
    CoachService coachService;

    //@RolesAllowed("ADMIN")
    @PostMapping("/createCoach")
    public CoachFullResponse createCoach(@RequestBody @Valid CoachDTO coachDTO) {
        return coachService.createCoach(coachDTO);
    }


    @GetMapping("/getCoachesInFilial")
    public AllCoachesInFilialResponse getCoachesInFilial(@RequestBody @Valid FilialDTO filialDTO) {
        return coachService.getCoachesInFilial(filialDTO);
    }


    @GetMapping("/getAllCoaches")
    public List<CoachWithFilialNameResponse> getAllCoaches() {
        return coachService.getAllCoaches();
    }


    @GetMapping("/getCoachGroups")
    public CoachGroupsResponse getCoachGroups(@AuthenticationPrincipal UserEntity userCoach) {
        return coachService.getCoachGroups(userCoach);
    }


    @GetMapping("/getAllCoachStudents")
    public CoachStudentsResponse getAllCoachStudents(@AuthenticationPrincipal UserEntity userCoach){
        return coachService.getCoachStudents(userCoach);
    }

    @DeleteMapping("/deleteCoach")
    public void deleteCoach(@RequestBody String username) {
        coachService.deleteCoach(username);
    }
}
