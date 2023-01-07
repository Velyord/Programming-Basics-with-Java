/*
Условие:
    Напишете програма, която отпечатва числата от 1 до 100, всяко на нов ред.
Примерен вход и изход:
    (няма) ->
    1
    2
    3
    …
    98
    99
    100
 */
package SoftUni.Lab9;

import static java.lang.System.out;

public class NumbersFrom1To100 {
    public static void main(String[] args) {
        for(int i=1; i<=100; i++)
            out.println(i);
    }
}
