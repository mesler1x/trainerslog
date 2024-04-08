package ru.npcric.asparagus.trainerslog.adapter.repository;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.npcric.asparagus.trainerslog.domain.StudentEntity;
import ru.npcric.asparagus.trainerslog.domain.user.UserEntity;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    StudentEntity findByFullName(@NotBlank String name);
    List<StudentEntity> findAllByUser_UsernameIn(List<String> studentUsernames);
}
