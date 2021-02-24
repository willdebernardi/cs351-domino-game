import java.util.ArrayList;

public class Player {
    Hand hand = new Hand();
    Domino lastPlaced;
    int turn = 1;
    boolean invalidMove = false;

    public Player(Boneyard boneyard) {
        hand.populateHand(boneyard);
    }

    public void placeDomino(Board board, int dominoIndex, String flip, String sideChoice) {
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
            lastPlaced = board.getRow().get(board.getRow().size() - 1);
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

    public void drawBoneyard(Boneyard boneyard) {
        Domino domino = boneyard.getBoneyard().get(0);
        boneyard.getBoneyard().remove(domino);
        hand.getHand().add(domino);
    }

    public ArrayList<Domino> accessPlayerHand() {
        return hand.getHand();
    }

    public Hand getHand() {
        return hand;
    }
}
