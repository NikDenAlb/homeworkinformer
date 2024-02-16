package coursework.three.homeworkInformer.repository;

import coursework.three.homeworkInformer.model.NotificationTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationTaskRepository extends JpaRepository<NotificationTask, Long> {
    @Query(value = "SELECT * FROM notifications WHERE date = ?1",
            nativeQuery = true)
    List<NotificationTask> getTasksofMinute(LocalDateTime minute);
}