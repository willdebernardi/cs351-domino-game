import java.util.ArrayList;

public class Board {
    private ArrayList<Domino> rowOne = new ArrayList<>();
    private ArrayList<Domino> rowTwo = new ArrayList<>();

    String rowOneToString() {
        return rowOne.toString();
    }

    String rowTwoToString() {
        return rowTwo.toString();
    }

    public void addRowOne(Domino domino) {
        rowOne.add(domino);
    }

    public void addRowTwo(Domino domino) {
        rowTwo.add(domino);
    }
}
