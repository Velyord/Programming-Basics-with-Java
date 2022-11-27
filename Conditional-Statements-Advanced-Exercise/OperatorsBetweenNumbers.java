/*
Условие:
    Напишете програма, която чете две цели числа (N1 и N2) и оператор, с който да се извърши дадена математическа
    операция с тях. Възможните операции са: Събиране(+), Изваждане(-), Умножение(*), Деление(/) и Модулно деление(%).
    При събиране, изваждане и умножение на конзолата трябва да се отпечатат резултата и дали той е четен или нечетен.
    При обикновеното деление – резултата. При модулното деление – остатъка. Трябва да се има предвид, че делителят може
    да е равен на 0(нула), а на нула не се дели. В този случай трябва да се отпечата специално съобщениe.
Вход:
    От конзолата се прочитат 3 реда, въведени от потребителя:
    •	N1 – цяло число в интервала [0...40 000]
    •	N2 – цяло число в интервала [0...40 000]
    •	Оператор – един символ измеду: „+“, „-“, „*“, „/“, „%“
Изход:
    Да се отпечата на конзолата един ред:
    •	Ако операцията е събиране, изваждене или умножение:
    o	 „{N1} {оператор} {N2} = {резултат} – {even/odd}“
    •	Ако операцията е деление:
    o	„{N1} / {N2} = {резултат}“ – резултатът е фораматиран до вторият знак след дес.запетая
    •	Ако операцията е модулно деление:
    o	„{N1} % {N2} = {остатък}“
    •	В случай на деление с 0(нула):
    o	„Cannot divide {N1} by zero“
Примерен вход и изход:
10
12
+
-> 10 + 12 = 22 - even
123
12
/
-> 123 / 12 = 10.25
112
0
/
-> Cannot divide 112 by zero
10
1
-
-> 10 – 1 = 9 - odd
10
3
%
-> 10 % 3 = 1
10
0
%
-> Cannot divide 10 by zero
7
3
*
-> 7 * 3 = 21 - odd
 */
package SoftUni.Exer8;

import static java.lang.System.out;
import static java.lang.System.in;

import java.util.List;
import java.util.Scanner;

public class OperationsBetweenNumbers {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int num1 = setValue(0, 40000);
        int num2 = setValue(0, 40000);
        char operator = setCharValue();

        calculate(num1, operator, num2);
    }

    private static void calculate(int num1, char operator, int num2) {
        double result = 0;

        if (num2 == 0 && (operator == '/' || operator == '%'))
            out.printf("Cannot divide %d by zero", num1);
        else {
            switch (operator) {
                case '+': result = num1 + num2;          break;
                case '-': result = num1 - num2;          break;
                case '*': result = num1 * num2;          break;
                case '/': result = (double) num1 / num2; break;
                case '%': result = num1 % num2;          break;
                default:                                 break;
            }

            String evenOrOdd = (result % 2 == 0) ? "even" : "odd";
            String formattedResult = (operator == '/') ? String.format("%.2f", result) : String.format("%.0f", result);

            if (operator == '+' || operator == '-' || operator == '*')
                out.printf("%d %c %d = %s - %s", num1, operator, num2, formattedResult, evenOrOdd);
            else
                out.printf("%d %c %d = %s", num1, operator, num2, formattedResult);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> T setValue(T min, T max) {
        Object value;
        boolean isIntArgs = min instanceof Integer && max instanceof Integer;
        // out.println("Въведете :");

        try {
            if (isIntArgs)
                value = Integer.parseInt(scanner.nextLine());
            else
                value = Double.parseDouble(scanner.nextLine());
        } catch (Exception e) {
            out.println("Не сте въвели число. Пробвайте пак!");
            return setValue(min, max);
        }
/*
        if (isIntArgs) {
            if ((int) value < (int) min || (int) value > (int) max) {
                out.printf("Моля въведете число между %s и %s!\n", min, max);
                return setValue(min, max);
            }
        } else {
            if ((double) value < (double) min || (double) value > (double) max) {
                out.printf("Моля въведете число между %s и %s!\n", min, max);
                return setValue(min, max);
            }
        }
*/
        return (T) value;
    }

    private static char setCharValue() {
        // out.println("Въведете: ");
        String value = scanner.nextLine();

        String[] symbols = {"+", "-", "*", "/", "%" };
        List<String> symbolList = List.of(symbols);

        if (!symbolList.contains(value)){
            out.print("Моля въведете един от следните знаци [ ");
            for (String symbol : symbols)
                out.print(symbol + " ");
            out.println("]:");

            return setCharValue();
        }
        else
            return value.charAt(0);
    }
}
