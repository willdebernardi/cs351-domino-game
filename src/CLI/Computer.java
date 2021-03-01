package CLI;

import java.util.ArrayList;

/**
 * The computer which the player plays against. Not particularly intelligent, can only place
 * to the right. Searches for a legal move, if none are found it draws from the boneyard until
 * a legal move is drawn.
 *
 * by: Will DeBernardi
 */
public class Computer {
    private Hand hand = new Hand();
    private Boneyard boneyard = new Boneyard();
    private Boolean madeMove = false;

    /**
     * Constructor which populates the hand with dominoes from the boneyard
     * @param boneyard Boneyard to draw from
     */
    public Computer (Boneyard boneyard) {
        hand.populateHand(boneyard);
    }

    /**
     * Draws a domino from the boneyard and then removes it from both the
     * boneyard and the hand
     * @param boneyard Boneyard to draw from
     */
    public void drawBoneyard(Boneyard boneyard) {
        Domino domino = boneyard.getBoneyard().get(0);
        boneyard.getBoneyard().remove(domino);
        hand.getHand().add(domino);
    }

    /**
     * Places a domino, searches for legal move, if none are found then
     * it draws from the boneyard until a legal move is found
     * @param board Board to place the domino on
     */
    public void placeDomino(Board board) {
        Domino lastPlaced = board.getRow().get(board.getRow().size() - 1);
        do {
            for (Domino dom : hand.getHand()) {
                if (dom.getSideOne() == lastPlaced.getSideTwo() ||
                dom.getSideOne() == 0) {
                    board.addRow(dom);
                    hand.getHand().remove(dom);
                    madeMove = true;
                    System.out.println("Computer plays " + dom.toString());
                    break;
                } else if(dom.getSideTwo() == lastPlaced.getSideTwo() ||
                dom.getSideTwo() == 0) {
                    dom.flip();
                    board.addRow(dom);
                    hand.getHand().remove(dom);
                    madeMove = true;
                    System.out.println("Computer plays " + dom.toString());
                    break;
                }
            }
            if (!madeMove) {
                drawBoneyard(boneyard);
                System.out.println("Computer drew from boneyard");
            }
        } while (!madeMove);
        madeMove = false;
    }

    /**
     * Getter method which allows access to the hand
     * @return the hand instance variable of the hand object
     */
    public ArrayList<Domino> accessComputerHand() {
        return hand.getHand();
    }

    /**
     * Getter method for the hand object
     * @return the hand object itself
     */
    public Hand getHand() {
        return hand;
    }
}
