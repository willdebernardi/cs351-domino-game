import java.util.*;

public class Boneyard {
    private static LinkedList<Domino> dominos = new LinkedList<>();

    public void populateBoneyard() {
        for(int i = 0; i<=6; i++) {
            for(int j=i; j<=6; j++) {
                Domino dom = new Domino(i,j);
                dominos.add(dom);
            }
        }
        Collections.shuffle(dominos);
    }

    public LinkedList<Domino> getDominos() {
        return dominos;
    }
}
