package task11_5_1;

import java.io.*;
import java.util.Locale;

// В этом фрагменте, Draft_Module11_Task5_Zadaniya1-3_part3, всё работает до видео мин 1448

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
                Ответ на вопрос 1, Что такое сериализация в контексте протокола HTTP?:
                  Сериализация в контексте протокола HTTP означает процесс преобразования объекта в поток байтов,
                  который может быть передан по сети. В Java это обычно используется для отправки объектов между
                  клиентским и серверным приложениями посредством HTTP. Например, объект Java может быть
                  сериализован в JSON формат для передачи по сети, а затем десериализован обратно в объект на
                  другом конце соединения. Это удобный способ обмена данными между приложениями, работающими
                  на разных устройствах или платформах.
                
                Ответ на вопрос 2, Что такое десериализация в контексте  протокола HTTP?:
                  Десериализация в контексте протокола HTTP - это процесс преобразования потока байтов, полученных
                  по сети, обратно в объект в языке программирования Java. В случае HTTP, данные, отправленные
                  по сети, могут быть сериализованы на одном конце соединения (например, в JSON формате),
                  переданы по сети, а затем десериализованы на другом конце соединения обратно в объект Java.
                  Десериализация позволяет приложению получать данные по сети, преобразовывать их обратно в объекты
                  и работать с этими данными в своем приложении.
                
                Ответ на вопрос 3, За что отвечает HttpMessageConverter?:
                  HttpMessageConverter в Java отвечает за преобразование HTTP сообщений в объекты Java и обратно.
                  Он предназначен для работы с телом запроса и ответа, преобразуя данные из формата, используемого
                  для передачи по сети (например, JSON, XML, HTML), в объекты Java, которые можно использовать в
                  приложении, и наоборот. HttpMessageConverter позволяет Spring Framework автоматически
                  преобразовывать данные, переданные по HTTP, в нужный формат без необходимости вручную выполнять
                  процесс сериализации и десериализации. Этот механизм очень удобен при разработке веб-приложений
                  на Java.
                
                Примеры использования:\s""");

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/main/java/task11_5_1/data.dat"));
        final Person person = new Person("Qwerty", 20, 170);
        out.writeObject(person);

        ObjectInputStream input = new ObjectInputStream(new FileInputStream("src/main/java/task11_5_1/data.dat"));
        Person inPerson = (Person) input.readObject();
        System.out.println(inPerson);



        // Способ 2:
        ObjectOutputStream out2 = new ObjectOutputStream(new FileOutputStream("src/main/java/task11_5_1/data2.dat"));
        final Person2 person2 = new Person2("Qwerty2", 32, 182);
        final AuthData authData = new AuthData("StrongPassword", person2);
        out2.writeObject(authData);

        ObjectInputStream input2 = new ObjectInputStream(new FileInputStream("src/main/java/task11_5_1/data2.dat"));
        AuthData data = (AuthData) input2.readObject();
        System.out.println(data);



        // Способ 3:
        ObjectOutputStream out3 = new ObjectOutputStream(new FileOutputStream("src/main/java/task11_5_1/data3.dat"));
        final Person3 person3 = new Person3("Qwerty3", 42, 192);
        final AuthData3 authData3 = new AuthData3("StrongPassword", person3);
        out3.writeObject(authData3);

//        ObjectInputStream input3 = new ObjectInputStream(new FileInputStream("src/main/java/task11_5_1/data3.dat"));
//        AuthData3 data3 = (AuthData3) input3.readObject();
//        System.out.println(data3);

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



class AuthData implements Serializable{
    static Person2 person2;
    final String password;

    AuthData(String password, Person2 person2) {
        this.password = password;
        this.person2 = person2;
    }
}

class Person2 /*implements Serializable*/{
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



class AuthData3 implements Externalizable{
    static Person3 person3;
    String password3;

    AuthData3(String password3, Person3 person3) {
        this.password3 = password3;
        AuthData3.person3 = person3;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(person3);
        out.writeChars(password3.toUpperCase(Locale.ROOT));
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
//        person3 = (Person3) in.readObject();
//        password3 = in.readLine();
    }
}

class Person3 implements Serializable{
    final String name;
    final int age;
    final int height;

    Person3(String name, int age, int height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    @Override
    public String toString() {
        return "Person3{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }
}
