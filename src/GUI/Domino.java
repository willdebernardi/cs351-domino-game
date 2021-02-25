package GUI;

import javafx.scene.image.Image;

public class Domino {
    private int sideOne, sideTwo;
    private int id = 1;
    private Image image;

    public Domino(int sideOne, int sideTwo) {
        this.sideOne = sideOne;
        this.sideTwo = sideTwo;
        String imageFilePath = "Resources/" + sideOne + "_" + sideTwo + "@0.5x.png";
        this.image = new Image(imageFilePath, true);
        id++;
    }

    public void flip() {
        int originalSideOne = sideOne;
        sideOne = sideTwo;
        sideTwo = originalSideOne;
    }

    public String toString() {
        return "[" + sideOne + " | " + sideTwo + "]";
    }

    public int getSideOne() {
        return sideOne;
    }

    public int getSideTwo() {
        return sideTwo;
    }

    public Image getImage() {
        return image;
    }
}
