/*
Условие:
    Напишете програма, която да отпечатва часовете в денонощието от 0:0:0 до 23:59:59,
    всеки на отделен ред. Часовете да се изписват във формат "{час} : {минути} : {секунди}".
Примерен вход и изход:
    (няма вход) ->
    0 : 0 : 0
    0 : 0 : 1
    0 : 0 : 2
    0 : 0 : 3
    0 : 0 : 4
    0 : 0 : 5
    0 : 0 : 6
    0 : 0 : 7
    0 : 0 : 8
    0 : 0 : 9
    0 : 0 : 10
    ...
    23 : 59 : 50
    23 : 59 : 51
    23 : 59 : 52
    23 : 59 : 53
    23 : 59 : 54
    23 : 59 : 55
    23 : 59 : 56
    23 : 59 : 57
    23 : 59 : 58
    23 : 59 : 59
*/
package SoftUni.MoreExercises.ForLoop;

import static java.lang.System.out;

public class ClockPart2 {

    public static void main(String[] args) {
        for (int hours = 0; hours <= 23; hours++)
            for (int minutes = 0; minutes <= 59; minutes++)
                for (int seconds = 0; seconds <= 59; seconds++)
                    out.printf("%d : %d : %d\n", hours, minutes, seconds);
    }
}
