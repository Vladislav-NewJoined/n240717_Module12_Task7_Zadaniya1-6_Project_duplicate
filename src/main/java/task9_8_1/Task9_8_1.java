package task9_8_1;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import task9_8_1.Bot;

// Создан Telegram бот:
// botName: MyTestBot_002
// userName: @lgcyrrerrr_002_bot  или  lgcyrrerrr_002_bot
// token: 7042048331:AAFXdhcICubXad5RxZGyItCIi7L6IUwvaJo

public class Task9_8_1 {
    public static void main(String[] args) throws Exception {
        System.out.println("""
            Задание:\s
            Модуль 9. UI и использование готовых SDK. Задание №8:\s
                1. Продолжая работу над ботом из предыдущих уроков реализуйте дополнительный фильтр
                   для обработки изображения при помощи которого отправленное  изображение пользователя
                   при соответствующей команде с его стороны,  будет перекрашено в красный цвет
                   (наложен красный фильтр) как это показано в уроке\s

            Решение:\s""");

        System.out.println("Создан Telegram бот:\n" +
                "botName: MyTestBot_002\n" +
                "userName: @lgcyrrerrr_002_bot или lgcyrrerrr_002_bot\n" +
                "token: 7042048331:AAFXdhcICubXad5RxZGyItCIi7L6IUwvaJo\n");
        System.out.println("В соответствии с заданием нужно выбрать фильтр 'onlyRed', но Bot также " +
                "перекрашивает и в другие цвета. Отредактированные изображения сохраняются в папке " +
                "`src/main/java/task9_8_1/images`.");

        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        BotSession botSession = api.registerBot(new Bot());
    }
}
