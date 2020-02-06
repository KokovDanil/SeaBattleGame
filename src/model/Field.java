package model;

import view.TextView;

import java.util.Random;
import java.util.Scanner;

public class Field {
    public Cell[][] cells = new Cell[10][10];
    Player player;

    public Field(Player player) {
        this.player = player;
        init();
    }

    void init(){
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                cells[i][j] = new Cell(i, j, null, false);
            }
        }
    }

    void placeShipsRandomly(Ship[][] ships){
        Random random = new Random();
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < ships[i].length; j++){
                int upperLeftX, upperLeftY, deckQty;
                boolean isHorizontal;
                do {
                    upperLeftX = random.nextInt(10);
                    upperLeftY = random.nextInt(10);
                    deckQty = i + 1;
                    isHorizontal = random.nextBoolean();
                } while (! checkFreeSpace(upperLeftX, upperLeftY, deckQty, isHorizontal, this));
                ships[i][j] = new Ship(this.cells[upperLeftX][upperLeftY], deckQty, isHorizontal, this);
            }
        }
    }

    void placeShipsByUser(Ship[][] ships, TextView textView){
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < ships[i].length; j++){
                int upperLeftX, deckQty;
                int upperLeftY = -1;
                boolean isHorizontal = true;
                String isHorizontalStr;
                do {
                    boolean error;
                    deckQty = i + 1;
                    if (i > 0) {
                        do {
                            error = false;
                            System.out.println("Задайте ориентацию " + (j + 1) + "-го " + deckQty + "-палубного корабля(h - горизонтальная, v - вертикальная):");
                            isHorizontalStr = (new Scanner(System.in)).nextLine();
                            if (isHorizontalStr.equals("h")) isHorizontal = true;
                            else if (isHorizontalStr.equals("v")) isHorizontal = false;
                            else error = true;
                            if (error) System.out.println("Неверный выбор ориентации корабля. Повторите");
                        } while (error);
                    }
                    do {
                        error = false;
                        System.out.println("Введите координаты верхнего левого угла корабля" + (j + 1) + "-го " + deckQty + "-палубного корабля(пример ввода: d4)");
                        String upperLeftCorner = (new Scanner(System.in)).nextLine();
                        upperLeftX = upperLeftCorner.charAt(0) - 97;
                        upperLeftX = upperLeftCorner.charAt(1) - 49;
                        if (upperLeftCorner.length() > 2) upperLeftY = ((upperLeftCorner.charAt(1) - 48) * 10 + upperLeftCorner.charAt(2) - 49);
                        if (upperLeftX < 0 || upperLeftX > 9 || upperLeftY < 0 || upperLeftY > 9) error = true;
                        if (error) System.out.println("Значение за пределами поля. Повторите ввод.");
                    } while (error);
                    if (!checkFreeSpace(upperLeftX, upperLeftY, deckQty, isHorizontal, this))
                        System.out.println("Нельзя распологать корабль таким образом. Повторите");
                } while (!checkFreeSpace(upperLeftX, upperLeftY, deckQty, isHorizontal, this));
                ships[i][j] = new Ship(this.cells[upperLeftX][upperLeftY], deckQty, isHorizontal, this);
                textView.showPartSea(this.player, true);
            }
        }
    }

    boolean checkFreeSpace(int upperLeftX, int upperLeftY, int deckQty, boolean isHorizontal, Field field) {
        if (isHorizontal && (upperLeftX + deckQty) > 10) return false;
        if (!isHorizontal && (upperLeftY + deckQty) > 10) return false;
        for (int i = 0; i < deckQty; i++){
            for (int j = upperLeftX - 1; j < upperLeftX + 2; j++){
                for (int k = upperLeftY - 1; k < upperLeftY + 2; k++){
                    try{
                        if(isHorizontal && field.cells[j + i][k].ship != null) return false;
                    } catch (ArrayIndexOutOfBoundsException e){}
                    try{
                        if(isHorizontal && field.cells[j][k + i].ship != null) return false;
                    } catch (ArrayIndexOutOfBoundsException e){}
                }
            }
        }
        return true;
    }
}
