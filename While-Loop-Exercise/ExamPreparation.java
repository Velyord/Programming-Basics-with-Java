/*
Условие:
    Напишете програма, в която Марин решава задачи от изпити, докато не получи съобщение "Enough" от лектора си.
    При всяка решена задача, той получава оценка. Програмата трябва да приключи прочитането на данни при команда
    "Enough" или ако Марин получи определеният брой незадоволителни оценки.
    Незадоволителна е всяка оценка, която е по-малка или равна на 4.
Вход:
    •	На първи ред - брой незадоволителни оценки - цяло число в интервала [1…5]
    •	След това многократно се четат по два реда:
    o	Име на задача - текст
    o	Оценка - цяло число в интервала [2…6]
Изход:
    •	Ако Марин стигне до командата "Enough", отпечатайте на 3 реда:
    o	"Average score: {средна оценка}"
    o	"Number of problems: {броя на всички задачи}"
    o	"Last problem: {името на последната задача}"
    •	Ако получи определения брой незадоволителни оценки:
    o	"You need a break, {брой незадоволителни оценки} poor grades."
    Средната оценка да бъде форматирана до втория знак след десетичната запетая.
Примерен вход и изход:
    3
    Money
    6
    Story
    4
    Spring Time
    5
    Bus
    6
    Enough
    -> Average score: 5.25
    -> Number of problems: 4
    -> Last problem: Bus
        Броя на позволени незадоволителни оценки е 3. Първата задача се казва Money, оценката на Марин е 6.
        Втората задача е Story, оценката на Марин е 4.
        Третата задача е Spring Time, оценката на Марин е 5.
        Четвъртата задача е Bus, оценката на Марин е 6.
        Следващата команда е Enough, програмата приключва.
        Средна оценка: 21 / 4 = 5.25
        Брой решени задачи: 4
        Последна задача: Bus
    2
    Income
    3
    Game Info
    6
    Best Player
    4
    -> You need a break, 2 poor grades.
        Броят незадоволителни оценки е 2.
        Първата задача е Income, оценката на Марин е 3.
        Втората задача е Game Info, оценката на Марин е 6.
        Третата задача е Best Player, оценката на Марин е 4.
        Марин достигна допустимия брой незадоволителни оценки, време е за почивка.
 */
package SoftUni.Exer12;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;

public class ExamPreparation {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int grades = setValue(1, 5);

        checkScore(grades);
    }

    private static void checkScore(int grades) {
        int sumScore = 0;
        int problemsCount = 0;
        int badGrades = 0;
        String lastProblem = "";

        while (true) {
            String problem = scanner.nextLine();
            
            if (problem.equals("Enough")) {
                displayResult(sumScore, problemsCount, lastProblem);
                break;
            } else {
                lastProblem = problem;
                int score = setValue(2, 6);
                
                if (score <= 4)
                    badGrades++;
                
                if (badGrades == grades) {
                    out.printf("You need a break, %d poor grades.", badGrades);
                    break;
                }
                
                sumScore += score;
                problemsCount++;
            }
        }
    }

    private static void displayResult(int sumScore, int problemsCount, String lastProblem) {
        double averageScore = (double) sumScore / problemsCount;
        out.printf("Average score: %.2f\n", averageScore);
        out.printf("Number of problems: %d\n", problemsCount);
        out.printf("Last problem: %s", lastProblem);
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
                    out.println("Грешка! Не сте въвели правилен тип. Възможности [int, double]");
                    value = null;
                    exit(1);
                }
            } catch (Exception e) {
                out.println("Не сте въвели число. Пробвайте пак!");
                return setValue(min, max);
            }

            if (max instanceof Integer) {
                if ((int) value < (int) min || (int) value > (int) max) {
                    out.println("Моля въведете положително число между");
                    return setValue(min, max);
                }
            }
            if (max instanceof Double) {
                if ((double) value < (double) min || (double) value > (double) max) {
                    out.println("Моля въведете положително число между");
                    return setValue(min, max);
                }
            }
        }
        return (T) value;
    }
}
