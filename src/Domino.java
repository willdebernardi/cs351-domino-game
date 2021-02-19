public class Domino {
    private int sideOne, sideTwo;

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

    public int getSideOne() {
        return sideOne;
    }

    public int getSideTwo() {
        return sideTwo;
    }
}
