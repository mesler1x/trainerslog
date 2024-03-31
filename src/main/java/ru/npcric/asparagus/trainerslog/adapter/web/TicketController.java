package ru.npcric.asparagus.trainerslog.adapter.web;

import jakarta.annotation.security.RolesAllowed;
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
@RolesAllowed("DEFAULT")
public class TicketController {
    TicketService ticketService;
    //todo
}
