/*
Условие:
    Да се напише програма, която чете текст (стринг), въведен от потребителя,
    изчислява и отпечатва сумата от стойностите на гласните букви според таблицата по-долу:
    буква
    a
    e
    i
    o
    u
    стойност
    1
    2
    3
    4
    5
Примерен вход и изход
    hello
    -> 6
        e + o = 2 + 4 = 6
    hi
    -> 3
        i = 3
    bamboo
    -> 9
        a + o + o = 1 + 4 + 4 = 9
    beer
    -> 4
        e + e = 2 + 2 = 4
 */
package SoftUni.Lab9;

import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;

public class VowelsSum {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String text = scanner.nextLine();
        int result = 0;
        for (int i=0; i<text.length(); i++){
            switch (text.charAt(i)) {
                case 'a': result += 1; break;
                case 'e': result += 2; break;
                case 'i': result += 3; break;
                case 'o': result += 4; break;
                case 'u': result += 5; break;
            }
        }
        out.println(result);
    }
}
