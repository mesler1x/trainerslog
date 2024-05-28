package ru.npcric.asparagus.trainerslog.service.factory;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import ru.npcric.asparagus.trainerslog.adapter.repository.UserRepository;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.student.StudentDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.errors.AlreadyExistException;
import ru.npcric.asparagus.trainerslog.adapter.web.errors.UserNotFoundException;
import ru.npcric.asparagus.trainerslog.domain.StudentEntity;
import ru.npcric.asparagus.trainerslog.domain.TicketEntity;
import ru.npcric.asparagus.trainerslog.domain.user.UserEntity;
import ru.npcric.asparagus.trainerslog.domain.user.UserRole;
import ru.npcric.asparagus.trainerslog.service.TicketService;

import java.util.Optional;


@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class StudentFactory {
    UserRepository userRepository;
    TicketService ticketService;
    int INITIAL_COST = 1000;
    public StudentEntity.StudentContext createContext(StudentDTO studentDTO) {
        Optional<UserEntity> user = userRepository.findByUsername(studentDTO.username());


        if (user.isEmpty()) throw new UserNotFoundException(studentDTO.username());
        else if(user.get().getAuthorities().contains(UserRole.STUDENT)){
            throw new AlreadyExistException("Student");
        }
        TicketEntity ticketEntity = ticketService.createTicketForNewStudent();
        UserEntity userEntity = user.get();

        userEntity.getAuthorities().add(UserRole.STUDENT);
        userEntity.getAuthorities().remove(UserRole.DEFAULT);

        //todo - засунуть в маппер
        return new StudentEntity.StudentContext(
                ticketEntity,
                studentDTO.fullName(),
                studentDTO.sex(),
                studentDTO.birthDate(),
                studentDTO.q(),
                studentDTO.phoneNumber(),
                studentDTO.parentPhoneNumber(),
                studentDTO.parentFullName(),
                studentDTO.eemail(),
                userEntity);
    }
}
