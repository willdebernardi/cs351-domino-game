import java.util.ArrayList;

public class Hand {
    ArrayList<Domino> hand = new ArrayList<>(28);

    public void populateHand(Boneyard boneyard) {
        for(int i = 0; i<=6; i++) {
            hand.add(boneyard.getBoneyard().get(i));
        }
        boneyard.getBoneyard().removeAll(hand);
    }
    
    public ArrayList<Domino> getHand() {
        return hand;
    }
}
