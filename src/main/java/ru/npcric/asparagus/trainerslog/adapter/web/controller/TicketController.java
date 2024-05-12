package ru.npcric.asparagus.trainerslog.adapter.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.ticket.TicketValidationRequest;
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

    //todo - scheduler
    @PatchMapping("/monthlyUpdate")
    public ResponseEntity<?> monthlyUpdateTicketForStudent(@RequestBody TicketValidationRequest ticketValidationRequest) {
        ticketService.updateValidTicketToDefault(ticketValidationRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/updateToPaid")
    public ResponseEntity<?> updateTicketToPaid(@RequestBody TicketValidationRequest ticketValidationRequest) {
        ticketService.updateToPaidTicket(ticketValidationRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
