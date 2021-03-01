package GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Domino {
    private int sideOne, sideTwo;
    private int id = 1;
    private Image image;
    private  ImageView imageView;

    public Domino(int sideOne, int sideTwo) {
        this.sideOne = sideOne;
        this.sideTwo = sideTwo;
        String imageFilePath = "Resources/" + sideOne + "_" + sideTwo + "@0.5x.png";
//        InputStream blah = getClass().getResourceAsStream(imageFilePath);
        this.image = new Image(imageFilePath, true);
        this.imageView = new ImageView(this.image);
        id++;
    }

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

    public String toString() {
        return "[" + sideOne + " | " + sideTwo + "]";
    }

    public int getSideOne() {
        return sideOne;
    }

    public int getSideTwo() {
        return sideTwo;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public ImageView getImageView() {
        return imageView;
    }
}
