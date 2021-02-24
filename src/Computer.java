import java.util.ArrayList;

public class Computer {
    Hand hand = new Hand();
    Boneyard boneyard = new Boneyard();
    Boolean madeMove = false;

    public Computer (Boneyard boneyard) {
        hand.populateHand(boneyard);
    }

    public void drawBoneyard(Boneyard boneyard) {
        Domino domino = boneyard.getBoneyard().get(0);
        boneyard.getBoneyard().remove(domino);
        hand.getHand().add(domino);
    }

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

    public ArrayList<Domino> accessComputerHand() {
        return hand.getHand();
    }

    public Hand getHand() {
        return hand;
    }
}
