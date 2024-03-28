package ru.npcric.asparagus.trainerslog.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @OneToOne
    TicketEntity ticket;
    String fullName;
    String sex;
    String birthDate;
    @ManyToOne
    @JsonIgnore
    GroupEntity group;
    int age;
    int q;
    String phoneNumber;
    String parentPhoneNumber;
    String parentFullName;
    @ElementCollection
    Map<String, Boolean> visitedStatistics;
}
