package tg.commands;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class BeginCommand extends ServiceCommand{
    public BeginCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    public static boolean isStarted = false;

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        sendAnswer(absSender, chat.getId(),
                "Введи своё количество дел на сегодня,\n" +
                        "Помни, одно дело = одна помидорка = 25 минут\n" +
                        "Для помощи введи '/help'");
        isStarted = true;
    }
}
