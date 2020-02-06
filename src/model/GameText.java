package model;

import view.TextView;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GameText {
    private GameText game;
    Player player1 = new Player();
    Player player2 = new Player();
    TextView textView = new TextView();
    boolean player1ShotRight; // право выстрела: true - игрок 1, false - игрок 2

    public void demoGame() {
        player1.name = "man";
        player2.name = "robot";
        player1.field.placeShipsRandomly(player1.ships);
        player2.field.placeShipsRandomly(player2.ships);
        player1ShotRight = true;
        while (player1.allDeckQty != 0 && player2.allDeckQty != 0) {
            if (player1ShotRight) {
                if (!player1.shooting(player2.field, true, false)) player1ShotRight = !player1ShotRight;
            } else {
                if (!player2.shooting(player1.field, true, false)) player1ShotRight = !player1ShotRight;
            }
            textView.showSea(player1, player2, true, true);
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (player1.allDeckQty == 0) System.out.println("Победил игрок " + player2.name + "!");
        else System.out.println("Победил игрок " + player1.name + "!");
        System.out.println("Условные обозначения для жизненного цикла кораблей:");
        System.out.println("S - жив");
        System.out.println("$ - скорее мертв, чем жив");
        System.out.println("T - потоплен");
    }

    public void newStart() {
        System.out.println("Добрый день! Пожалуйста, введите Ваше имя: ");
        player1.name = (new Scanner(System.in)).nextLine();
        player2.name = "robot";
        System.out.println("Начнем игру, " + player1.name + ".");
        // создание и расстановка кораблей игроков
        init();
        player1ShotRight = true;
        while (player1.allDeckQty != 0 && player2.allDeckQty != 0) {
            if (player1ShotRight) {
                if (!player1.shooting(player2.field, false, false)) player1ShotRight = !player1ShotRight;
            } else {
                if (!player2.shooting(player1.field, true, false)) player1ShotRight = !player1ShotRight;
            }
            textView.showSea(player1, player2, true, false);
        }
        if (player1.allDeckQty == 0) System.out.println("Победил игрок " + player2.name + "!");
        else System.out.println("Победил игрок " + player1.name + "!");
    }

    void init() {
        //создание и расстановка кораблей игрока 1
        textView.showSea(player1, player2, true, false);
        player1.field.placeShipsByUser(player1.ships, textView);
        //создание и расстановка кораблей игрока 2 - робота
        player2.field.placeShipsRandomly(player2.ships);
        textView.showSea(player1, player2, true, false);
    }
}
