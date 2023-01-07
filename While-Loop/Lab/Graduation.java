/*
Условие:
    Напишете програма, която изчислява средната оценка на ученик от цялото му обучение.
    На първия ред ще получите името на ученика, а на всеки следващ ред неговите годишни оценки.
    Ученикът преминава в следващия клас, ако годишната му оценка е по-голяма или равна на 4.00.
    Ако ученикът бъде скъсан повече от един път, то той бива изключен и програмата приключва,
    като се отпечатва името на ученика и в кой клас бива изключен.
При успешно завършване на 12-ти клас да се отпечата:
    "{име на ученика} graduated. Average grade: {средната оценка от цялото обучение}"
    В случай, че ученикът е изключен от училище, да се отпечата:
    "{име на ученика} has been excluded at {класа, в който е бил изключен} grade"
    Стойността трябва да бъде форматирана до втория знак след десетичната запетая.
Примерен вход и изход:
    Gosho
    5
    5.5
    6
    5.43
    5.5
    6
    5.55
    5
    6
    6
    5.43
    5
    -> Gosho graduated. Average grade: 5.53
    Mimi
    5
    6
    5
    6
    5
    6
    6
    2
    3
    -> Mimi has been excluded at 8 grade
 */
package SoftUni.Lab11;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;

public class Graduation {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String studentName = setValue(null, null);

        checkIfGraduated(studentName);
    }

    private static void checkIfGraduated(String studentName) {
        int grade = 1;
        double gradeSum = 0;
        int strike = 0;

        while (true) {
            double yearlyGrade = setValue(2.00, 6.00);

            if (yearlyGrade >= 4.0) {
                gradeSum += yearlyGrade;

                if (grade >= 12) {
                    double averageGradeForStuden = gradeSum / 12;
                    System.out.printf("%s graduated. Average grade: %.2f", studentName, averageGradeForStuden);
                    break;
                }
                grade++;
            } else {
                strike++;
                if (strike >= 2) {
                    System.out.printf("%s has been excluded at %d grade", studentName, grade);
                    break;
                }
            }
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
            /*
            String[] required = { "" };
            List<String> requiredList = List.of(required);

            if (!requiredList.contains(value)){
                out.print("Моля въведете един от следните избори: | ");
                for (String thing : required)
                    out.print(thing + " | ");

                return setValue(null, null);
            } */
        } else {
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
                    if ((int) min == 0 && (int) max == Integer.MAX_VALUE)
                        out.println("Моля въведете положително число:");
                    else
                        out.printf("Моля въведете число между %s и %s:\n", min, max);

                    return setValue(min, max);
                }
            }
            if (max instanceof Double) {
                if ((double) value < (double) min || (double) value > (double) max) {
                    if ((double) min == 0 && (double) max == Double.MAX_VALUE)
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
