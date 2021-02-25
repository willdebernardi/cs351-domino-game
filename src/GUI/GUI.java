package GUI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 1200, 800);
        Boneyard boneyard = new Boneyard();
        boneyard.populateBoneyard();
        Board board = new Board();
        Player player = new Player(boneyard);

        Label label = new Label("The boneyard has " + boneyard.getBoneyard().size() + " dominoes");
        Label handLabel = new Label("You have " + player.accessPlayerHand().size() + " dominoes");
        label.setFont(new Font(32));
        handLabel.setFont(new Font(32));

        HBox labelBox = new HBox();
        labelBox.setAlignment(Pos.TOP_CENTER);
        labelBox.setSpacing(10);
        labelBox.getChildren().addAll(label, handLabel);

        HBox handBox = new HBox();
        Label selectedLabel = new Label("You've selected " + player.getSelectedDomino().toString());
        for(Domino dom : player.accessPlayerHand()) {
            ImageView img = new ImageView();
            img.setImage(dom.getImage());
            handBox.getChildren().add(img);
            img.setOnMouseClicked(event -> {
                player.setSelectedDomino(dom);
                selectedLabel.setText("You've selected " + player.getSelectedDomino().toString());
            });
        }

        Button btn = new Button("place");
        Group rowGroup = new Group();
        btn.setOnAction(event -> {
            board.addRow(player.getSelectedDomino());
            player.accessPlayerHand().remove(player.getSelectedDomino());
            handLabel.setText("You have " + player.accessPlayerHand().size() + " dominoes");
            for (Domino dom : board.getRow()) {
                ImageView img = new ImageView();
                img.setImage(dom.getImage());
                rowGroup.getChildren().add(img);
                root.setLeft(rowGroup);
            }
        });
        handBox.getChildren().add(btn);
        root.setRight(selectedLabel);
        root.setBottom(handBox);
        root.setTop(labelBox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
