package ru.npcric.asparagus.trainerslog.adapter.web;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.Coach;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.CoachResponse;
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
    public List<CoachResponse> getAllCoaches() {
        return coachService.findAll();
    }

    @PostMapping("/create")
    public CoachEntity createCoach(@RequestBody Coach coach) {
        return coachService.create(coach);
    }
}
