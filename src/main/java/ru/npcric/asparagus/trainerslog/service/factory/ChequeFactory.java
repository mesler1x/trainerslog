package ru.npcric.asparagus.trainerslog.service.factory;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.npcric.asparagus.trainerslog.adapter.repository.StudentRepository;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.cheque.ChequeCreateRequest;
import ru.npcric.asparagus.trainerslog.domain.ChequeEntity;
import ru.npcric.asparagus.trainerslog.domain.StudentEntity;
import ru.npcric.asparagus.trainerslog.domain.user.UserEntity;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ChequeFactory {
    StudentRepository studentRepository;

    public ChequeEntity.ChequeContext createContext(ChequeCreateRequest chequeCreateRequest, UserEntity user){
        Optional<StudentEntity> student = studentRepository.findByUser_Username(user.getUsername());
        if(student.isEmpty()) throw new UsernameNotFoundException(user.getUsername());
        StudentEntity studentEntity = student.get();
        return new ChequeEntity.ChequeContext(
                chequeCreateRequest.link(), studentEntity
        );
    }
}
