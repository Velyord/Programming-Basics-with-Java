/*
Услуги:
    Напишете програма, която чете текст (стринг), въведен от потребителя и печата всеки символ от текста на отделен ред.
Примерен вход и изход:
    softuni
    -> s
    -> o
    -> f
    -> t
    -> u
    -> n
    -> i
    ice cream
    -> i
    -> c
    -> e
    ->
    -> c
    -> r
    -> e
    -> a
    -> m
 */
package SoftUni.Lab9;

import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;

public class CharacterSequence {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String text = scanner.nextLine();

        for (int i=0; i<text.length(); i++)
            out.println(text.charAt(i));
    }
}
