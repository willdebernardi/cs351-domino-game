package GUI;

import java.util.ArrayList;

/**
 * Class which represents the Player, handles the logic of placing to the
 * board, checking validity, and drawing from the boneyard. Keeps track of
 * the selected domino to update the GUI
 *
 * by: Will DeBernardi
 */
public class Player {
    private Hand hand = new Hand();
    private Domino selectedDomino = new Domino(0,0);
    private int turn = 1;

    /**
     * Constructor which populates the hand with 7 dominoes from the boneyard
     * @param boneyard Boneyard to draw from
     */
    public Player(Boneyard boneyard) {
        hand.populateHand(boneyard);
    }

    /**
     * Handles the placement of the domino based on GUI input, and checks
     * the validity of the placement
     * @param board Board to place domino to
     * @param domino Domino to place
     * @param flip State of checkbox to flip
     * @param sideChoice Choie of side to place at
     */
    public void placeDomino(Board board, Domino domino, boolean flip, String sideChoice) {
        if (flip) {
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
            if(sideChoice.equals("Left")) {
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

    /**
     * Getter method for the selected domino
     * @return The selected domino
     */
    public Domino getSelectedDomino() {
        return selectedDomino;
    }

    /**
     * Setter method for the selected domino
     * @param selectedDomino Domino to set as selected
     */
    public void setSelectedDomino(Domino selectedDomino) {
        this.selectedDomino = selectedDomino;

    }
}
