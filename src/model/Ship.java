package model;

public class Ship {
    int deckQty;
    public int liveDeckQty;
    Cell upperLeftCell;
    boolean isHorizontal;
    Field field;

    public Ship(Cell upperLeftCell, int deckQty, boolean isHorizontal, Field field) {
        this.deckQty = deckQty;
        this.liveDeckQty = liveDeckQty;
        this.upperLeftCell = upperLeftCell;
        this.isHorizontal = isHorizontal;
        this.field = field;

        for (int i = 0; i < deckQty; i++) {
            if (isHorizontal) {
                field.cells[upperLeftCell.x + i][upperLeftCell.y].ship = this;
            }
            else {
                field.cells[upperLeftCell.x][upperLeftCell.y + i].ship = this;
            }
        }
    }
}
