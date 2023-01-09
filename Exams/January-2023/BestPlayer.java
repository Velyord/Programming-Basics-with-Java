/*
Условие:
    Пепи иска да напишете програма, чрез която да разбере кой е най-добрият играч от световното първенство.
    Информацията, която получавате ще бъде играч и колко гола е отбелязал. От вас се иска да отпечатате кой е играчът с
    най-много голове и дали е направил хет-трик. Хет-трик е, когато футболистът е вкарал 3 или повече гола.
    Ако футболист е вкарал 10 или повече гола, програмата трябва да спре.
Вход
    От конзолата се четат по два реда до въвеждане на команда "END":
    •	Име на играч – текст
    •	Брой вкарани голове  – цяло положително число в интервала [1 … 10000]
Изход
    На конзолата да се отпечатат 2 реда :
    •	На първия ред:
            "{име на играч} is the best player!"
    •	На втория ред :
    o	 Ако най-добрият футболист е направил хет-трик:
                "He has scored {брой голове} goals and made a hat-trick !!!"
    o	Ако най-добрият футболист НЕ е направил хет-трик:
                        "He has scored {брой голове} goals."
Примерен вход и изход
    Neymar
    2
    Ronaldo
    1
    Messi
    3
    END
    -> Messi is the best player!
    -> He has scored 3 goals and made a hat-trick !!!
        Име на играч: Neymar
        Брой голове: 2 -> той има най-много голове
        Име на следващ играч: Ronaldo
        Брой голове: 1 -> той има по-малко голове от Neymar
        Име на следващ играч: Messi
        Брой голове: 3 -> той вече има най-много голове. Той е направил и хет-трик.
        Въвежда се команда END и програмата приключва.
    Silva
    5
    Harry Kane
    10
    -> Harry Kane is the best player!
    -> He has scored 10 goals and made a hat-trick !!!
    Rooney
    1
    Junior
    2
    Paolinio
    2
    END
    -> Junior is the best player!
    -> He has scored 2 goals.
    Petrov
    2
    Drogba
    11
    -> Drogba is the best player!
    -> He has scored 11 goals and made a hat-trick !!!
    Zidane
    1
    Felipe
    2
    Johnson
    4
    END
    -> Johnson is the best player!
    -> He has scored 4 goals and made a hat-trick !!!
*/
package SoftUni.Exam;

import java.util.Scanner;
import java.util.List;

import static java.lang.System.*;

public class BestPlayer {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        setValues();
    }

    private static void setValues() {
        int maxGoals = 0;
        String bestPlayer = "";
        String input = setValue(null, null);

        while (!input.equals("END")) {
            String name = input;
            int countGoals = setValue(1, 10000);

            if (countGoals > maxGoals) {
                maxGoals = countGoals;
                bestPlayer = name;
            }

            if (countGoals >= 10)
                break;

            input = setValue(null, null);
        }
        displayResult(bestPlayer, maxGoals);
    }

    private static void displayResult(
            String bestPlayer,
            int maxGoals
    ) {
        out.printf("%s is the best player!\n", bestPlayer);

        if (maxGoals >= 3)
            out.printf("He has scored %d goals and made a hat-trick !!!", maxGoals);
        else
            out.printf("He has scored %d goals.", maxGoals);
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
