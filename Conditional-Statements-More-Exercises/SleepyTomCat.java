/*
Условие:
    Котката Том обича по цял ден да спи, за негово съжаление стопанинът му си играе с него винаги
    когато  има свободно време. За да се наспи добре, нормата за игра на Том е 30 000 минути в година.
    Времето за игра на Том зависи от почивните дни на стопанина му:
    • Когато е на работа, стопанинът му си играе с него по 63 минути на ден.
    • Когато почива, стопанинът му си играе с него  по 127 минути на ден.
    Напишете програма, която въвежда броя почивни дни и отпечатва дали Том може да се наспи добре и
    колко е разликата от нормата за текущата година, като приемем че годината има 365 дни.
    Пример: 20 почивни дни -> работните дни са 345 (365 – 20 = 345). Реалното време за игра е 24 275
    минути (345 * 63 + 20 *127).  Разликата от нормата е 5 725 минути (30 000 – 24 275 = 5 725)
    или 95 часа и 25 минути.
Вход:
    Входът се чете от конзолата и се състои от едно число – броят почивни дни –
    цяло число в интервала [0...365]
Изход:
    На конзолата трябва да се отпечатат два реда.
        • Ако времето за игра на Том е над нормата за текущата година:
            ◦  На първия ред отпечатайте: “Tom will run away”
            ◦  На втория ред отпечатайте разликата от нормата във формат:
    “{H} hours and {M} minutes more for play”
        • Ако времето за игра на Том е под нормата за текущата година:
            ◦ На първия ред отпечатайте: “Tom sleeps well”
            ◦  На втория ред отпечатайте разликата от нормата във формат:
    “{H} hours and {M} minutes less for play”
Примерен вход и изход:
    20
    -> Tom sleeps well
    -> 95 hours and 25 minutes less for play
        Почични дни: 20 * 127 = 2 540 минути игра
        Работни дни: 365 - 20 = 345 * 63 = 21 735 минути игра
        30 000 > 24 274 => остават 5725 мин = 95 часа и 25 мин
    113
    -> Tom will run away
    -> 3 hours and 47 minutes more for play
        Почични дни: 113 * 127 = 14 351 минути
        Работни дни: 365 - 113 = 252 * 63 = 15 876 минути
        30 000 < 30 227 => 227 мин повече = 3 часа и 47 мин
*/
package SoftUni.MoreExercises.ConditionalStatements;

import static java.lang.Math.abs;
import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class SleepyTomCat {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int freeDays = setValue(0, 365);

        willTomRunAway(freeDays);
    }

    private static void willTomRunAway(int freeDays) {
        int minutesPlayForAFreeDay = 127;
        int minutesPlayForAWorkDay = 63;
        int workdays = 365 - freeDays;

        int gameMinutes = freeDays * minutesPlayForAFreeDay;
        gameMinutes += workdays * minutesPlayForAWorkDay;
        
        int timeDiff = abs(30000 - gameMinutes);
        int hours = timeDiff / 60;
        int minutes = timeDiff % 60; 

        if (gameMinutes <= 30000)
            out.printf("Tom sleeps well\n%d hours and %d minutes less for play", hours, minutes);
        else
            out.printf("Tom will run away\n%d hours and %d minutes more for play", hours, minutes);
    }

    @SuppressWarnings("unchecked")
    private static <T> T setValue(T min, T max) {
        Object value;
        // out.println("Въведете :");

        if (min == null && max == null) {
            String specialCharacters = "!#$%&'()*+,./:;<=>?@[]^_`{|}0123456789";
            boolean isSpecChar = false;
            value = scanner.nextLine();

            for (int i = 0; i < ((String) value).length(); i++)
                if (specialCharacters.contains(Character.toString(((String) value).charAt(i)))) {
                    isSpecChar = true;
                    break;
                }

            if (isSpecChar) {
                out.println("Моля въведете правилно наименование!");
                return setValue(null, null);
            }
            /*
            String[] required = { "" };
            List<String> requiredList = List.of(required);
            if (!requiredList.contains(value)){
                out.print("Моля въведете един от следните избори: | ");
                for (String thing : required)
                    out.print(thing + " | ");
                out.println();
                return setValue(null, null);
            } */
        }
        else {
            try {
                if (max instanceof Integer)
                    value = Integer.parseInt(scanner.nextLine());
                else if (max instanceof Double)
                    value = Double.parseDouble(scanner.nextLine());
                else {
                    out.println("Грешка!");
                    value = null;
                    exit(1);
                }
            } catch (Exception e) {
                out.println("Не сте въвели число. Пробвайте пак!");
                return setValue(min, max);
            }

            if (max instanceof Integer) {
                if ((int) value < (int) min || (int) value > (int) max) {
                    if ((int) min == 0 && (int) max == biggestInt)
                        out.println("Моля въведете положително число:");
                    else
                        out.printf("Моля въведете число между %s и %s:\n", min, max);

                    return setValue(min, max);
                }
            }
            if (max instanceof Double) {
                if ((double) value < (double) min || (double) value > (double) max) {
                    if ((double) min == 0 && (double) max == biggestDouble)
                        out.println("Моля въведете положително число:");
                    else
                        out.printf("Моля въведете число между %s и %s:\n", min, max);

                    return setValue(min, max);
                }
            }
        }
        return (T) value;
    }
}
