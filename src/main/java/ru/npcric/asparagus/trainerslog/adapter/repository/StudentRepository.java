package ru.npcric.asparagus.trainerslog.adapter.repository;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.npcric.asparagus.trainerslog.domain.CoachEntity;
import ru.npcric.asparagus.trainerslog.domain.StudentEntity;
import ru.npcric.asparagus.trainerslog.domain.user.UserEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    StudentEntity findByFullName(@NotBlank String name);
    List<StudentEntity> findAllByUser_UsernameIn(List<String> studentUsernames);
    Optional<StudentEntity> findByUser_Username(String username);
    StudentEntity findByUser(UserEntity user);

    @Transactional
    @Modifying
    @Query("UPDATE StudentEntity s set s.group = null WHERE s.id = :studentId")
    void updateStudentByIdSetTicketToNull(@Param("studentId") Long studentId);

    @Query("SELECT s FROM StudentEntity s WHERE s.ticket.endDate < :currentDate")
    List<StudentEntity> findStudentsWithExpiredTickets(LocalDate currentDate);
}
