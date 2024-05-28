package ru.npcric.asparagus.trainerslog.service.scheduler;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.npcric.asparagus.trainerslog.adapter.repository.TrainingRepository;
import ru.npcric.asparagus.trainerslog.domain.TrainingEntity;
import ru.npcric.asparagus.trainerslog.service.TrainingService;

import java.time.LocalDateTime;
import java.util.List;

@Log4j2
@EnableAsync
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TrainingScheduler {
    TrainingRepository trainingRepository;

    @SchedulerLock(name = "duplicateLastWeekTrainings")
    //Каждый понедельник в 00:01
    @Scheduled(cron = "0 1 0 ? * MON", zone = "Asia/Yekaterinburg")
    @SneakyThrows
    @Transactional
    public void autoCreateTrainings() {
        log.info("Scheduler duplicateLastWeekTrainings start");

        LocalDateTime nowTime = LocalDateTime.now();
        List<TrainingEntity> trainingEntitiesOfLastWeek = trainingRepository.findTrainingsForThePastWeek(nowTime.minusWeeks(1), nowTime);
        for(TrainingEntity trainingEntity : trainingEntitiesOfLastWeek) {
            TrainingEntity newTraining = new TrainingEntity();
            newTraining.setGroup(trainingEntity.getGroup());
            newTraining.setDate(trainingEntity.getDate().plusWeeks(1));
            newTraining.setEndDate(trainingEntity.getEndDate().plusWeeks(1));
            trainingRepository.save(newTraining);
        }

        log.info("Scheduler duplicateLastWeekTrainings end");
    }
}
