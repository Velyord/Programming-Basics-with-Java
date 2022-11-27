/*
Условие:
    Студент трябва да отиде на изпит в определен час. Той идва в изпитната зала в даден час на пристигане.
    Счита се, че студентът е дошъл навреме, ако е пристигнал в часа на изпита или до половин час преди това.
    Ако е пристигнал по-рано повече от 30 минути, той е подранил. Ако е дошъл след часа на изпита, той е закъснял.
    Напишете програма, която прочита време на изпит и време на пристигане и отпечатва дали студентът е дошъл навреме,
    дали е подранил или е закъснял и с колко часа или минути е подранил или закъснял.
Вход:
    От конзолата се четат 4 цели числа (по едно на ред), въведени от потребителя:
    •	Първият ред съдържа час на изпита – цяло число от 0 до 23.
    •	Вторият ред съдържа минута на изпита – цяло число от 0 до 59.
    •	Третият ред съдържа час на пристигане – цяло число от 0 до 23.
    •	Четвъртият ред съдържа минута на пристигане – цяло число от 0 до 59.
Изход:
    На първият ред отпечатайте:
    •	“Late”, ако студентът пристига по-късно от часа на изпита.
    •	“On time”, ако студентът пристига точно в часа на изпита или до 30 минути по-рано.
    •	“Early”, ако студентът пристига повече от 30 минути преди часа на изпита.
    Ако студентът пристига с поне минута разлика от часа на изпита, отпечатайте на следващия ред:
Примерен вход и изход:
    9
    30
    9
    50
    -> Late
    -> 20 minutes after the start
    9
    00
    10
    30
    -> Late
    -> 1:30 hours after the start
    10
    00
    10
    00
    -> On time
    9
    00
    8
    30
    -> On time
    -> 30 minutes before the start
    14
    00
    13
    55
    -> On time
    -> 5 minutes before the start
    c
    -> Early
    -> 35 minutes before the start
    16
    00
    15
    00
    -> Early
    -> 1:00 hours before the start
    11
    30
    8
    12
    -> Early
    -> 3:18 hours before the start
    11
    30
    12
    29
    -> Late
    -> 59 minutes after the start
 */
package SoftUni.Exer8;

import static java.lang.Math.abs;
import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;

public class OnTimeForTheExam {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int examHour = setValue(0, 23);
        int examMinutes = setValue(0, 59);
        int arrivalHour = setValue(0, 23);
        int arrivalMinutes = setValue(0, 59);

        checkIfOnTime(examHour, examMinutes, arrivalHour, arrivalMinutes);
    }

    private static void checkIfOnTime(int examHour, int examMinutes, int arrivalHour, int arrivalMinutes) {
        int examInMinutes = examHour * 60 + examMinutes;
        int arrivalInMinutes = arrivalHour * 60 + arrivalMinutes;

        boolean isLate = (
            arrivalInMinutes > examInMinutes
        );
        boolean isOnTime = (
            (examInMinutes >= arrivalInMinutes) &&
            ((examInMinutes - arrivalInMinutes) <= 30)
        );
        boolean isEarly = (
            (examInMinutes > arrivalInMinutes) &&
            ((examInMinutes - arrivalInMinutes) > 30)
        );

        int timeDiff = abs(arrivalInMinutes - examInMinutes);
        String timeDiffInHours;
        if (timeDiff % 60 < 10)
            timeDiffInHours = (timeDiff / 60) + ":0" + (timeDiff % 60);
        else
            timeDiffInHours = (timeDiff / 60) + ":" + (timeDiff % 60);

        if (isLate) {
            out.println("Late");
            if (timeDiff < 60)
                out.printf("%d minutes after the start", timeDiff);
            else
                out.printf("%s hours after the start", timeDiffInHours);
        } else if (isOnTime) {
            out.println("On Time");
            if (timeDiff == 0)
                out.print("");
            else if (timeDiff < 60)
                out.printf("%d minutes before the start", timeDiff);
            else
                out.printf("%s hours before the start", timeDiffInHours);
        } else if (isEarly) {
            out.println("Early");
            if (timeDiff < 60)
                out.printf("%d minutes before the start", timeDiff);
            else
                out.printf("%s hours before the start", timeDiffInHours);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> T setValue(T min, T max) {
        Object value;
        boolean isIntArgs = min instanceof Integer && max instanceof Integer;
        // out.println("Въведете :");

        try {
            if (isIntArgs)
                value = Integer.parseInt(scanner.nextLine());
            else
                value = Double.parseDouble(scanner.nextLine());
        } catch (Exception e) {
            out.println("Не сте въвели число. Пробвайте пак!");
            return setValue(min, max);
        }

        if (isIntArgs) {
            if ((int) value < (int) min || (int) value > (int) max) {
                out.printf("Моля въведете число между %s и %s!\n", min, max);
                return setValue(min, max);
            }
        } else {
            if ((double) value < (double) min || (double) value > (double) max) {
                out.printf("Моля въведете число между %s и %s!\n", min, max);
                return setValue(min, max);
            }
        }

        return (T) value;
    }
}
