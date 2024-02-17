package coursework.three.homeworkInformer.repository;

import coursework.three.homeworkInformer.model.NotificationTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationTaskRepository extends JpaRepository<NotificationTask, Long> {
    List<NotificationTask> findAllByDate(LocalDateTime minute);
    List<NotificationTask> findAllByDateLessThan(LocalDateTime minute);
}