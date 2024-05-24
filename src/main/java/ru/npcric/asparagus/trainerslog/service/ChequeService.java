package ru.npcric.asparagus.trainerslog.service;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.npcric.asparagus.trainerslog.adapter.repository.StudentRepository;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.cheque.UsernameAndLinkRequest;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.cheque.StudentWithChequesResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.errors.UserNotFoundException;
import ru.npcric.asparagus.trainerslog.domain.StudentEntity;
import ru.npcric.asparagus.trainerslog.domain.user.UserEntity;
import ru.npcric.asparagus.trainerslog.service.mapper.ChequeMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
@RequiredArgsConstructor
public class ChequeService {
    StudentRepository studentRepository;
    ChequeMapper chequeMapper;

    public StudentWithChequesResponse addNewChequeToStudent(UserEntity user, String link) {
        Optional<StudentEntity> student = studentRepository.findByUser_Username(user.getUsername());
        StudentEntity studentEntity;
        if (student.isPresent()) {
            studentEntity = student.get();
            List<String> cheques = studentEntity.getLinkToCheques();
            if (cheques == null) {
                cheques = new ArrayList<>();
            }
            studentEntity.setLinkToCheques(cheques);
            studentEntity.getLinkToCheques().add(link);
            return chequeMapper.entityToResponse(studentEntity);
        }



        throw new UsernameNotFoundException(user.getUsername());
    }

    public StudentWithChequesResponse getChequesByUsername(String username) {
        Optional<StudentEntity> student = studentRepository.findByUser_Username(username);
        if (student.isPresent()) {
            StudentEntity studentEntity = student.get();
            return chequeMapper.entityToResponse(studentEntity);
        }
        throw new UserNotFoundException(username);
    }

    public StudentWithChequesResponse deleteChequeFromStudent(UserEntity user, String linkToRemove) {
        Optional<StudentEntity> student = studentRepository.findByUser_Username(user.getUsername());

        if (student.isPresent()) {
            StudentEntity studentEntity = student.get();
            List<String> linkToCheques = studentEntity.getLinkToCheques();
            for (int i = 0; i < linkToCheques.size(); i++) {
                String currentCheque = linkToCheques.get(i);
                if (linkToRemove.equalsIgnoreCase(currentCheque)) {
                    studentEntity.getLinkToCheques().remove(i);
                    break;
                }
            }
            return chequeMapper.entityToResponse(studentEntity);
        }


       throw new UserNotFoundException(user.getUsername());
    }
}
