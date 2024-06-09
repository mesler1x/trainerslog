package ru.npcric.asparagus.trainerslog.service;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.npcric.asparagus.trainerslog.adapter.repository.ChequeRepository;
import ru.npcric.asparagus.trainerslog.adapter.repository.StudentRepository;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.cheque.ChequeConfirmRequest;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.cheque.ChequeCreateRequest;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.cheque.ChequeDeleteRequest;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.cheque.ChequeFullResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.errors.UserNotFoundException;
import ru.npcric.asparagus.trainerslog.domain.ChequeEntity;
import ru.npcric.asparagus.trainerslog.domain.StudentEntity;
import ru.npcric.asparagus.trainerslog.domain.user.UserEntity;
import ru.npcric.asparagus.trainerslog.service.factory.ChequeFactory;
import ru.npcric.asparagus.trainerslog.service.mapper.ChequeMapper;

import java.time.LocalDateTime;
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
    ChequeFactory chequeFactory;
    ChequeRepository chequeRepository;

    public ChequeFullResponse addNewChequeToStudent(UserEntity user, ChequeCreateRequest chequeCreateRequest) {
        ChequeEntity.ChequeContext context = chequeFactory.createContext(chequeCreateRequest, user);
        ChequeEntity chequeEntity = new ChequeEntity(context);
        StudentEntity student = chequeEntity.getStudent();
        int debt = (student.getBalance() < 0) ? student.getBalance() * (-1) : 0;
        List<ChequeEntity> listCheques = student.getChequeEntities();
        listCheques.add(chequeEntity);
        student.setChequeEntities(listCheques);
        ChequeEntity chequeEntityWithId = chequeRepository.save(chequeEntity);
        return chequeMapper.entityToFullResponse(chequeEntityWithId, debt, student.getBalance());
    }

    public List<ChequeFullResponse> getChequesByUsername(String username) {
        List<ChequeFullResponse> chequeFullResponses = new ArrayList<>();
        Optional<StudentEntity> student = studentRepository.findByUser_Username(username);

        if (student.isEmpty()) throw new UserNotFoundException(username);
        StudentEntity studentEntity = student.get();
        int debt = (studentEntity.getBalance() < 0) ? studentEntity.getBalance() * (-1) : 0;
        List<ChequeEntity> listCheques = studentEntity.getChequeEntities();
        for (ChequeEntity chequeEntity : listCheques) {
            chequeFullResponses.add(chequeMapper.entityToFullResponse(chequeEntity, debt, studentEntity.getBalance()));
        }

        return chequeFullResponses;
    }

    public ChequeFullResponse confirmCheque (ChequeConfirmRequest request) {
        ChequeEntity chequeEntity = chequeRepository.findByDate(request.dateTime());
        StudentEntity student = chequeEntity.getStudent();
        int debt = (student.getBalance() < 0) ? student.getBalance() * (-1) : 0;
        int studentBalance = student.getBalance();
        chequeEntity.setPaymentAmount(request.amount());
        chequeEntity.setLink(request.link());
        chequeEntity.setIsChecked(true);
        student.setBalance(studentBalance + request.amount());
        chequeRepository.save(chequeEntity);

        return chequeMapper.entityToFullResponse(chequeEntity, debt, student.getBalance());
    }

    public void deleteChequeFromStudent(UserEntity user, ChequeDeleteRequest request) {
        Optional<StudentEntity> student = studentRepository.findByUser_Username(user.getUsername());
        if(student.isEmpty()) throw new UserNotFoundException(user.getUsername());
        StudentEntity studentEntity = student.get();
        List<ChequeEntity> listCheques = studentEntity.getChequeEntities();
        for(ChequeEntity chequeEntity : listCheques) {
            if(chequeEntity.getDate().isEqual(request.dateTime())) {
                listCheques.remove(chequeEntity);
                studentEntity.setChequeEntities(listCheques);
                chequeRepository.delete(chequeEntity);
                break;
            }
        }
    }
}
