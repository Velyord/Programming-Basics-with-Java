/*
Условие:
    Ани отива до родния си град след много дълъг период извън страната. Прибирайки се вкъщи, тя вижда старата библиотека на баба си и си спомня за любимата си книга. Помогнете на Ани, като напишете програма, в която тя въвежда търсената от нея книга (текст). Докато Ани не намери любимата си книга или не провери всички книги в библиотеката, програмата трябва да чете всеки път на нов ред името на всяка следваща книга (текст), която тя проверява. Книгите в библиотеката са свършили щом получите текст "No More Books".
    •	Ако не открие търсената книгата да се отпечата на два реда:
    o	"The book you search is not here!"
    o	"You checked {брой} books."
    •	Ако открие книгата си се отпечатва един ред:
    o	"You checked {брой} books and found it."
Примерен вход и изход:
    Troy
    Stronger
    Life Style
    Troy
    -> You checked 2 books and found it.
        Книгата, която Ани търси, в случая е Troy. Първата е Stronger, втората е Life Style, третата книга е търсената –
        Troy и програмата приключва.
    The Spot
    Hunger Games
    Harry Potter
    Torronto
    Spotify
    No More Books
    -> The book you search is not here!
    -> You checked 4 books.
        Книгата, която търси Ани е "The Spot". Библиотеката съдържа 4 книги. Първата е Hunger Games, втората Harry Potter,
        третата Torronto, а четвъртата Spotify. Понеже няма повече книги в библиотеката четенето на имена приключва.
        Ани не намери книгата, която търсеше.
    Bourne
    True Story
    Forever
    More Space
    The Girl
    Spaceship
    Strongest
    Profit
    Tripple
    Stella
    The Matrix
    Bourne
    -> You checked 10 books and found it.
 */
package SoftUni.Exer12;

import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;

public class OldBooks {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String searchedBook = scanner.nextLine();
        checkForBook(searchedBook);
    }

    private static void checkForBook(String searchedBook) {
        int count = 0;
        while (true) {
            String checkBook = scanner.nextLine();
            if (checkBook.equals("No More Books")) {
                out.println("The book you search is not here!");
                out.printf("You checked %d books.", count);
                break;
            }
            if (checkBook.equals(searchedBook)) {
                out.printf("You checked %d books and found it.", count);
                break;
            }
            count++;
        }
    }
}
