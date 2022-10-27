import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import tg.myTgBot;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static final int WORK = 1;
    public static final int BREAK = 1;

    public static void main(String[] args) throws InterruptedException, TelegramApiException {
        new myTgBot();
        System.out.println("Привет!\nЯ твой личный тайм-менеджер Помидорка :)\nВведи количество своих планов на сегодня: ");
        int counterTasks = new Scanner(System.in).nextInt();

        long startTime = System.currentTimeMillis();
        for (int i = 1; i <= counterTasks; i++) {
            Timer(i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Таймер Помидорки истёк: " + (endTime - startTime) / (1000 * 60) + " мин");
    }

    public static void Timer (int counter) throws InterruptedException {
        System.out.println("Задача № " + counter);
        printProgress("Work Progress :: ", WORK);
        printProgress("Break Progress :: ", BREAK);
    }

    private static void printProgress(String process, int time) throws InterruptedException {
        int length;
        int repeat;
        length = 60 * time / 30;
        repeat = 60 * time / length;
        int stretch = 30 / (3 * time);
        for (int i = 1; i <= repeat; i++) {
            double x = i;
            x = 1.0 / 3.0 * x;
            x *= 10;
            x = Math.round(x);
            x /= 10;
            double w = time * stretch;
            double percent = (x / w) * 1000;
            x /= stretch;
            x *= 10;
            x = Math.round(x);
            x /= 10;
            percent = Math.round(percent);
            percent /= 10;
            System.out.print(process + percent + "%" + (" ")
                    .repeat(5 - (String.valueOf(percent).length())) + "[" + ("#")
                    .repeat(i) + ("-").repeat(repeat - i) + "]     (" + x + " мин / " + time + " мин)" + "\r");
            TimeUnit.SECONDS.sleep(length);
        }
        System.out.println();
    }
}
