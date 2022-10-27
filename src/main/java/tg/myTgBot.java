package tg;

import com.sun.tools.javac.Main;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import tg.commands.BeginCommand;
import tg.commands.HelpCommand;

import java.util.concurrent.TimeUnit;

public class myTgBot extends TelegramLongPollingCommandBot {
    public static final String BOT_TOKEN = "5540362490:AAGw5if0rDsXZFUndKPNsjTi64hz1MgyGXY";
    public static final String BOT_USERNAME = "PomodoroMyTimer_bot";
    public static final String CHAT_ID = "719529955";

    public static final int WORK = 25;
    public static final int BREAK = 5;

    public myTgBot() throws TelegramApiException {
        TelegramBotsApi botApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            botApi.registerBot(this);
            register(new BeginCommand("begin", "Начать"));
            register(new HelpCommand("help", "Помощь"));
        }catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        if (update.hasMessage() && isNumeric(update.getMessage().getText()) && BeginCommand.isStarted) {
            BeginCommand.isStarted = false;
            String message;
            int count = Integer.parseInt(update.getMessage().getText());
            for (int i = 1; i <= count; i++) {
                message = "Задача №" + i + " началась";
                sendMessage(message);
                try {
                   TimeUnit.MINUTES.sleep(WORK);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                message = "Задача №" + i + " завершилась";
                sendMessage(message);
                if (count != 0) {
                    message = "Время отдыха";
                    sendMessage(message);
                    try {
                        TimeUnit.MINUTES.sleep(BREAK);

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    message = "Пора вернуться за работу";
                    sendMessage(message);
                }
            }
        }else {
            String message = "Я тебя не понимаю :(";
            sendMessage(message);
        }
    }

    private void sendMessage(String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(CHAT_ID);
        sendMessage.setText(message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return  false;
        }
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
