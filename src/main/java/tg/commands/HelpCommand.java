package tg.commands;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class HelpCommand extends ServiceCommand{
    public HelpCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
            sendAnswer(absSender, chat.getId(),
                    "Привет! Я твой личный тайм-менеджер Помидорка :)\n" +
                            "Тебе нужно задать количество твоих задач на сегодня,\n" +
                            "а я помогу тебе сосредоточиться, ведб самое главное в любом деле - не отвлекаться!\n" +
                            "Чтобы начать введи команду '/begin'");
    }
}
