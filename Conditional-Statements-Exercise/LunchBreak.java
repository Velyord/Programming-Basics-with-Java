/*
Условие:
    По време на обедната почивка искате да изгледате епизод от своя любим сериал.
    Вашата задача е да напишете програма, с която ще разберете дали имате достатъчно време
    да изгледате епизода. По време на почивката отделяте време за обяд и време за отдих.
    Времето за обяд ще бъде 1/8 от времето за почивка, а времето за отдих ще бъде
    1/4 от времето за почивка.
Вход
    От конзолата се четат 3 реда:
        1. Име на сериал – текст
        2. Продължителност на епизод  – цяло число в диапазона [10… 90]
        3. Продължителност на почивката  – цяло число в диапазона [10… 120]
Изход
    На конзолата да се изпише един ред:
        • Ако времето е достатъчно да изгледате епизода:
    "You have enough time to watch {име на сериал} and left with {останало време} minutes free time."
        • Ако времето не Ви е достатъчно:
    "You don't have enough time to watch {име на сериал}, you need {нужно време} more minutes."
    Времето да се закръгли до най-близкото цяло число нагоре.
Примерен вход и изход
    Game of Thrones
    60
    96
    -> You have enough time to watch Game of Thrones and left with 0 minutes free time.
        Време за обяд : 96 * 1/8 = 12.0
        Време за отдих : 96 * 1/4 = 24.0
        Останало време : 96 - 12 - 24 = 60
        Останалото време е по-голямо или равно на продължителността на епизода, следователно печатаме подходящия изход.
    Teen Wolf
    48
    60
    -> You don't have enough time to watch Teen Wolf, you need 11 more minutes.
        Време за обяд : 60 * 1/8 = 7.5
        Време за отдих : 60 * 1/4 = 15.0
        Останало време : 60 – 7.5 - 15 = 37.5
        Останалото време е по-малко от продължителността на епизода, следователно печатаме подходящия изход.
 */
package SoftUni.Exer6;

import static java.lang.Math.*;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;

public class LunchBreak {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String movieName = setStringValue();
        int movie = setValue(10, 90);
        int time = setValue(10, 120);

        calculateIfTimeIsEnough(movieName, movie, time);
    }

    private static void calculateIfTimeIsEnough(String movieName, int movie, int time) {
        double lunch = time / 8.0;
        double relax = time / 4.0;

        double timeLeftForMovie = time - lunch - relax;
        double timeDifference = movie - timeLeftForMovie;

        if (movie > timeLeftForMovie)
            out.printf("You don't have enough time to watch %s, you need %.0f more minutes.", movieName, ceil(abs(timeDifference)));
        else
            out.printf("You have enough time to watch %s and left with %.0f minutes free time.", movieName, ceil(abs(timeDifference)));
    }

    @SuppressWarnings("unchecked")
    private static <T> T setValue(T min, T max) {
        boolean isIntArgs = min instanceof Integer && max instanceof Integer;
        Object value;

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
                out.printf("Моля въведе число между %s и %s!\n", min, max);
                return setValue(min, max);
            }
        } else {
            if ((double) value < (double) min || (double) value > (double) max) {
                out.printf("Моля въведе число между %s и %s!\n", min, max);
                return setValue(min, max);
            }
        }

        return (T) value;
    }

    private static String setStringValue() {
        String specialCharacters = "!#$%&'()*+,./:;<=>?@[]^_`{|}";
        boolean isSpecChar = false;
        String value = scanner.nextLine();

        for (int i = 0; i < value.length(); i++)
            if (specialCharacters.contains(Character.toString(value.charAt(i)))) {
                isSpecChar = true;
                break;
            }

        if (isSpecChar) {
            out.println("Моля въведете правилно име!");
            return setStringValue();
        } else
            return value;
    }
}
