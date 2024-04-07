package ru.npcric.asparagus.trainerslog.adapter.repository;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.npcric.asparagus.trainerslog.domain.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    StudentEntity findByFullName(@NotBlank String name);
}
