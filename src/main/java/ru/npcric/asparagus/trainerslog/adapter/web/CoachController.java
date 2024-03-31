package ru.npcric.asparagus.trainerslog.adapter.web;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.CoachDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.coach.CoachFullResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.coach.CoachSmallResponse;
import ru.npcric.asparagus.trainerslog.service.CoachService;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/trainerslog/api/v1/coach")
@RolesAllowed("ADMIN")
public class CoachController {
    CoachService coachService;
    @GetMapping("/getAll")
    public List<CoachSmallResponse> getAllCoaches() {
        return coachService.findAll();
    }

    @PostMapping("/createCoach")
    public CoachFullResponse createCoach(@RequestBody @Valid CoachDTO coachDTO) {
        return coachService.createCoach(coachDTO);
    }


}
