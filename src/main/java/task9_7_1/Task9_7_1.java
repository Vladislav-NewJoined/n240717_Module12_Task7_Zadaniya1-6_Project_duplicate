package task9_7_1;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

// Создан Telegram бот:
// botName: MyTestBot_001
// userName: @qytewqwww_Bot  или  qytewqwww_Bot
// token: 7057416920:AAEzJF-2L8i8GdyLnkqMThUyyXk6BQOdoAk

public class Task9_7_1 {
    public static void main(String[] args) throws Exception {
        System.out.println("""
            Задание:\s
            Модуль 9. UI и использование готовых SDK. Задание №5:\s
                1. Попробуйте создать отдельный класс с какими - либо методами  которые можно было бы запустить из
                   меню бота с клавиатуры (привязать метод к кнопке в боте).\s

            Решение:\s""");

        System.out.println("В Telegram боте созданы кнопки и надписи к ним с использованием механизма 'рефлексия' " +
                "(reflection). \nОтредактированное изображение сохранено в файле 'cloned_image.jpg' в папке: " +
                "`src/main/java/task9_7_1`. \nА также кнопки появились под сообщением в Telegram боте. Снимок " +
                "экрана сохранён в файле 'buttonsInTelegramBot.jpg' в папке: `src/main/java/task9_7_1`.");

        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        BotSession botSession = api.registerBot(new Bot());

    }
}
