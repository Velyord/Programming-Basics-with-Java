/*
Условие:
    Напишете програма, която печата всички числа в интервала от 1 до 100, 
    който се делят на 3 без остатък, по едно на ред.
Вход и изход:
    (няма) ->
    3
    6
    9
    …
    99
*/
package SoftUni.MoreExercises.WhileLoop;

import static java.lang.System.out;

public class NumbersDividedBy3WithoutReminder {
    public static void main(String[] args) {
        for (int i=3; i<100; i+=3)
            out.println(i);
    }
}
