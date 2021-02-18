import java.util.ArrayList;

public class Hand {
    ArrayList<Domino> hand = new ArrayList<>(28);

    public void populateHand(Boneyard boneyard) {
        for(int i = 0; i<=6; i++) {
            hand.add(boneyard.getDominos().get(i));
        }
        boneyard.getDominos().removeAll(hand);
    }
    
    public ArrayList<Domino> getHand() {
        return hand;
    }
}
