/*
Условие:
    За лятната ваканция в списъка със задължителна литература на Жоро има определен брой книги. Понеже Жоро предпочита да играе с приятели навън, вашата задача е да му помогнете да изчисли колко часа на ден трябва да отделя, за да прочете необходимата литература.
Вход:
    От конзолата се четат 3 реда:
        1. Брой страници в текущата книга – цяло число в интервала [1…1000]
        2. Страници, които прочита за 1 час – цяло число в интервала [1…1000]
        3. Броят на дните, за които трябва да прочете книгата – цяло число в интервала [1…1000]
Изход:
    Да се отпечата на конзолата броят часове, които Жоро трябва да отделя за четене всеки ден.
Примерен вход и изход:
    212
    20
    2
    -> 5
        Общо време за четене на книгата: 212 страници / 20 страници за час = 10 часа общо
        Необходимите часове на ден: 10 часа / 2 дни = 5 часа на ден
    432
    15
    4
    -> 7
        Общо време за четене на книгата: 432 страници / 15 страници за час = 28 часа общо
        Необходимите часове на ден: 28 часа / 4 дни = 7 часа на ден
*/
package SoftUni.Exer4;

import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;

public class VacationBooksList {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int numOfPages = setIntValue(1, 1000, "брой страници");
        int pagesInHour = setIntValue(1, 1000, "прочетени страници за час");
        int numOfDays = setIntValue(1, 1000, "брой дни");

        double hoursPerDay = calculateHoursPerDay(numOfPages, pagesInHour, numOfDays);

        out.println(hoursPerDay);
    }

    private static double calculateHoursPerDay(int numOfPages, int pagesInHour, int numOfDays) {
        return numOfPages / pagesInHour / numOfDays;
    }

    private static int setIntValue(int min, int max, String output) {
        out.println("Въведи " + output + ":");
        int value;

        try {
            value = Integer.parseInt(scanner.nextLine());
        }
        catch (Exception e){
            out.println("Не сте въвели число. Пробвайте пак!");
            return setIntValue(min, max, output);
        }
        
        return value;
        /*
        if (value < min || value > max) {
            out.printf("Моля въведе число между %d и %d!", min, max);
            return setIntValue(min, max, output);
        }
        else
            return value;
        */
    }
}
