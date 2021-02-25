package GUI;

import java.util.*;

public class Boneyard {
    private static LinkedList<Domino> boneyard = new LinkedList<>();

    public void populateBoneyard() {
        for(int i = 0; i<=6; i++) {
            for(int j=i; j<=6; j++) {
                Domino dom = new Domino(i,j);
                boneyard.add(dom);
            }
        }
        Collections.shuffle(boneyard);
    }

    public LinkedList<Domino> getBoneyard() {
        return boneyard;
    }
}
