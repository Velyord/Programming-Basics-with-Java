/*
Условие:
    Шеф на компания забелязва че все повече служители прекарват  време в сайтове, които ги разсейват.
    За да предотврати това, той въвежда изненадващи проверки на отворените табове на браузъра на служителите си.
    Според отворения сайт в таба се налагат следните глоби:
    •	"Facebook" -> 150 лв.
    •	"Instagram" -> 100 лв.
    •	"Reddit" -> 50 лв.
Вход:
    От конзолата се четат два реда:
    •	Брой отворени табове в браузъра n - цяло число в интервала [1...10]
    •	Заплата - число в интервала [500...1500]
    След това n – на брой пъти се чете име на уебсайт – текст
Изход:
    •	Ако по време на проверката заплатата стане по-малка или равна на 0 лева, на конзолата се изписва
    "You have lost your salary." и програмата приключва.
    •	В противен случай след проверката на конзолата се изписва остатъкът от заплатата (да се изпише като цяло число).
Примерен вход и изход:
    10
    750
    Facebook
    Dev.bg
    Instagram
    Facebook
    Reddit
    Facebook
    Facebook
    -> You have lost your salary.
        Има 10 отворени таба в браузъра.
        Първоначалната заплата е: 750
        За първия таб -> Facebook -> глоба 150 лв. (заплата: 750 – 150 = 600)
        За втория таб -> Dev.bg -> няма глоба
        За третия таб -> Instagram -> глоба 100 лв. (заплата: 600 – 100 = 500)
        За четвъртия таб -> Facebook -> глоба 150 лв. (заплата: 500 – 150 = 350)
        За петия таб -> Reddit -> глоба 50 лв. (заплата: 350 – 50 = 300)
        За шестия таб -> Facebook -> глоба 150 лв. (заплата: 300 – 150 = 150)
        За седмия таб -> Facebook -> глоба 150 лв. (заплата: 150 – 150 = 0)
        Заплатата е равна на 0 =>  изписва съответният изход и програмата приключва
    3
    500
    Github.com
    Stackoverflow.com
    softuni.bg
    -> 500
    3
    500
    Facebook
    Stackoverflow.com
    softuni.bg
    -> 350
 */
package SoftUni.Exer10;

import java.util.Scanner;
import java.util.List;

import static java.lang.System.*;

public class Salary {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int openedTabs = setValue(1, 10);
        double salary = setValue(500, 1500);

        salaryAfterPenalty(openedTabs, salary);
    }

    private static void salaryAfterPenalty(int openedTabs, double salary) {
        for (int i=0; i<openedTabs; i++) {
            String tab = scanner.nextLine();

            switch (tab) {
                case "Facebook":  salary -= 150; break;
                case "Instagram": salary -= 100; break;
                case "Reddit":    salary -= 100; break;
                default:                         break;
            }

            if (salary <= 0){
                out.println("You have lost your salary.");
                break;
            }
        }

        if (salary > 0)
            out.printf("%.0f", salary);
    }

    @SuppressWarnings("unchecked")
    private static <T> T setValue(T min, T max) {
        Object value;
        // out.println("Въведете :");

        if (min == null && max == null) {
            String specialCharacters = "!#$%&'()*+,/:;<=>?@[]^_`{|}0123456789";
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

            String[] required = { "" };
            List<String> requiredList = List.of(required);

            if (!requiredList.contains(value)){
                out.print("Моля въведете един от следните избори: | ");
                for (String thing : required)
                    out.print(thing + " | ");

                return setValue(null, null);
            }
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
