package coursework.three.homeworkInformer.shelduled;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import coursework.three.homeworkInformer.repository.NotificationTaskRepository;
import coursework.three.homeworkInformer.service.NotificationTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class Schelduled {

    @Autowired
    private TelegramBot telegramBot;
    @Autowired
    private NotificationTaskService noteService;

    @Autowired
    private NotificationTaskRepository repository;

    private final Logger logger = LoggerFactory.getLogger(Schelduled.class);

    @Scheduled(cron = "0 * * * * *")
    public void everyMinute() {
        logger.debug("everyMinute()");
        LocalDateTime currMinute = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        noteService.findAllByDate(currMinute).forEach(task -> {
                    telegramBot.execute(new SendMessage(task.getChatId(), task.getMessage()));
                    logger.info(task.toString());
                }
        );
    }

    @Scheduled(cron = "@weekly")
    public void weekCleaning() {
        logger.debug("weeklyCleaning()");
        repository.deleteAll(repository.findAllByDateLessThan(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)));
    }
}
