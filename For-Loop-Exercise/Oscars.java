/*
Условие:
    Поканени сте от академията да напишете софтуер, който да
    пресмята точките за актьор/актриса. Академията ще ви даде
    първоначални точки за актьора. След това всеки оценяващ ще
    дава своята оценка. Точките, които актьора получава се
    формират от: дължината на името на оценяващия умножено по
    точките, които дава делено на две.
    Ако резултатът в някой момент надхвърли 1250.5 програмата
    трябва да прекъсне и да се отпечата, че дадения актьор е
    получил номинация.
Вход
    •	Име на актьора - текст
    •	Точки от академията - реално число
        в интервала [2.0... 450.5]
    •	Брой оценяващи n - цяло число в интервала[1… 20]
    На следващите n-на брой реда:
    o	Име на оценяващия - текст
    o	Точки от оценяващия - реално число
        в интервала [1.0... 50.0]
Изход:
    Да се отпечата на конзолата един ред:
    •	Ако точките са над 1250.5:
    "Congratulations, {име на актьора} got a nominee for
    leading role with {точки}!"
    •	Ако точките не са достатъчни:
	"Sorry, {име на актьора} you need {нужни точки} more!"
    Резултатът да се форматирана до първата цифра след
    десетичния знак!
Примерен вход и изход:
    Zahari Baharov
    205
    4
    Johnny Depp
    45
    Will Smith
    29
    Jet Lee
    10
    Matthew Mcconaughey
    39
    -> Sorry, Zahari Baharov you need 247.5 more!
        Zahari Baharov започва с 205 точки, като 4 човека ще го оценяват.
        Първи е Johnny Depp
        => 205 + ((11 * 45) / 2) = 452.5
        Втори е Will Smith
        => 452.5 + ((10 * 29) / 2) = 597.5
        Трети е Jet Lee
        => 597.5 + ((7 *10) / 2) = 632.5
        Четвърти е Matthew Mcconaughey
        => 632.5 + ((19 * 39) / 2) = 1003.0
        1003.0 < 1250.5
        => Toчките не са достатъчни.
        Нужни точки: 1250.5 - 1003.0 = 247.5
    Sandra Bullock
    340
    5
    Robert De Niro
    50
    Julia Roberts
    40.5
    Daniel Day-Lewis
    39.4
    Nicolas Cage
    29.9
    Stoyanka Mutafova
    33
    -> Congratulations, Sandra Bullock got a nominee for leading
    role with 1268.5!
 */
package SoftUni.Exer10;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class Oscars {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String actorName = scanner.nextLine();
        double academyPoints = setValue(2.0, 450.5);
        int numOfAppraisers = setValue(1, 20);

        whoGotANominee(actorName, academyPoints, numOfAppraisers);
    }

    private static void whoGotANominee(String actorName, double academyPoints, int numOfAppraisers) {
        double allPoints = academyPoints;
        double nomination = 1250.5;

        for (int i=0; i<numOfAppraisers; i++) {
            String appraiserName = scanner.nextLine();
            double appraiserPoints = setValue(1.0, 50.0);

            allPoints += (appraiserName.length() * appraiserPoints) / 2;

            if (allPoints >= nomination) {
                out.printf("Congratulations, %s got a nominee for leading role with %.1f!", actorName, allPoints);
                break;
            }
        }

        if (allPoints < nomination)
            out.printf("Sorry, %s you need %.1f more!", actorName, nomination - allPoints);
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
                    if ((int) min == 0 && (int) max == Double.MAX_VALUE)
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
