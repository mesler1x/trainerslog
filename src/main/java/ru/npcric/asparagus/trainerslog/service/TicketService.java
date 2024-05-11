package ru.npcric.asparagus.trainerslog.service;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.npcric.asparagus.trainerslog.adapter.repository.StudentRepository;
import ru.npcric.asparagus.trainerslog.adapter.repository.TicketRepository;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.ticket.TicketValidationRequest;
import ru.npcric.asparagus.trainerslog.domain.StudentEntity;
import ru.npcric.asparagus.trainerslog.domain.TicketEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PUBLIC)
@RequiredArgsConstructor
public class TicketService {
    TicketRepository ticketRepository;
    StudentRepository studentRepository;
    int INITIAL_COST = 1000;
    //метод для шедулера --> обнуляет тикеты у всех и создает все новый дефолт тикет
    @Transactional
    public void updateValidTicketToDefault(TicketValidationRequest ticketValidationRequest) {
        StudentEntity studentEntity = studentRepository.findByUser_Username(ticketValidationRequest.username());
        Long ticketId = studentEntity.getTicket().getId();
        studentRepository.updateStudentByIdSetTicketToNull(studentEntity.getId());
        ticketRepository.deleteById(ticketId);

        TicketEntity newTicket = getDefaultTicket();
        studentEntity.setTicket(newTicket);
    }

    public TicketEntity createTicketForNewStudent() {
        TicketEntity ticketEntity = getDefaultTicket();
        return ticketEntity;
    }

    public TicketEntity getDefaultTicket() {
        TicketEntity ticketEntity = ticketRepository.save(new TicketEntity(LocalDate.now(),
                LocalDate.now().plusMonths(1), true, INITIAL_COST));
        return ticketEntity;
    }
}
