package ru.npcric.asparagus.trainerslog.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.npcric.asparagus.trainerslog.adapter.repository.CoachRepository;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.Coach;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.Group;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.Student;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.Ticket;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.CoachResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.GroupsResponse;
import ru.npcric.asparagus.trainerslog.domain.CoachEntity;
import ru.npcric.asparagus.trainerslog.domain.GroupEntity;
import ru.npcric.asparagus.trainerslog.domain.StudentEntity;
import ru.npcric.asparagus.trainerslog.domain.TicketEntity;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CoachService {
    CoachRepository coachRepository;

    //Метод выводит всех тренеров, а именно, имя тренеров и для каждого тренера его группы
    public List<CoachResponse> findAll() {
        List<CoachEntity> coachEntities = coachRepository.findAll();
        List<CoachResponse> coachesResponse = new ArrayList<>();
        for(CoachEntity coachEntity : coachEntities) {
            List<GroupEntity> groupEntities = coachEntity.getGroups();
            List<GroupsResponse> groupsResponse = new ArrayList<>();
            for(GroupEntity group : groupEntities) {
                GroupsResponse allGroupsResponseWithCoach =
                        new GroupsResponse(group.getGroupName(), group.getDates());
                groupsResponse.add(allGroupsResponseWithCoach);
            }

            CoachResponse coach = new CoachResponse(coachEntity.getName(), groupsResponse);
            coachesResponse.add(coach);
        }
        return coachesResponse;
    }

    public CoachEntity create(Coach coach) {
        CoachEntity coachEntity = new CoachEntity();
        coachEntity.setName(coachEntity.getName());
        List<GroupEntity> groups = new ArrayList<>();
        List<StudentEntity> students = new ArrayList<>();
        List<Group> DTOGroupList = coach.groups();
        for(Group groupDTO : DTOGroupList) {
            List<Student> DTOStudentList = groupDTO.students();
            for(Student studentDTO : DTOStudentList) {
                StudentEntity studentEntity = new StudentEntity();
                studentEntity.setFullName(studentDTO.fullName());
                studentEntity.setSex(studentDTO.sex());
                studentEntity.setBirthDate(studentDTO.birthDate());
                studentEntity.setAge(studentDTO.age());
                studentEntity.setQ(studentDTO.q());
                studentEntity.setPhoneNumber(studentDTO.phoneNumber());
                studentEntity.setParentPhoneNumber(studentDTO.parentPhoneNumber());
                studentEntity.setParentFullName(studentDTO.parentFullName());

                TicketEntity ticket = new TicketEntity();
                Ticket ticketDTO = studentDTO.ticket();
                ticket.setStudent(studentEntity);
                ticket.setStartDate(ticketDTO.startDate());
                ticket.setEndDate(ticketDTO.endDate());
                ticket.setIsExpired(ticketDTO.isExpired());
                ticket.setPaidAmount(ticketDTO.paidAmount());

                studentEntity.setTicket(ticket);
                students.add(studentEntity);
            }
            GroupEntity group = new GroupEntity();
            group.setGroupName(groupDTO.groupName());
            group.setAddress(groupDTO.address());
            group.setDates(groupDTO.dates());
            group.setStudents(students);
            group.setCoach(coachEntity);
            groups.add(group);
        }
        coachEntity.setGroups(groups);
        return coachRepository.save(coachEntity);
    }
}
