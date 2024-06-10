package task9_7_1_part1;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

// Создан Telegram бот:
// botName: MyTestBot_002
// userName: @lgcyrrerrr_002_bo  или  lgcyrrerrr_002_bo
// token: 7042048331:AAFXdhcICubXad5RxZGyItCIi7L6IUwvaJo

public class Task9_7_1_part1 {
    public static void main(String[] args) throws Exception {
        System.out.println("""
            Задание:\s
            Модуль 9. UI и использование готовых SDK. Задание №7:\s
                1. Продолжая работу над ботом из предыдущих уроков, реализуйте возможность вызова
                   соответствующего обработчика (класса)  в зависимости от того, прислал пользователь
                   изображение или текстовое сообщение\s

            Решение:\s""");

        System.out.println("Создан Telegram бот:\n" +
                "botName: MyTestBot_002\n" +
                "userName: @lgcyrrerrr_002_bot или lgcyrrerrr_002_bot\n" +
                "token: 7042048331:AAFXdhcICubXad5RxZGyItCIi7L6IUwvaJo\n" +
                "\n" +
                "Предложено два решения:\n" +
                "-первое в папке package:\n" +
                "‘task9_7_1_part1’ - обрабатывает одно изображение\n" +
                "- второе в папке package:\n" +
                "‘task9_7_1_part2’ - обрабатывает несколько изображений\n");
        System.out.println("В Telegram боте созданы кнопки и надписи к ним с использованием механизма 'рефлексия' " +
                "(reflection). \nОтредактированное изображение сохранено в файле 'cloned_image.jpg' в папке: " +
                "`src/main/java/task9_7_1_part1`. \nТакже кнопки создаются под сообщением в Telegram боте.");

        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        BotSession botSession = api.registerBot(new Bot());

    }
}
