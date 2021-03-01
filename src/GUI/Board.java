package GUI;

import java.util.ArrayList;

/***
 * This class is the board on which the dominoes are placed, the row is accessed with a getter
 * and added to with the add methods below.
 * by: Will DeBernardi
 */
public class Board {
    private ArrayList<Domino> row = new ArrayList<>();

    /**
     * Adds a domino to the end of the row
     * @param domino Domino to add to the row
     */
    public void addRow(Domino domino) {
        row.add(domino);
    }

    /**
     * Adds a domino to the beginning of the row
     * @param domino Domino to add to the row
     */
    public void addRowLeft(Domino domino) {
        row.add(0, domino);
    }

    /**
     * Getter method for the row
     * @return the row of the board
     */
    public ArrayList<Domino> getRow() {
        return row;
    }
}
