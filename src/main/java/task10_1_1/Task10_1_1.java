package task10_1_1;

// этот фрагмент, Draft_Module10_Task1_Zadaniye1_part3, записывается следуя за видео 09

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Command {
    String name();
    boolean showInHelp();
}

class Commands {
    @Command(name = "hello", showInHelp = true)
    public void hello() {
        System.out.println("Hello, human");
    }

    @Command(name = "bye", showInHelp = true)
    public void bye() {
        System.out.println("Bye, human");
    }

    @Command(name = "stop", showInHelp = true)
        public void stop() {
            System.out.println("I'm stopped");
        }
    @Deprecated
    public void qwerty () {
    }
}

public class Task10_1_1 {

    public static void main(String[] args) throws Exception {
        System.out.println("""
                Задание:\s
                Модуль 10. Подходы к программированию. Задание №1:\s
                    Повторяя действия за спикером во время урока сделайте следующее:\s
                    1. Пройдите по дереву класса
                    2. Выведите методы и поля
                    3. Создайте фильтр по аннотации. Аннотация применяется к методам и полям.
                    4. В качестве дополнительного задания опишите, как вы поняли, что такое  Annotation и Reflection?\s

                Решение:\s""");

        System.out.println("... ...\n");

        Class2 class2 = new Class2();
        System.out.println("В следующем фрагменте мы проходим по дереву класса: ");
        showClassInfo(class2.getClass());


        Commands commands = new Commands();
        System.out.println("Проверка1");
        for (Method method : commands.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Command.class)) {
                Command command = method.getAnnotation(Command.class);
                if (command.showInHelp()) {
                    System.out.println(command.name());
                }
            }
        }
    }

    static void showClassInfo(Class _class) {
        System.out.println(_class.getSimpleName());
        for (Field field : _class.getDeclaredFields()) {
            if (field.isAnnotationPresent(FilterIt.class)) {
                FilterIt f = field.getAnnotation(FilterIt.class);
                if (f.show()) {
                    System.out.println(field.getName());
                }
            }
        }
        for (Method method : _class.getDeclaredMethods()) {
            if (method.isAnnotationPresent(FilterIt.class)) {
                FilterIt f = method.getAnnotation(FilterIt.class);
                if (f.show()) {
                    System.out.println(method.getName());
                }
            }
        }
        System.out.println();
        Class parentClass = _class.getSuperclass();
        if (parentClass != null) {
            showClassInfo(_class.getSuperclass());
        }
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
@interface FilterIt {
    boolean show();
}



class Class1 {
    @FilterIt(show = true)
    String field1;
    @FilterIt(show = false)
    String field2;
    @FilterIt(show = true)
    String field3;

    @FilterIt(show = true)
    void method1() {}
    @FilterIt(show = true)
    void method2() {}
    @FilterIt(show = false)
    void method3() {}
}

class Class2 extends Class1 {
    @FilterIt(show = true)
    String field11;
    @FilterIt(show = true)
    Object field22;
    @FilterIt(show = false)
    int field33;

    @FilterIt(show = true)
    String method11() {return "";}

    @FilterIt(show = true)
    Integer method22() {return 0;}

    @FilterIt(show = false)
    Double method33() {return 0.0;}
}

