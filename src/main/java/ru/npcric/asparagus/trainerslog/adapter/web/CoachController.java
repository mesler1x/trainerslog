package ru.npcric.asparagus.trainerslog.adapter.web;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.CoachDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.CoachFullResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.CoachSmallResponse;
import ru.npcric.asparagus.trainerslog.domain.CoachEntity;
import ru.npcric.asparagus.trainerslog.service.CoachService;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/trainerslog/api/v1/coach")
public class CoachController {
    CoachService coachService;
    @GetMapping("/getAll")
    public List<CoachSmallResponse> getAllCoaches() {
        return coachService.findAll();
    }

    @PostMapping("/create")
    public CoachFullResponse createCoach(@RequestBody CoachDTO coachDTO) {
        return coachService.createCoach(coachDTO);
    }
}
