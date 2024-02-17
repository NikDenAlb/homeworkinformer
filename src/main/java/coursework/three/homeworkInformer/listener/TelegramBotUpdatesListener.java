package coursework.three.homeworkInformer.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import coursework.three.homeworkInformer.service.NotificationTaskService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;

    @Autowired
    private NotificationTaskService noteService;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.debug("Processing update: {}", update);
            // Process your updates here

            if ("/start".equals(update.message().text())) {
                telegramBot.execute(new SendMessage(update.message().chat().id(), "Hello message"));
                return;
            }

            telegramBot.execute(new SendMessage(update.message().chat().id(),
                    noteService.addNotification(update.message().text(), update.message().chat().id()) ?
                            "Notice added" : "Wrong command"));
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}