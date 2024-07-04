package task10_1_1;

// С этого метода, Draft_Module10_Task1_Zadaniye1,

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
    @FilterIt(show = true)
    int field33;

    @FilterIt(show = true)
    String method11() {return "";}

    @FilterIt(show = true)
    Integer method22() {return 0;}

    @FilterIt(show = false)
    Double method33() {return 0.0;}
}

