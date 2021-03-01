package GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Class which represents the Domino object itself, contains logic to flip
 * the domino and assign corresponding image to each domino.
 *
 * by: Will DeBernardi
 */
public class Domino {
    private int sideOne, sideTwo;
    private Image image;
    private  ImageView imageView;

    /**
     * Constructor which assigns side values to the domino
     * Additionally, assigns corresponding image to the domino
     * @param sideOne int value of side one
     * @param sideTwo int value of side two
     */
    public Domino(int sideOne, int sideTwo) {
        this.sideOne = sideOne;
        this.sideTwo = sideTwo;
        String imageFilePath = "Resources/" + sideOne + "_" + sideTwo + "@0.5x.png";
        this.image = new Image(imageFilePath, true);
        this.imageView = new ImageView(this.image);
    }

    /**
     * Flips the sides of the domino and changes the image to the proper one
     */
    public void flip() {
        int originalSideOne = sideOne;
        sideOne = sideTwo;
        sideTwo = originalSideOne;
        String imageFilePath = "Resources/" + sideOne + "_" + sideTwo + "@0.5x.png";
        this.image = new Image(imageFilePath, true);
        this.imageView = new ImageView(this.image);
        this.imageView.setFitWidth(100);
        this.imageView.setPreserveRatio(true);
    }

    /**
     * Creates properly formatted string representation of domino
     * @return formatted string of domino
     */
    public String toString() {
        return "[" + sideOne + " | " + sideTwo + "]";
    }

    /**
     * Getter method for side one
     * @return int value of side one
     */
    public int getSideOne() {
        return sideOne;
    }

    /**
     * Getter method for side two
     * @return int value of side two
     */
    public int getSideTwo() {
        return sideTwo;
    }

    /**
     * Getter method for the ImageView so that it can be added to the GUI
     * @return ImageView of the domino
     */
    public ImageView getImageView() {
        return imageView;
    }
}
