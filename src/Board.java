import java.util.ArrayList;

public class Board {
    private ArrayList<Domino> row = new ArrayList<>();

    String rowToString() {
        return row.toString();
    }

    public void addRow(Domino domino) {
        row.add(domino);
    }

    public void addRowLeft(Domino domino) {
        row.add(0, domino);
    }

    public ArrayList<Domino> getRow() {
        return row;
    }
}
