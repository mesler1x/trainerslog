package ru.npcric.asparagus.trainerslog.adapter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.npcric.asparagus.trainerslog.domain.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
}
