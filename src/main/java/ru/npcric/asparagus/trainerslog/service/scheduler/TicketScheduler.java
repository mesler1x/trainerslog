package ru.npcric.asparagus.trainerslog.service.scheduler;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.npcric.asparagus.trainerslog.adapter.repository.GroupRepository;
import ru.npcric.asparagus.trainerslog.adapter.repository.StudentRepository;
import ru.npcric.asparagus.trainerslog.adapter.repository.TicketRepository;
import ru.npcric.asparagus.trainerslog.domain.StudentEntity;
import ru.npcric.asparagus.trainerslog.domain.TicketEntity;
import ru.npcric.asparagus.trainerslog.service.TicketService;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@EnableAsync
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TicketScheduler {
    StudentRepository studentRepository;
    TicketService ticketService;
    TicketRepository ticketRepository;


    @SchedulerLock(name = "UpdateAllTicketsStatus")
    @Scheduled(cron = "0 */10 * * * *")
    @SneakyThrows
    @Transactional
    public void autoUpdateAllTicketsStatus() {
        log.info("Scheduler updateAllTicketsStatus start");
        List<StudentEntity> students = studentRepository.findStudentsWithExpiredTickets(LocalDate.now());
        for (StudentEntity student : students) {
            TicketEntity ticket = student.getTicket();
            TicketEntity newTicket = ticketService.getDefaultTicket();
            student.setTicket(newTicket);
            studentRepository.save(student);
            ticketRepository.delete(ticket);
        }

        log.info("Scheduler updateAllTicketsStatus end");
    }
}
