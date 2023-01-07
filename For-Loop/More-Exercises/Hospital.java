/*
Условие:
    За даден период от време, всеки ден в болницата пристигат пациенти за преглед.
    Тя разполага първоначално със 7 лекари.
    Всеки лекар може да преглежда само по един пациент на ден, но понякога има недостиг на лекари,
    затова останалите пациенти се изпращат в други болници. Всеки трети ден,
    болницата прави изчисления
    и ако броят на непрегледаните пациенти е по-голям от броя на прегледаните,
    се назначава още един лекар. Като назначаването става преди да започне приемът на пациенти за деня.
    Напишете програма, която изчислява за дадения период броя на прегледаните
    и непрегледаните пациенти.
Вход:
    Входът се чете от конзолата и съдържа:
        • На първия ред – периода, за който трябва да направите изчисления.
        Цяло число в интервала [1 ... 1000]
        • На следващите редове(равни на броят на дните) – броя пациенти,
        които пристигат за преглед за текущия ден. Цяло число в интервала [0…10 000]
Изход:
    Да се отпечатат на конзолата 2 реда :
        • На първия ред: "Treated patients: {брой прегледани пациенти}."
        • На втория ред: "Untreated patients: {брой непрегледани пациенти}."
Примерен вход и изход:
    4
    7
    27
    9
    1
    -> Treated patients: 23.
    -> Untreated patients: 21.
        1 ден: 7 прегледани и 0 непрегледани пациента за деня
        2 ден: 7 прегледани и 20 непрегледани пациента за деня
        3 ден: До момента прегледаните пациенти са общо 14, а непрегледаните – 20 –>
        Назначава се нов лекар –>
        8 прегледани и 1 непрегледан пациент за деня
        4 ден: 1 прегледан и 0 непрегледани пациента за деня
        Общо: 23 прегледани и 21 непрегледани пациенти.
    6
    25
    25
    25
    25
    25
    2
    -> Treated patients: 40.
    -> Untreated patients: 87.
    3
    7
    7
    7
    -> Treated patients: 21.
    -> Untreated patients: 0.
*/
package SoftUni.MoreExercises.ForLoop;

import static java.lang.Math.abs;
import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class Hospital {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int period = setValue(1, 1000);

        goThroughPeriod(period);
    }

    private static void goThroughPeriod(int period) {
        int patients, treatments;
        int doctors = 7;
        int treated = 0, untreated = 0;

        for (int i=1; i<=period; i++) {
            if (i % 3 == 0)
                if (untreated > treated)
                    doctors++;
            patients = setValue(0, 10000);
            treatments = doctors - patients;
            if (treatments < 0) {
                untreated += patients - doctors;
                treated += doctors;
            } else
                treated += patients;
        }

        displayPatients(treated, untreated);
    }

    private static void displayPatients(int treated, int untreated) {
        out.printf("Treated patients: %d.\n", treated);
        out.printf("Untreated patients: %d.", untreated);
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
