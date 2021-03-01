package GUI;

import java.util.ArrayList;

/**
 * Class which represents the hand of the player or the computer
 * 7 dominoes are selected from the boneyard and placed into the
 * ArrayList, dominoes are removed as necessary.
 */
public class Hand {
    private ArrayList<Domino> hand = new ArrayList<>(28);

    /**
     * Populates the hand with 7 dominoes from the pre-shuffled boneyard
     * @param boneyard Boneyard to draw from
     */
    public void populateHand(Boneyard boneyard) {
        for(int i = 0; i<=6; i++) {
            hand.add(boneyard.getBoneyard().get(i));
        }
        boneyard.getBoneyard().removeAll(hand);
    }

    /**
     * Sums the dominoes in the hand for win checking
     * @return int value of sum of all dots on dominoes
     */
    public int sum() {
        int totalSum = 0;
        for (Domino dom : hand) {
            int sideOne = dom.getSideOne();
            int sideTwo = dom.getSideTwo();
            int sum = sideOne+sideTwo;
            totalSum+= sum;
        }
        return totalSum;
    }

    /**
     * Getter method for the hand object itself
     * @return hand object
     */
    public ArrayList<Domino> getHand() {
        return hand;
    }
}
