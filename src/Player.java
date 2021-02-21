import java.util.ArrayList;

public class Player {
    Hand hand = new Hand();
    Domino lastPlaced;
    int turn = 1;

    public Player(Boneyard boneyard) {
        hand.populateHand(boneyard);
    }

    public void placeDomino(Board board, int dominoIndex, String flip, String sideChoice) {
        Domino domino = hand.getHand().get(dominoIndex);
        if (flip.equals("y")) {
            domino.flip();
        }
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
            if(domino.getSideOne() == lastPlaced.getSideTwo() ||
            domino.getSideOne() == 0 ||
            lastPlaced.getSideTwo() == 0) {
                if (sideChoice.equals("l")) {
                    board.addRowLeft(domino);
                } else {
                    board.addRow(domino);
                }
                hand.getHand().remove(domino);
            } else {
                System.out.println("Invalid move, try again, or draw from the boneyard");
            }
        }
    }

    public void drawBoneyard(Boneyard boneyard) {
        Domino domino = boneyard.getBoneyard().get(0);
        boneyard.getBoneyard().remove(domino);
        hand.getHand().add(domino);
    }

    public ArrayList<Domino> getPlayerHand() {
        return hand.getHand();
    }

}
