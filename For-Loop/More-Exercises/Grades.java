/*
Условие:
    Напишете програма, която да пресмята статистика на оценки от изпит.
    В началото програмата получава броят на студентите явили се на изпита
    и за всеки студент неговата оценка.
    На края програмата трябва да изпечата процента на студенти с оценка между 2.00 и 2.99,
    между 3.00 и 3.99, между 4.00 и 4.99, 5.00 или повече. Също така и средният успех на изпита.
Вход:
    От конзолата се четат поредица от числа, всяко на отделен ред:
        • На първия ред – броя на студентите явили се на изпит – цяло число в интервала [1...1000]
        • За всеки един студент на отделен ред – оценката от изпита –
        реално число в интервала [2.00...6.00]
Изход:
    Да се отпечатат на конзолата 5 реда, които съдържат следната информация:
        Ред 1 - "Top students: {процент студенти с успех 5.00 или повече}%"
        Ред 2 - "Between 4.00 and 4.99: {между 4.00 и 4.99 включително}%"
        Ред 3 - "Between 3.00 and 3.99: {между 3.00 и 3.99 включително}%"
        Ред 4 - "Fail: {по-малко от 3.00}%"
        Ред 5 - "Average: {среден успех}"
    Всички числа трябва да са форматирани до вторият знак след десетичната запетая.
Примерен вход и изход:
    10
    3.00
    2.99
    5.68
    3.01
    4
    4
    6.00
    4.50
    2.44
    5
    -> Top students: 30.00%
    -> Between 4.00 and 4.99: 30.00%
    -> Between 3.00 and 3.99: 20.00%
    -> Fail: 20.00%
    -> Average: 4.06
        5 и повече – трима = 30% от 10
        Между 4 и 4.99 – трима = 30% от 10
        Между 3 и 3.99 – двама = 20% от 10
        Под 3 – двама = 20% от 10
        Средният успех: 3 + 2.99 + 5.68 + 3.01 + 4 + 4 + 6 + 4.50 + 2.44 + 5 = 40.62 / 10 = 4.062
    6
    2
    3
    4
    5
    6
    2.2
    -> Top students: 33.33%
    -> Between 4.00 and 4.99: 16.67%
    -> Between 3.00 and 3.99: 16.67%
    -> Fail: 33.33%
    -> Average: 3.70
*/
package SoftUni.MoreExercises.ForLoop;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class Grades {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int studentCount = setValue(1, 1000);
        insertGradesAndDisplayTiers(studentCount);
    }

    private static void insertGradesAndDisplayTiers(int studentCount) {
        int top = 0, between4and5 = 0, between3and4 = 0, fail = 0;
        double gradeSum = 0;
        for (int student = 1; student <= studentCount; student++) {
            double grade = setValue(2.00, 6.00);
            gradeSum += grade;
            if (grade >= 5)      top++;
            else if (grade >= 4) between4and5++;
            else if (grade >= 3) between3and4++;
            else                 fail++;
        }
        double avgGrade = gradeSum / studentCount;
        displayResult(studentCount, top, between4and5, between3and4, fail, avgGrade);
    }

    private static void displayResult(
        int studentCount,
        int top,
        int between4and5,
        int between3and4,
        int fail,
        double avgGrade
    ) {
        double topPercent =
                100.0 * top / studentCount;
        double between4and5Percent =
                100.0 * between4and5 / studentCount;
        double between3and4Percent =
                100.0 * between3and4 / studentCount;
        double failPercent =
                100.0 * fail / studentCount;

        out.printf("Top students: %.2f%%%n", topPercent);
        out.printf("Between 4.00 and 4.99: %.2f%%%n", between4and5Percent);
        out.printf("Between 3.00 and 3.99: %.2f%%%n", between3and4Percent);
        out.printf("Fail: %.2f%%%n", failPercent);
        out.printf("Average: %.2f", avgGrade);
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
