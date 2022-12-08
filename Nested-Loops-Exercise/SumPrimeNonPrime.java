/*
Условие:
    Напишете програма, която чете от конзолата цели числа в диапазона, докато не се получи команда "stop".
    Да се намери сумата на всички въведени прости и сумата на всички въведени непрости числа.
    Тъй като по дефиниция от математиката отрицателните числа не могат да бъдат прости,
    ако на входа се подаде отрицателно число да се изведе следното съобщение "Number is negative.".
    В този случай въведено число се игнорира и не се прибавя към нито една от двете суми,
    а програмата продължава своето изпълнение, очаквайки въвеждане на следващо число.
    На изхода да се отпечатат на два реда двете намерени суми в следния формат:
    •	"Sum of all prime numbers is: {prime numbers sum}"
    •	"Sum of all non prime numbers is: {nonprime numbers sum}"
Примерен вход и изход:
    3
    9
    0
    7
    19
    4
    stop
    -> Sum of all prime numbers is: 29
    -> Sum of all non prime numbers is: 13
        Първото въведено число е 3. То е просто и го прибавяме съм сумата на простите числа.
        Следващото число е 9. То не е просто и го прибавяме към сумата на непростите числа.
        Числото 0 не е просто число и го прибавяме към сумата на непростите числа. Сумата става 9+0=9.
        Следващите две числа са 7 и 19. Те са прости и всяко едно от тях го прибавяме към сумата на простите числа.
        3+7=10 и 10+19=29.
        Следва числото 4, което не е просто и го прибавяме към съответната сума 9+4=13.
        Получаваме команда stop. Програмата прекъсва своето изпълнение и отпечатваме двете суми.
    30
    83
    33
    -1
    20
    stop
    -> Number is negative.
    -> Sum of all prime numbers is: 83
    -> Sum of all non prime numbers is: 83
    0
    -9
    0
    stop
    -> Number is negative.
    -> Sum of all prime numbers is: 0
    -> Sum of all non prime numbers is: 0
*/
package SoftUni.Exer14;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class SumPrimeNonPrime {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        executeProgram();
    }

    private static void executeProgram() {
        int sumPrime = 0;
        int sumNonPrime = 0;
        String input = scanner.nextLine();

        while (!input.equals("stop")) {
            boolean isPositive = true;
            boolean isPrime = true;
            int inputNumber = Integer.parseInt(input);

            if (inputNumber < 0)
                isPositive = false;

            if (isPositive) {
                if (inputNumber != 2 && inputNumber != 3) {
                    for (int i=2; i<=inputNumber/2; i++)
                        if (inputNumber % i == 0) {
                            isPrime = false;
                            break;
                        }
                }

                if (isPrime)
                    sumPrime += inputNumber;
                else
                    sumNonPrime += inputNumber;
            } else {
                out.println("Number is negative.");
            }

            input = scanner.nextLine();
        }
        displayResult(sumPrime, sumNonPrime);
    }

    private static void displayResult(int sumPrime, int sumNonPrime) {
        out.println("Sum of all prime numbers is: " + sumPrime);
        out.println("Sum of all non prime numbers is: " + sumNonPrime);

    }
}
