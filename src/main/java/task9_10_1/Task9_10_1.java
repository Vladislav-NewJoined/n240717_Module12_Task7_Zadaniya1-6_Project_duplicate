package task9_10_1;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

// Для этого проекта создан Бот:
// TestBot_005
// kkkllll_005_bot
// 6882256834:AAH5Fg-wUdKw7Rdqj8s9kXDgVt0R08tDnlY

// Это КРАЙНИЙ МЕТОД, Draft_Module9_Task10_Zadaniya1-10_Project_part17, ПЕРЕД ПЕРЕПИСКОЙ НА ЧИСТОВИК

public class Task9_10_1 {
    public static void main(String[] args) throws Exception {
        System.out.println("""
                Задание:\s
                Модуль 9. UI и использование готовых SDK. Задание №10 Проект:\s
                    Создать телеграмм-бота для управления изображениями
                    Бот должен уметь:
                    - применять фильтр для отправленного изображения и отправлять обработанное фото обратно
                    - выбор фильтра с помощью кастомизируемой клавиатуры
                    Админ панель:
                    - отображение списка пользователей
                    - включение/выключение бота
                    Пошаговое выполнение проекта
                    1. Знакомство с telegram bots api
                    2. Создание примитивного бота
                    3. Интеграция кода из задания с лямбдами в проект для обработки фото
                    4. Чтение и отправка фото из чата с пользователем
                    5. Знакомство с ReplyKeyboardMarkup, подключение кастомизируемой клавиатуры
                    6. Автоматическое создание кнопок из методов с помощью рефлексии
                    7. Создание возможности выбора фильтра для изображения с помощью кастомизируемой клавиатуры
                    8. Знакомство с Java Swing
                    9. Создание UI для админ-панели
                    10. Интеграция админ-панели с телеграм-ботом\s

                Решение:\s""");

        System.out.println("Для этого проекта cоздан тестовый Telegram бот:\n" +
                "botName: TestBot_005\n" +
                "userName: @kkkllll_005_bot или kkkllll_005_bot\n" +
                "token: 6882256834:AAH5Fg-wUdKw7Rdqj8s9kXDgVt0R08tDnlY\n");
        System.out.println("Пояснение к работе Telegram бота: Inline-клавиатура появляется после отправки в чат изображения." +
                "Меню 'Админ. панель' (это одна из кнпок внизу в inline-клавиатуре) с двумя кнопками текстовых команд: " +
                "'/users_list' и '/on_off_bot', появляется также после отправки в чат изображения." +
                "Отредактированные изображения сохраняются в папке `src/main/java/task9_10_1/images`.");

        System.out.println("Ваша программа начинает выполнение...");

        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        BotSession botSession = api.registerBot(new Bot());
    }
}
