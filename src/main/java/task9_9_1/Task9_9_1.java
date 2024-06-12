package task9_9_1;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

// Создан Telegram бот:
// botName: MyTestBot_002
// userName: @lgcyrrerrr_002_bot  или  lgcyrrerrr_002_bot
// token: 7042048331:AAFXdhcICubXad5RxZGyItCIi7L6IUwvaJo

public class Task9_9_1 {
    public static void main(String[] args) throws Exception {
        System.out.println("""
            Задание:\s
            Модуль 9. UI и использование готовых SDK. Задание №9:\s
                1. Продолжая работу над ботом из предыдущих уроков реализуйте возможность
                   выбора фильтра  при отправке пользователем изображения прямо из меню бота\s

            Решение:\s""");

        System.out.println("Создан Telegram бот:\n" +
                "botName: MyTestBot_002\n" +
                "userName: @lgcyrrerrr_002_bot или lgcyrrerrr_002_bot\n" +
                "token: 7042048331:AAFXdhcICubXad5RxZGyItCIi7L6IUwvaJo\n");
        System.out.println("Bot перекрашивает в цвета меню бота. Отредактированные изображения" +
                " сохраняются в папке `src/main/java/task9_9_1/images`.");

        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        BotSession botSession = api.registerBot(new Bot());
    }
}
