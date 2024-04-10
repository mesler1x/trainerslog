package ru.npcric.asparagus.trainerslog.adapter.repository;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.npcric.asparagus.trainerslog.domain.StudentEntity;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    StudentEntity findByFullName(@NotBlank String name);
    List<StudentEntity> findAllByUser_UsernameIn(List<String> studentUsernames);
    StudentEntity findByUser_Username(String username);
}
