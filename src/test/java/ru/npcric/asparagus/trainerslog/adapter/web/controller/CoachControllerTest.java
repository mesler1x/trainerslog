package ru.npcric.asparagus.trainerslog.adapter.web.controller;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.coach.CoachDTO;
import ru.npcric.asparagus.trainerslog.service.CoachService;

@ExtendWith(MockitoExtension.class)
public class CoachControllerTest {

    @Mock
    CoachService coachService;

    @InjectMocks
    CoachController coachController;

    @Test
    void createCoach() throws Exception{
        //todo

    }

}
