package ru.npcric.asparagus.trainerslog.adapter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.npcric.asparagus.trainerslog.domain.TicketEntity;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
}
