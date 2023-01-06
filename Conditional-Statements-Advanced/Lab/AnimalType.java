/*
Условие:
    Напишете програма, която отпечатва класа на животното според неговото име, въведено от потребителя.
    • dog -> mammal
    • crocodile, tortoise, snake -> reptile
    • others -> unknown
Примерен вход и изход:
    dog -> mammal
    snake -> reptile
    cat -> unknown

 */
package SoftUni.Lab7;

import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;

public class AnimalType {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String animal = setStringValue();

        checkAnimal(animal);
    }

    private static void checkAnimal(String animal) {
        switch (animal){
            case "dog":
                out.println("mammal"); break;
            case "crocodile":
            case "tortoise":
            case "snake":
                out.println("reptile"); break;
            default:
                out.println("unknown");
        }
    }

    private static String setStringValue() {
        String specialCharacters = "!#$%&'()*+,./:;<=>?@[]^_`{|}0123456789";
        boolean isSpecChar = false;
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
