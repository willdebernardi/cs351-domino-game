package CLI;

import java.util.Collections;
import java.util.LinkedList;

/**
 * This class generates the boneyard from which the hand objects
 * are populated, and the player can draw from at any time.
 *
 * by: Will DeBernardi
 */
public class Boneyard {
    private static final LinkedList<Domino> boneyard = new LinkedList<>();

    /**
     * Populates the boneyard with the 28 dominoes and then shuffles them
     */
    public void populateBoneyard() {
        for(int i = 0; i<=6; i++) {
            for(int j=i; j<=6; j++) {
                Domino dom = new Domino(i,j);
                boneyard.add(dom);
            }
        }
        Collections.shuffle(boneyard);
    }

    /**
     * Getter method for the boneyard
     * @return the boneyard
     */
    public LinkedList<Domino> getBoneyard() {
        return boneyard;
    }
}
