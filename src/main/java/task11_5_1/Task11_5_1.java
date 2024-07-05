package task11_5_1;

// В этом фрагменте, Draft_Module11_Task5_Zadaniya1-3,

import java.io.*;

public class Task11_5_1 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("""
                Задание:\s
                Модуль 11. Протокол “HTTP”. Задание №5:\s
                Цель задания: сформировать базовое представление о сериализации и десериализации в протоколе HTTP
                    Задание:
                    1. Что такое сериализации в контексте протокола HTTP?
                    2. Что такое десериализация в контексте  протокола HTTP?
                    3. За что отвечает HttpMessageConverter?
                
                Решение:\s""");

        System.out.println("""
                Ответ на вопрос 1, Что такое сериализации в контексте протокола HTTP?:
                  В HTTP существуют различные типы запросов, используемые для взаимодействия между клиентом и сервером.
                  Некоторые из известных типов запросов в HTTP:
                  - GET - запрос для получения данных с сервера.
                
                  \s""");

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/main/java/task11_5_1/data.dat"));
        final Person person = new Person("Qwerty", 20, 170);
        out.writeObject(person);

        ObjectInputStream input = new ObjectInputStream(new FileInputStream("src/main/java/task11_5_1/data.dat"));
        Person inPerson = (Person) input.readObject();
        System.out.println(inPerson);
    }
}

class Person implements Serializable{
    final String name;
    final int age;
    final int height;

    Person(String name, int age, int height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }
}


class AuthData {
    final String password;
    final Person2 person2;

    AuthData(String password, Person2 person2) {
        this.password = password;
        this.person2 = person2;
    }
}

class Person2 {
    final String name;
    final int age;
    final int height;

    Person2(String name, int age, int height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    @Override
    public String toString() {
        return "Person2{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }
}

