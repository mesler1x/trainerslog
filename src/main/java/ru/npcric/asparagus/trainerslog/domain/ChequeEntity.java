package ru.npcric.asparagus.trainerslog.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.npcric.asparagus.trainerslog.domain.common.BaseEntity;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cheque")
public class ChequeEntity extends BaseEntity {
    @Column(name = "date")
    LocalDateTime date;

    @Column(name = "payment_amount")
    Integer paymentAmount;

    @Column(name = "link")
    String link;

    @Column(name = "is_checked")
    Boolean isChecked;

    @ManyToOne
    @JoinColumn(name = "student_id")
    StudentEntity student;

    public ChequeEntity(ChequeContext context){
        this.date = LocalDateTime.now();
        this.paymentAmount = 0;
        this.link = context.link;
        this.isChecked = false;
        this.student = context.student;
    }

    public record ChequeContext(
            String link, StudentEntity student
    ){}
}
