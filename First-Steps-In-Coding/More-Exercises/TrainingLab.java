/*
Условие:
    Учебна зала има правоъгълен размер w на h метра, без колони във вътрешността си.
    Залата е разделена на две части – лява и дясна, с коридор приблизително по средата.
    В лявата и в дясната част има редици с бюра. В задната част на залата има голяма входна врата.
    В предната част на залата има катедра с подиум за преподавателя.
    Едно работно място заема 70 на 120 cm
    (маса с размер 70 на 40 cm + място за стол и преминаване с размер 70 на 80 cm).
    Коридорът е широк поне 100 cm. Изчислено е,
    че заради входната врата (която е с отвор 160 cm) се губи точно 1 работно място,
    а заради катедрата (която е с размер 160 на 120 cm) се губят точно 2 работни места.
    Напишете програма, която въвежда размери на учебната зала
    и изчислява броя работни места в нея при описаното разположение (вж. фигурата).
Вход:
    От конзолата се четат 2 числа, по едно на ред: w (дължина в метри) и h (широчина в метри).
    Ограничения: 3 ≤ h ≤ w ≤ 100.
Изход:
    Да се отпечата на конзолата едно цяло число: броят места в учебната зала.
Примерен вход и изход:
15
8.9
-> 129
    Залата е широка 890 cm. От тях 100 cm отиват за коридора в средата.
    В останалите 790 cm могат да се разположат по 11 бюра на ред (11 * 70 cm = 770 cm + 20 cm остатък).
    Залата е дълга 1500 cm. В тях могат да бъдат разположени 12 реда (12 * 120 cm = 1440 + 60 cm остатък).
    Брой места = 12 * 11 - 3 =  132 - 3 = 129
    (имаме 12 реда по 11 места = 132 минус 3 места за катедра и входна врата).
8.4
5.2
-> 39
    Залата е широка 520 cm. От тях 100 cm отиват за коридора в средата.
    В останалите 420 cm могат да се разположат по 6 бюра на ред (6 * 70 cm = 420 cm, без остатък).
    Залата е дълга 840 cm. В тях могат да бъдат разположени 7 реда (7 * 120 cm = 840, без остатък).
    Брой места = 7 * 6 - 3 =  42 - 3 = 39 (имаме 7 реда по 6 места = 42 минус 3 места за катедра и входна врата).
*/
package SoftUni.MoreExercises.FirstStepsInCoding;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class TrainingLab {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        double w = setValue(3.0, 100.0);
        double h = setValue(3.0, w);
        
        int seats = calcSeats(w,h);
        
        out.println(seats);
    }

    private static int calcSeats(double w, double h) {
        double hInCm = h * 100;
        double hWithoutHall = hInCm - 100;
        int desks = (int) (hWithoutHall / 70);
        double wInCn = w * 100;
        int rows = (int) (wInCn / 120);
        int departmentSpace = 2;
        int entranceSpace = 1;
        return desks * rows - departmentSpace - entranceSpace;
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
            } catch (Exception e) {
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
