/*
Условие:
    Курсът "Train the trainers" е към края си и финалното оценяване наближава.
    Вашата задача е да помогнете на журито което ще оценява презентациите,
    като напишете програма в която да изчислява средната оценка от представянето
    на всяка една презентация от даден студент, а накрая средният успех от всички тях.
    От конзолата на първият ред се прочита броят на хората в журито n - цяло число в интервала [1…20]
    След това на отделен ред се прочита името на презентацията - текст
    За всяка една презентация на нов ред се четат n - на брой оценки - реално число в интервала [2.00…6.00]
    След изчисляване на средната оценка за конкретна презентация, на конзолата се печата
    "{името на презентацията} - {средна оценка}."
    След получаване на команда "Finish" на конзолата се печата
    "Student's final assessment is {среден успех от всички презентации}." и програмата приключва.
    Всички оценки трябва да бъдат форматирани до втория знак след десетичната запетая.
Примерен вход и изход:
    2
    While-Loop
    6.00
    5.50
    For-Loop
    5.84
    5.66
    Finish
    -> While-Loop - 5.75.
    -> For-Loop - 5.75.
    -> Student's final assessment is 5.75.
        2 – броят на хората в журито следователно ще получаваме по 2 оценки на презентация.
        (6.00 + 5.50) / 2 = 5.75
        (5.84 + 5.66) / 2 = 5.75
        (6.00 + 5.50 + 5.84 + 5.66) / 4 = 5.75
    3
    Arrays
    4.53
    5.23
    5.00
    Lists
    5.83
    6.00
    5.42
    Finish
    -> Arrays - 4.92.
    -> Lists - 5.75.
    -> Student's final assessment is 5.34.
    2
    Objects and Classes
    5.77
    4.23
    Dictionaries
    4.62
    5.02
    RegEx
    2.88
    3.42
    Finish
    -> Objects and Classes - 5.00.
    -> Dictionaries" - 4.82.
    -> RegEx - 3.15.
    -> Student's final assessment is 4.32.
*/
package SoftUni.Exer14;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class TrainTheTrainers {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        calcAvgScore();
    }

    private static void calcAvgScore() {
        int numOfJudges = setValue(1, 20);
        double grades = 0;
        double presentationsCount = 0;
        String input = scanner.nextLine();

        while (!input.equals("Finish")) {
            ++presentationsCount;
            String presentationName = input;
            double presentationGrades = 0;

            for (int i=0; i<numOfJudges; i++) {
                double grade = Double.parseDouble(scanner.nextLine());
                presentationGrades += grade;
            }

            grades += presentationGrades;
            double presentationAvgGrade = presentationGrades / numOfJudges;
            out.printf("%s - %.2f.\n", presentationName, presentationAvgGrade);

            input = scanner.nextLine();
        }
        double result = grades / (presentationsCount * numOfJudges);

        out.printf("Student's final assessment is %.2f.", result);
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
