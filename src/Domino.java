public class Domino {
    private int sideOne, sideTwo;

    public Domino() {
        sideOne = 0;
        sideTwo = 0;
    }

    public Domino(int sideOne, int sideTwo) {
        this.sideOne = sideOne;
        this.sideTwo = sideTwo;
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
}
