/*
Условие:
    Напишете програма, която отпечатва числата в диапазона от 1 до 1000, които завършват на 7.
Вход и изход:
    (няма)	
    -> 7
    -> 17
    -> 27
    -> …
    -> 997
 */
package SoftUni.Exer10;

import static java.lang.System.out;

public class NumbersEndingIn7 {
    public static void main(String[] args) {
        for (int i=7; i<=997; i+=10) 
            out.println(i);
    }
}
