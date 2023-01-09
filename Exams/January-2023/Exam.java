/*
Условие:
    Напишете програма, която да пресмята статистика за оценки от изпит.
    В началото програмата получава броя на студентите явили се на изпита и за всеки студент неговата оценка.
    На края програмата трябва да отпечата процента студенти с оценка
    между 2.00 и 2.99, между 3.00 и 3.99, между 4.00 и 4.99, 5.00 или повече. Също така и средния успех на изпита.
Вход:
    От конзолата се четат:
    •	На първия ред – броя на студентите явили се на изпит – цяло число в интервала [1...1000]
    •	За всеки един студент на отделен ред – оценката от изпита – реално число в интервала [2.00...6.00]
Изход:
    Да се отпечатат на конзолата 5 реда, които съдържат следната информация:
    Ред 1 -	"Top students: {процент студенти с успех 5.00 или повече}%"
    Ред 2 -	"Between 4.00 and 4.99: {между 4.00 и 4.99 включително}%"
    Ред 3 -	"Between 3.00 and 3.99: {между 3.00 и 3.99 включително}%"
    Ред 4 -	"Fail: {по-малко от 3.00}%"
    Ред 5 -	"Average: {среден успех}"
    Всички числа трябва да са форматирани до втория знак след десетичната запетая.
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
        Имаме 10 студента.
        Имаме 4 групи със студенти:
        1.	Група 1 - студенти с оценка >= 5.00
        2.	Група 2 - студенти с оценка >= 4 и <= 4.99
        3.	Група 3 - студенти с оценка >= 3 и <= 3.99
        4.	Група 4 - студенти с оценка < 3
        Студент 1: 3.00 -> попада в група 3
        Студент 2: 2.99 -> попада в група 4
        Студент 3: 5.68 -> попада в група 1
        Студент 4: 3.01 -> попада в група 3
        Студент 5: 4 -> попада в група 2
        Студент 6: 4 -> попада в група 2
        Студент 7: 6.00 -> попада в група 1
        Студент 8: 4.50 -> попада в група 2
        Студент 9: 2.44 -> попада в група 4
        Студент 10: 5 -> попада в група 1

        Група 1 -> брой студенти: 3 => 3 / 10 * 100 = 30%
        Група 2 -> брой студенти: 3 => 3 / 10 * 100 = 30%
        Група 3 -> брой студенти: 2 => 2 / 10 * 100 = 20%
        Група 4 -> брой студенти: 2 => 2 / 10 * 100 = 20%
        Среден успех: (3.00 + 2.99 + 5.68 + 3.01 + 4 + 4 + 6.00 + 4.50 + 2.44 + 5) / 10 = 40.62 / 10 = 4.062
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
package SoftUni.Exam;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class Exam {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int countOfExaminedStudents = setValue(1, 1000);

        setStudentsGrades(countOfExaminedStudents);
    }

    private static void setStudentsGrades(
            int countOfExaminedStudents
    ) {
        int fiveToSix = 0;
        int fourToFive = 0;
        int threeToFour = 0;
        int twoToThree = 0;
        double sumOfGrades = 0;
        double avgOfGrades;
        double grade;

        for (int i=0; i<countOfExaminedStudents; i++) {
            grade = setValue(2.00, 6.00);

            if (grade >= 5 && grade <= 6)
                fiveToSix++;
            else if (grade >= 4 && grade < 5)
                fourToFive++;
            else if (grade >= 3 && grade < 4)
                threeToFour++;
            else
                twoToThree++;

            sumOfGrades += grade;
        }
        avgOfGrades = sumOfGrades / countOfExaminedStudents;

        double top =
                (double) fiveToSix / countOfExaminedStudents * 100;
        double between4and5 =
                (double) fourToFive / countOfExaminedStudents * 100;
        double between3and4 =
                (double) threeToFour / countOfExaminedStudents * 100;
        double fail =
                (double) twoToThree / countOfExaminedStudents * 100;

        displayResult(
                top,
                between4and5,
                between3and4,
                fail,
                avgOfGrades
        );
    }

    private static void displayResult(
            double top,
            double between4and5,
            double between3and4,
            double fail,
            double avgOfGrades
    ) {
        out.printf("Top students: %.2f%%%n", top);
        out.printf("Between 4.00 and 4.99: %.2f%%%n", between4and5);
        out.printf("Between 3.00 and 3.99: %.2f%%%n", between3and4);
        out.printf("Fail: %.2f%%%n", fail);
        out.printf("Average: %.2f", avgOfGrades);
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

                if (stringCount == 1) {
                    String[] required = { "room for one person", "apartment", "president apartment" };
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

                if (stringCount == 2) {
                    String[] required = { "positive", "negative" };
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
