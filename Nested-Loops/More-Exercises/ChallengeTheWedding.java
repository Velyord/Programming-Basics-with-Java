/*
Условие:
    Провокирани от сватбата си, Михаела и Иван решават да предоставят нова услуга на клиенти на
    ресторанта си, а именно вечеря за запознанства - "Предизвикай Сватбата".
    Напишете програма, която отпечатва всички възможни срещи на клиентите на ресторанта.
    При настаняване всеки мъж и всяка жена получават талончета с поредни номера стартирайки от 1.
    Ако бъдат заети всички маси, програмата трябва да приключи. Всяка маса има две места.
Вход
    От конзолата се четат точно 3 числа, всяко на отделен ред:
        • Броя клиенти мъже - цяло число в интервала [1...100]
        • Броя клиенти жени - цяло число в интервала [1...100]
        • Максималният брой маси - цяло число в интервала [1...100]
Изход
    На конзолата се принтират на един ред, разделени с интервал всички срещи в следният формат:
        • ({№ клиент} <-> {№ клиент}) ({№ клиент} <-> {№ клиент}) ...
Примерен вход и изход
    2
    2
    6
    ->
    (1 <-> 1) (1 <-> 2) (2 <-> 1) (2 <-> 2)
        Мъж 1 се среща с Жена 1, Мъж 1 се среща с Жена 2,
        Мъж 2 се среща с Жена 1, Мъж 2 се среща с Жена 2.
    2
    2
    3
    ->
    (1 <-> 1) (1 <-> 2) (2 <-> 1)
        Мъж 1 се среща с Жена 1, Мъж 1 се среща с Жена 2, Мъж 2 се среща с Жена 1.
        И трите маси за заети и програмата приключва.
    5
    8
    40
    ->
    (1 <-> 1) (1 <-> 2) (1 <-> 3) (1 <-> 4) (1 <-> 5) (1 <-> 6) (1 <-> 7) (1 <-> 8) (2 <-> 1) (2 <-> 2)
    (2 <-> 3) (2 <-> 4) (2 <-> 5) (2 <-> 6) (2 <-> 7) (2 <-> 8) (3 <-> 1) (3 <-> 2) (3 <-> 3) (3 <-> 4)
    (3 <-> 5) (3 <-> 6) (3 <-> 7) (3 <-> 8) (4 <-> 1) (4 <-> 2) (4 <-> 3) (4 <-> 4) (4 <-> 5) (4 <-> 6)
    (4 <-> 7) (4 <-> 8) (5 <-> 1) (5 <-> 2) (5 <-> 3) (5 <-> 4) (5 <-> 5) (5 <-> 6) (5 <-> 7) (5 <-> 8)
*/
package SoftUni.MoreExercises.NestedLoops;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class ChallengeTheWedding {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int gents = setValue(0, 100);
        int ladies = setValue(0, 100); // judge tests ladies with 0
        int maxTables = setValue(0, 100);
        int tablesTaken = 0;

        outer: for (int gent = 1; gent <= gents; gent++)
            for (int lady = 1; lady <= ladies; lady++) {
                if (tablesTaken >= maxTables)
                    break outer;
                else
                    tablesTaken++;
                out.print("(" + gent + " <-> " + lady + ") ");
            }
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

            if (requiredString){
                stringCount++;
                String[] required = {};

                if (stringCount == 1)
                    required = new String[] {"Spring", "Summer", "Autumn", "Winter"};
                if (stringCount == 2)
                    required = new String[] {"Y", "N"};
                if (stringCount > 2) {
                    requiredString = false;
                    return (T) value;
                }

                List<String> requiredList = List.of(required);

                if (!requiredList.contains(value)){
                    out.print("Моля въведете един от следните избори: \n| ");
                    for (String thing : required)
                        out.print(thing + " | ");
                    out.println();

                    stringCount--;
                    return setValue(null, null);
                }
            }
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
            }
            catch (Exception e) {
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
