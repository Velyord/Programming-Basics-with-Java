/*
Условие:
    Младоженците искат да направят списък
    кой на кое място ще седи на сватбената церемония.
    Местата са разделени на различни сектори.
    Секторите са главните латински букви като започват от A.
    Във всеки сектор има определен брой редове.
    От конзолата се чете броят на редовете в първия сектор (A),
    като във всеки следващ сектор редовете се увеличават с 1.
    На всеки ред има определен брой места -
    тяхната номерация е представена с малките латински букви.
    Броя на местата на нечетните редове се прочита от конзолата,
    а на четните редове местата са с 2 повече.
Вход:
    От конзолата се четaт 3 реда:
    •	Последния сектор от секторите - символ (B-Z)
    •	Броят на редовете в първия сектор - цяло число (1-100)
    •	Броят на местата на нечетен ред - цяло число (1-24)
Изход:
    Да се отпечата на конзолата всяко място на отделен ред по
    следния формат:
    {сектор}{ред}{място}
    Накрая трябва да отпечата броя на всички места.
Примерен вход и изход:
    B
    3
    2
    ->
    A1a
    A1b
    A2a
    A2b
    A2c
    A2d
    A3a
    A3b
    B1a
    B1b
    B2a
    B2b
    B2c
    B2d
    B3a
    B3b
    B4a
    B4b
    B4c
    B4d
    20
        Първият въведен символ е ‘В’, който представлява
        означението на последният сектор, който ще има в залата.
        На вторият ред получаваме броя на редовете в първия
        сектор (A) - 3.
        Накрая получаваме броя на местата
        в нечетните редове - 2.
        Първият принтиран символ е сектора, в случая A или B;
        Вторият символ представлява реда. В сектор ‘А’ има общо 3 реда.
        Местата на нечетен ред са 2 и ще бъдат представени с
        буквите a и b, а на четен са с 2 повече = 4 - a, b, c, d.
        Генерираните места за сектор А са:
        A1a - нечетен ред - имаме две места  първо място - а
        A1b -  второ място - b
        A2a -  четен ред - имаме общо 4 места, първо място - а
        A2b -  второ място - b
        A2c -  трето място - c
        A2d -  четвърто място - d
        A3a  - нечетен ред - имаме две места, първо място - а
        A3b -  второ място - b
        Местата за сектор B се генерират по същия начин.
        По условие във всеки следващ сектор
        имаме с 1 ред повече. Тоест в сектор ‘B’ ще имаме 4 реда,
        вместо 3.
        Печатаме всички места за сектор B.
        Накрая печатаме броя на местата - в случая 20.
    C
    4
    2
    ->
    A1a
    A1b
    A2a
    A2b
    A2c
    A2d
    A3a
    A3b
    A4a
    A4b
    A4c
    A4d
    B1a
    B1b
    B2a
    B2b
    B2c
    B2d
    B3a
    B3b
    B4a
    B4b
    B4c
    B4d
    B5a
    B5b
    C1a
    C1b
    C2a
    C2b
    C2c
    C2d
    C3a
    C3b
    C4a
    C4b
    C4c
    C4d
    C5a
    C5b
    C6a
    C6b
    C6c
    C6d
    44
*/
package SoftUni.MoreExercises.NestedLoops;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class WeddingSeats {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String sector;
        sector = setValue(null, null);
        sector = sector.toUpperCase();
        char sectorChar = sector.charAt(0);
        int rowsInFirstSector = setValue(1, 100);
        int seatsInOddRow = setValue(1, 24);
        int count = 0;

        for (int i = 'A'; i <= sectorChar; i++) {
            for (int j = 1; j <= rowsInFirstSector; j++) {
                if (j % 2 != 0)
                    for (int k = 'a'; k < 'a' + seatsInOddRow; k++) {
                        out.println("" + (char) i + j + (char) k);
                        count++;
                    }
                else
                    for (int k = 'a'; k < 'a' + seatsInOddRow + 2; k++) {
                        out.println("" + (char) i + j + (char) k);
                        count++;
                    }
            }
            rowsInFirstSector++;
        }
        out.println(count);
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

            if (isSpecChar || ((String) value).length() > 1) {
                out.println("Моля въведете буква от B до Z!");
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
