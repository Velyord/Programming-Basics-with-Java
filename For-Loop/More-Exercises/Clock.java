/*
Условие:
    Напишете програма, която отпечатва часовете в денонощието от 0:0 до 23:59, 
    всеки на отделен ред. Часовете трябва да се изписват във формат "{час} : {минути}". 
Примерен вход и изход:
    (няма вход) ->
    0 : 0
    0 : 1
    0 : 2
    0 : 3
    0 : 4
    0 : 5
    0 : 6
    0 : 7
    0 : 8
    0 : 9
    0 : 10
    ...
    23 : 50
    23 : 51
    23 : 52
    23 : 53
    23 : 54
    23 : 55
    23 : 56
    23 : 57
    23 : 58
    23 : 59
*/
package SoftUni.MoreExercises.ForLoop;

import static java.lang.System.out;

public class Clock {

    public static void main(String[] args) {
        for (int hours = 0; hours <= 23; hours++) 
            for (int minutes = 0; minutes <= 59; minutes++) 
                out.printf("%d : %d\n", hours, minutes);
    }
}
