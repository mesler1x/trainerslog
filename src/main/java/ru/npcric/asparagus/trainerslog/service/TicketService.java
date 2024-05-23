package ru.npcric.asparagus.trainerslog.service;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.npcric.asparagus.trainerslog.adapter.repository.StudentRepository;
import ru.npcric.asparagus.trainerslog.adapter.repository.TicketRepository;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.ticket.TicketValidationRequest;
import ru.npcric.asparagus.trainerslog.adapter.web.errors.UserNotFoundException;
import ru.npcric.asparagus.trainerslog.domain.StudentEntity;
import ru.npcric.asparagus.trainerslog.domain.TicketEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PUBLIC)
@RequiredArgsConstructor
public class TicketService {
    TicketRepository ticketRepository;
    StudentRepository studentRepository;
    int INITIAL_COST = 1000;
    @Transactional
    public void updateValidTicketToDefault(TicketValidationRequest ticketValidationRequest) {
        Optional<StudentEntity> student = studentRepository.findByUser_Username(ticketValidationRequest.username());
        if (student.isEmpty()) throw new UserNotFoundException(ticketValidationRequest.username());
        StudentEntity studentEntity = student.get();
        Long ticketId = studentEntity.getTicket().getId();
        studentRepository.updateStudentByIdSetTicketToNull(studentEntity.getId());
        ticketRepository.deleteById(ticketId);

        TicketEntity newTicket = getDefaultTicket();
        studentEntity.setTicket(newTicket);
    }

    @Transactional
    public void updateToPaidTicket(TicketValidationRequest ticketValidationRequest) {
        Optional<StudentEntity> student = studentRepository.findByUser_Username(ticketValidationRequest.username());
        if (student.isEmpty()) throw new UserNotFoundException(ticketValidationRequest.username());
        StudentEntity studentEntity = student.get();
        TicketEntity ticket = studentEntity.getTicket();
        ticket.setPaidAmount(0);
        ticket.setIsExpired(false);
        ticketRepository.save(ticket);
    }

    public TicketEntity createTicketForNewStudent() {
        return getDefaultTicket();
    }

    public TicketEntity getDefaultTicket() {
        return ticketRepository.save(new TicketEntity(LocalDate.now(),
                LocalDate.now().plusMonths(1), true, INITIAL_COST));
    }
}
