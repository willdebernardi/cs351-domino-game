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

import java.util.ArrayList;
import java.util.HashMap;

public class GUI extends Application {

    public void updateRowBox(HBox rowBox, ArrayList<ImageView> list) {
        ImageView image = list.get(list.size() - 1);
        rowBox.getChildren().add(image);
    }

    @Override
    public void start(Stage primaryStage) {
        ArrayList<ImageView> rowImages = new ArrayList<>();
        ArrayList<ImageView> handImages = new ArrayList<>();
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
        for(int i = 0; i < player.accessPlayerHand().size(); i++) {
            handImages.add(player.accessPlayerHand().get(i).getImageView());
            handBox.getChildren().add(handImages.get(i));
            int finalI = i;
            handImages.get(i).setOnMouseClicked(event -> {
                player.setSelectedDomino(player.accessPlayerHand().get(finalI));
                selectedLabel.setText("You've selected " + player.getSelectedDomino().toString());
            });
        }
        handBox.setAlignment(Pos.CENTER);
        handBox.setSpacing(5.0);

        HBox rowBox = new HBox();
        Button btn = new Button("place");
        btn.setOnAction(event -> {
            board.addRow(player.getSelectedDomino());
//            player.accessPlayerHand().remove(player.getSelectedDomino());
            handLabel.setText("You have " + player.accessPlayerHand().size() + " dominoes");
            handBox.getChildren().removeAll(player.getSelectedDomino().getImageView());

            for(int i = 0; i<board.getRow().size(); i++) {
                rowImages.add(board.getRow().get(i).getImageView());
            }
            updateRowBox(rowBox, rowImages);
        });

        handBox.getChildren().add(btn);
        root.setLeft(rowBox);
        root.setRight(selectedLabel);
        root.setBottom(handBox);
        root.setTop(labelBox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
