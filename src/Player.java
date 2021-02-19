import java.util.ArrayList;

public class Player {
    Hand hand = new Hand();
    Domino lastPlaced;

    public Player(Boneyard boneyard) {
        hand.populateHand(boneyard);
    }

    public void placeDomino(Board board, int dominoIndex, int rowChoice, String flip) {
        Domino domino = hand.getHand().get(dominoIndex);
        lastPlaced = domino;
        if (rowChoice == 1) {
            if (flip.equals("y")) {
                domino.flip();
            }
            board.addRowOne(domino);
        } else if (rowChoice == 2) {
            if (flip.equals("y")) {
                domino.flip();
            }
            board.addRowTwo(domino);
        }
        hand.getHand().remove(domino);
    }

    public void drawBoneyard() {

    }

    public ArrayList<Domino> getPlayerHand() {
        return hand.getHand();
    }

}
