package zadanye0;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Zadanye0 {
    public static void main(String[] args) {
        System.out.println("""
            Задание:\s
            Модуль 12. Базы данных и Git. Задание №2:\s
            Цель задания: знакомство и формирование базовых навыков с по работе  с SQLite\s
                Задание:
                1. Что такое SQLite?
                2. Напишите запрос для отображения имен (first_name, last_name), используя псевдонимы «Имя»,
                   «Фамилия».  (Пример таблицы  см. таблица 1)
                3. Напишите запрос и получите все имена из таблицы сотрудников в верхнем регистре (Пример таблицы
                   см. таблица 1)
                4. Напишите запрос и получите все id сотрудников (Пример таблицы  см. таблица 1)
                5. Напишите запрос, чтобы получить первые 3 символа имени из таблицы сотрудников
                   (Пример таблицы  см. таблица 1)
                6. Напишите запрос, чтобы выбрать первые 5 записей из таблицы.
                   employee_id  first_name  last_name
                   100  Steven  King
                   101  Neena   Kochhar
            \s""");

        System.out.println("""
            Ответ на вопрос 1, Что такое SQLite?:
                Docker - это программное обеспечение, которое позволяет вам упаковывать, доставлять и запускать
            
            Решение по заданию 2, Напишите запрос для отображения имен (first_name, last_name), используя псевдонимы «Имя»,
                   «Фамилия».  (Пример таблицы  см. таблица 1):
                Docker используется для упаковки, доставки и выполнения приложений в изолированных средах, называемых
            
            Решение по заданию 3, Напишите запрос и получите все имена из таблицы сотрудников в верхнем регистре (Пример таблицы
                   см. таблица 1):
                Да, помимо Docker существуют и другие инструменты для управления контейнерами и развертывания
            \s""");

        System.out.println("""
            Примеры :\s
            \s""");

        System.out.println("""
                Команды в терминале IntelliJ IDEA для работы с Docker и Docker.hub и выполнение этих команд выглядят так: \s
                ```
            \s""");

        System.out.println("Результат соединения:");
        connect();

    }

    public static void connect() {
        Connection conn = null;
        try {
            // db parametets
            String url = "jdbc:sqlite:C:\\Владислав\\IdeaProjectsDrafts\\Draft_Module12_Task2_Zadaniya1-6_part2\\src\\main\\java\\task12_2_1\\sqlite.dbase";
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}