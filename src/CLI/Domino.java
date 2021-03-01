package CLI;

/**
 * Class which represents the Domino object itself, contains logic to flip
 * the domino and assign corresponding image to each domino.
 *
 * by: Will DeBernardi
 */
public class Domino {
    private int sideOne, sideTwo;
    private int id = 1;

    /**
     * Constructor which assigns side values to the domino
     * @param sideOne int value of side one
     * @param sideTwo int value of side two
     */
    public Domino(int sideOne, int sideTwo) {
        this.sideOne = sideOne;
        this.sideTwo = sideTwo;
        id++;
    }

    /**
     * Flips the sides of the domino
     */
    public void flip() {
        int originalSideOne = sideOne;
        sideOne = sideTwo;
        sideTwo = originalSideOne;
    }

    /**
     * Creates properly formatted string representation of domino
     * @return formatted string of domino
     */
    public String toString() {
        return "[" + sideOne + " | " + sideTwo + "]";
    }

    /**
     * Getter method for side one
     * @return int value of side one
     */
    public int getSideOne() {
        return sideOne;
    }

    /**
     * Getter method for side two
     * @return int value of side two
     */
    public int getSideTwo() {
        return sideTwo;
    }
}
