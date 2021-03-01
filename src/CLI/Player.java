package CLI;

import java.util.ArrayList;

/**
 * Class which represents the Player, handles the logic of placing to the
 * board, checking validity, and drawing from the boneyard.
 *
 * by: Will DeBernardi
 */
public class Player {
    private Hand hand = new Hand();
    private int turn = 1;

    /**
     * Constructor which populates the hand with 7 dominoes from the boneyard
     * @param boneyard Boneyard to draw from
     */
    public Player(Boneyard boneyard) {
        hand.populateHand(boneyard);
    }

    /**
     * Handles the placement of the domino based on user input, and checks
     * the validity of the placement
     * @param board board to place upon
     * @param dominoIndex selected domino from hand to place
     * @param flip choice to flip the domino or not
     * @param sideChoice choice for which side of row to place domino on
     */
    public void placeDomino(Board board, int dominoIndex,
                            String flip, String sideChoice) {
        Domino domino = hand.getHand().get(dominoIndex);
        if (flip.equals("y")) {
            domino.flip();
        }
        // Keeps track of first turn to place domino normally
        if (turn == 1) {
            if (sideChoice.equals("l")) {
                board.addRowLeft(domino);
            } else {
                board.addRow(domino);
                turn++;
            }
            hand.getHand().remove(domino);
        } else {
            Domino lastPlaced = board.getRow().get(board.getRow().size() - 1);
            // If left side of row is chosen to place upon
            // check validity against the first domino in the row
            // rather than the last placed domino
            if(sideChoice.equals("l")) {
                Domino firstDomino = board.getRow().get(0);
                if(domino.getSideTwo() == firstDomino.getSideOne() ||
                domino.getSideTwo() == 0 ||
                firstDomino.getSideOne() == 0) {
                    board.addRowLeft(domino);
                    hand.getHand().remove(domino);
                } else {
                    throw new IllegalArgumentException("Invalid move, try again or draw from the boneyard");
                }
            } else {
                // Checks validity of domino against last placed domino
                if(domino.getSideOne() == lastPlaced.getSideTwo() ||
                        domino.getSideOne() == 0 ||
                        lastPlaced.getSideTwo() == 0) {
                    board.addRow(domino);
                    hand.getHand().remove(domino);
                } else {
                    throw new IllegalArgumentException("Invalid move, try again, or draw from the boneyard");
                }
            }
        }
    }

    /**
     * Draws domino from the pre-shuffled boneyard
     * @param boneyard boneyard to draw from
     */
    public void drawBoneyard(Boneyard boneyard) {
        Domino domino = boneyard.getBoneyard().get(0);
        boneyard.getBoneyard().remove(domino);
        hand.getHand().add(domino);
    }

    /**
     * Getter method which provides access to the hand of the player
     * @return access to the hand instance variable of hand object
     */
    public ArrayList<Domino> accessPlayerHand() {
        return hand.getHand();
    }

    /**
     * Getter method for the hand itself
     * @return hand object itself
     */
    public Hand getHand() {
        return hand;
    }
}
