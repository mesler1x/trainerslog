package ru.npcric.asparagus.trainerslog.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.npcric.asparagus.trainerslog.domain.common.BaseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ticket")
public class TicketEntity extends BaseEntity {
    LocalDate startDate;
    LocalDate endDate;
    Boolean isExpired;
    Integer paidAmount;
}
