package ru.npcric.asparagus.trainerslog.adapter.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.ticket.TicketValidationRequest;
import ru.npcric.asparagus.trainerslog.service.TicketService;

@Validated
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/trainerslog/api/v1/ticket")
@Tag(name = "Контроллер абонемента", description = "Контроллер для управления абонементами учеников, федерации айкидо")
public class TicketController {
    TicketService ticketService;

    @Operation(
            summary = "Обновляет абонемент каждый месяц",
            description = "Обновление абонемента на дефолтный, 1000р - задолженность"
    )
    @RolesAllowed("ADMIN")
    @PatchMapping("/monthlyUpdate")
    public ResponseEntity<?> monthlyUpdateTicketForStudent(@RequestBody TicketValidationRequest ticketValidationRequest) {
        ticketService.updateValidTicketToDefault(ticketValidationRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            summary = "Обновление абонемента, при его оплате"
    )
    @RolesAllowed({"ADMIN","COACH"})
    @PatchMapping("/updateToPaid")
    public ResponseEntity<?> updateTicketToPaid(@RequestBody TicketValidationRequest ticketValidationRequest) {
        ticketService.updateToPaidTicket(ticketValidationRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
