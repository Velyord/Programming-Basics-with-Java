/*
Условие:
    Да се напише програма, която чете едно цяло число N, въведено от потребителя,
    и генерира всички възможни "специални" числа от 1111 до 9999. За да бъде "специално" едно число,
    то трябва да отговаря на следното условие:
    •	N да се дели на всяка една от неговите цифри без остатък.
    Пример: при N = 16, 2418 е специално число:
    •	16 / 2 = 8 без остатък
    •	16 / 4 = 4 без остатък
    •	16 / 1 = 16 без остатък
    •	16 / 8 = 2 без остатък
Вход:
    Входът се чете от конзолата и се състои от едно цяло число в интервала [1…600000]
Изход:
    На конзолата трябва да се отпечатат всички "специални" числа, разделени с интервал
Примерен вход и изход:
    3
    -> 1111 1113 1131 1133 1311 1313 1331 1333 3111 3113 3131 3133 3311 3313 3331 3333
        3 / 1 = 3 без остатък
        3 / 3 = 1 без остатък
        3 / 3 = 1 без остатък
        3 / 3 = 1 без остатък
    11
    -> 1111
    16
    -> 1111 1112 1114 1118 1121 1122 1124 1128 1141 1142 1144 1148 1181 1182 1184 1188 1211 1212 1214 1218 1221 1222
    1224 1228 1241 1242 1244 1248 1281 1282 1284 1288 1411 1412 1414 1418 1421 1422 1424 1428 1441 1442 1444 1448 1481
    1482 1484 1488 1811 1812 1814 1818 1821 1822 1824 1828 1841 1842 1844 1848 1881 1882 1884 1888 2111 2112 2114 2118
    2121 2122 2124 2128 2141 2142 2144 2148 2181 2182 2184 2188 2211 2212 2214 2218 2221 2222 2224 2228 2241 2242 2244
    2248 2281 2282 2284 2288 2411 2412 2414 2418 2421 2422 2424 2428 2441 2442 2444 2448 2481 2482 2484 2488 2811 2812
    2814 2818 2821 2822 2824 2828 2841 2842 2844 2848 2881 2882 2884 2888 4111 4112 4114 4118 4121 4122 4124 4128 4141
    4142 4144 4148 4181 4182 4184 4188 4211 4212 4214 4218 4221 4222 4224 4228 4241 4242 4244 4248 4281 4282 4284 4288
    4411 4412 4414 4418 4421 4422 4424 4428 4441 4442 4444 4448 4481 4482 4484 4488 4811 4812 4814 4818 4821 4822 4824
    4828 4841 4842 4844 4848 4881 4882 4884 4888 8111 8112 8114 8118 8121 8122 8124 8128 8141 8142 8144 8148 8181 8182
    8184 8188 8211 8212 8214 8218 8221 8222 8224 8228 8241 8242 8244 8248 8281 8282 8284 8288 8411 8412 8414 8418 8421
    8422 8424 8428 8441 8442 8444 8448 8481 8482 8484 8488 8811 8812 8814 8818 8821 8822 8824 8828 8841 8842 8844 8848
    8881 8882 8884 8888
*/
package SoftUni.Exer14;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Arrays;
import java.util.Scanner;
import java.util.List;

public class SpecialNumbers {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int n = setValue(1, 600000);

        generateSpecialNumbers(n);
    }

    private static void generateSpecialNumbers(int n) {
        int size = 0;
        int[] arr = {};

        for (int x = 1; x <= 9; x++)
            if (n % x == 0) {
                arr = addX(size, arr, x);
                size++;
            }

        displayVariation(arr);
    }

    private static void displayVariation(int[] arr) {
        int end = arr.length;
        
        for (int i=0; i<end; i++)
            for (int j=0; j<end; j++)
                for (int k=0; k<end; k++)
                    for (int l=0; l<end; l++)
                        out.print("" + arr[i] + arr[j] + arr[k] + arr[l] + " ");
    }

    // Function to add x in arr
    public static int[] addX(int size, int[] arr, int x) {
        int i;

        // create a new array of size n+1
        int[] newArr = new int[size + 1];

        // insert the elements from
        // the old array into the new array
        // insert all elements till n
        // then insert x at n+1
        for (i = 0; i < size; i++)
            newArr[i] = arr[i];

        newArr[size] = x;

        return newArr;
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
