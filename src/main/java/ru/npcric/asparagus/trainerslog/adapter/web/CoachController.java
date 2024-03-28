package ru.npcric.asparagus.trainerslog.adapter.web;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.CoachResponse;
import ru.npcric.asparagus.trainerslog.service.CoachService;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/trainerslog/api/v1")
public class CoachController {
    CoachService coachService;
    @GetMapping("/getAll")
    public List<CoachResponse> getAllCoaches() {
        return coachService.findAll();
    }
}
