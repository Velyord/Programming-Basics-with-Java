/*
Условие:
    Лято е с много променливо време и Виктор има нужда от вашата помощ. Напишете програма която спрямо времето от денонощието и градусите да препоръча на Виктор какви дрехи да си облече. Вашия приятел има различни планове за всеки етап от деня, които изискват и различен външен вид, тях може да видите от таблицата.
    От конзолата се четат точно два реда:
        • Градусите - цяло число в интервала [10…42]
        • Текст, време от денонощието - с възможности - "Morning", "Afternoon", "Evening"
            Време от денонощието / градуси
                • 10 <= градуси <= 18
                • 18 < градуси <= 24
                • градуси >= 25
            Мorning
                • Outfit = Sweatshirt
                  Shoes = Sneakers
                • Outfit = Shirt
                  Shoes = Moccasins
                • Outfit = T-Shirt
                  Shoes = Sandals
            Afternoon
                • Outfit = Shirt
                  Shoes = Moccasins
                • Outfit = T-Shirt
                  Shoes = Sandals
                • Outfit = Swim Suit
                  Shoes = Barefoot
            Evening
                • Outfit = Shirt
                  Shoes = Moccasins
                • Outfit = Shirt
                  Shoes = Moccasins
                • Outfit = Shirt
                  Shoes = Moccasins
    Да се отпечата на конзолата на един ред: "It's {градуси} degrees, get your {облекло} and {обувки}."
Примерен вход и изход
    16
    Morning
    -> It's 16 degrees, get your Sweatshirt and Sneakers.
        Сутрин когато градусите са 16, Виктор си взима суичър и маратонки.
    22
    Afternoon
    -> It's 22 degrees, get your T-Shirt and Sandals.
    28
    Evening
    -> It's 28 degrees, get your Shirt and Moccasins.
 */
package SoftUni.Exer8;

import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;

public class SummerOutfit {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int degrees = setValue(10, 42, "градуси");
        String timeOfDay = setStringValue();

        displayCloths(degrees, timeOfDay);
    }

    private static void displayCloths(int degrees, String timeOfDay) {
        String outfit = "";
        String shoes = "";
        boolean error = false;
        boolean cold = (degrees >= 10 && degrees <= 18);
        boolean warm = (degrees > 18 && degrees <= 24);
        boolean hot = (degrees >= 25);

        switch (timeOfDay) {
            case "Morning":
                if (cold) {
                    outfit = "Sweatshirt";
                    shoes = "Sneakers";
                }
                else if (warm) {
                    outfit = "Shirt";
                    shoes = "Moccasins";
                }
                else if (hot) {
                    outfit = "T-Shirt";
                    shoes = "Sandals";
                }
                break;
            case "Afternoon":
                if (cold) {
                    outfit = "Shirt";
                    shoes = "Moccasins";
                }
                else if (warm) {
                    outfit = "T-Shirt";
                    shoes = "Sandals";
                }
                else if (hot) {
                    outfit = "Swim Suit";
                    shoes = "Barefoot";
                }
                break;
            case "Evening":
                if (cold) {
                    outfit = "Shirt";
                    shoes = "Moccasins";
                }
                else if (warm) {
                    outfit = "Shirt";
                    shoes = "Moccasins";
                }
                else if (hot) {
                    outfit = "Shirt";
                    shoes = "Moccasins";
                }
                break;
            default:
                error = true;
                break;
        }

        if (error)
            out.println("error");
        else
            out.printf("It's %d degrees, get your %s and %s.", degrees, outfit, shoes);
    }

    @SuppressWarnings("unchecked")
    private static <T> T setValue(T min, T max, String thing) {
        Object value;
        boolean isIntArgs = min instanceof Integer && max instanceof Integer;
        // out.println("Въведете " + thing + ":");

        try {
            if (isIntArgs)
                value = Integer.parseInt(scanner.nextLine());
            else
                value = Double.parseDouble(scanner.nextLine());
        } catch (Exception e) {
            out.println("Не сте въвели число. Пробвайте пак!");
            return setValue(min, max, thing);
        }

        if (isIntArgs) {
            if ((int) value < (int) min || (int) value > (int) max) {
                out.printf("Моля въведете число между %s и %s!\n", min, max);
                return setValue(min, max, thing);
            }
        } else {
            if ((double) value < (double) min || (double) value > (double) max) {
                out.printf("Моля въведете число между %s и %s!\n", min, max);
                return setValue(min, max, thing);
            }
        }

        return (T) value;
    }

    private static String setStringValue() {
        String specialCharacters = "!#$%&'()*+,./:;<=>?@[]^_`{|}0123456789";
        boolean isSpecChar = false;
        // out.println("Въведете един от вариантите [Morning, Afternoon, Evening]:");
        String value = scanner.nextLine();

        for (int i = 0; i < value.length(); i++)
            if (specialCharacters.contains(Character.toString(value.charAt(i)))) {
                isSpecChar = true;
                break;
            }

        if (isSpecChar) {
            out.println("Моля въведете правилно наименование!");
            return setStringValue();
        } else
            return value;
    }
}
