package coursework.three.homeworkInformer.service;

import coursework.three.homeworkInformer.model.NotificationTask;
import coursework.three.homeworkInformer.repository.NotificationTaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class NotificationTaskService {
    private static final Pattern PATTERN = Pattern.compile("([0-9\\.\\:\\s]{16})(\\s)([\\W+]+)");
    private final NotificationTaskRepository repository;


    public NotificationTaskService(NotificationTaskRepository repository) {
        this.repository = repository;
    }

    public boolean addNotification(String text, long chatId) {
        Matcher matcher = PATTERN.matcher(text);
        boolean out = matcher.matches();
        if (out) {
            String date = matcher.group(1);
            String message = matcher.group(3);
            LocalDateTime pDate = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
            repository.save(new NotificationTask(chatId, message, pDate));
        }
        return out;
    }

    public List<NotificationTask> getTasksofMinute(LocalDateTime minute) {
        return repository.getTasksofMinute(minute);
    }
}