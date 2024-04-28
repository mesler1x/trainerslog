package ru.npcric.asparagus.trainerslog.adapter.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.npcric.asparagus.trainerslog.service.TicketService;

@Validated
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/trainerslog/api/v1/ticket")
@Tag(name = "Контроллер абонемента", description = "Контроллер для управления абонементами учеников, федерации айкидо")
//@RolesAllowed("DEFAULT")
public class TicketController {
    TicketService ticketService;
    //todo
}
