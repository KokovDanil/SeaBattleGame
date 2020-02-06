import model.GameText;
import java.util.Scanner;

public class MainText {
    public static void main(String[] args) {
        int n = 0;
        while (n != 3) {
            System.out.println("Вы находитесь в главном меню. Выберите режим работы:");
            System.out.println("1 - посмотреть демонстрационный пример и условные обозначения");
            System.out.println("2 - сыграть в игру с умным роботом");
            System.out.println("3 - выйти из программы");
            n = Integer.parseInt((new Scanner(System.in)).nextLine());
            if (n == 1) {
                GameText game = new GameText();
                game.demoGame();
            }
            if (n == 2) {
                GameText game = new GameText();
                game.newStart();
            }
        }
    }
}
