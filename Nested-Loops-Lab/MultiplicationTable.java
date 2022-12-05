/*
Условие:
    Отпечатайте на конзолата таблицата за умножение за числата от
    1 до 10 във формат:
    "{първи множител} * {втори множител} = {резултат}".
Примерен вход и изход:
    (няма вход)	->
    1 * 1 = 1
    1 * 2 = 2
    1 * 3 = 3
    1 * 4 = 4
    1 * 5 = 5
    1 * 6 = 6
    1 * 7 = 7
    1 * 8 = 8
    1 * 9 = 9
    1 * 10 = 10
    ...
    10 * 1 = 10
    10 * 2 = 20
    10 * 3 = 30
    10 * 4 = 40
    10 * 5 = 50
    10 * 6 = 60
    10 * 7 = 70
    10 * 8 = 80
    10 * 9 = 90
    10 * 10 = 100
 */
package SoftUni.Lab13;

import static java.lang.System.out;

public class MultiplicationTable {
    public static void main(String[] args) {
        for (int firstNum=1; firstNum<=10; firstNum++)
            for (int secondNum=1; secondNum<=10; secondNum++)
                out.printf("%d * %d = %d\n", firstNum, secondNum, firstNum * secondNum);
    }
}
