public class Domino {
    private int sideOne, sideTwo;
    private int id = 1;

    public Domino(int sideOne, int sideTwo) {
        this.sideOne = sideOne;
        this.sideTwo = sideTwo;
        id++;
    }

    public void flip() {
        int originalSideOne = sideOne;
        sideOne = sideTwo;
        sideTwo = originalSideOne;
    }

    public String toString() {
        String output = "[" + sideOne + " | " + sideTwo + "]";
        return output;
    }

    public int getSideOne() {
        return sideOne;
    }

    public int getSideTwo() {
        return sideTwo;
    }

    public int getId() {
        return id;
    }
}
