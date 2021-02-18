import java.util.ArrayList;

public class Player {
    Boneyard boneyard = new Boneyard();
    Hand hand = new Hand();

    public Player() {
        hand.populateHand(boneyard);
    }

    public ArrayList<Domino> getPlayerHand() {
        return hand.getHand();
    }

}
