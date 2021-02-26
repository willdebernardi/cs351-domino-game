package GUI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GUI extends Application {

    public void updateRowBox(HBox rowBox, ArrayList<ImageView> list) {
        ImageView image = list.get(list.size() - 1);
        rowBox.getChildren().add(image);
    }

    public void dominoImageView(Player player, HBox handBox, int i, Label selectedLabel) {
        Domino domino = player.accessPlayerHand().get(i);
        handBox.getChildren().add(domino.getImageView());
        handBox.getChildren().get(i).setOnMouseClicked(event -> {
            player.setSelectedDomino(domino);
            selectedLabel.setText("You've selected " + player.getSelectedDomino().toString());
        });

    }

    @Override
    public void start(Stage primaryStage) {
        ArrayList<ImageView> rowImages = new ArrayList<>();
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 1200, 800);
        Boneyard boneyard = new Boneyard();
        boneyard.populateBoneyard();
        Board board = new Board();
        Player player = new Player(boneyard);

        Label boneyardLabel = new Label("The boneyard has " + boneyard.getBoneyard().size() + " dominoes");
        Label handLabel = new Label("You have " + player.accessPlayerHand().size() + " dominoes");
        boneyardLabel.setFont(new Font(32));
        handLabel.setFont(new Font(32));

        HBox labelBox = new HBox();
        labelBox.setAlignment(Pos.TOP_CENTER);
        labelBox.setSpacing(10);
        labelBox.getChildren().addAll(boneyardLabel, handLabel);

        HBox handBox = new HBox();
        Label selectedLabel = new Label("You've selected " + player.getSelectedDomino().toString());
        for(int i = 0; i < player.accessPlayerHand().size(); i++) {
            dominoImageView(player, handBox, i, selectedLabel);
        }
        handBox.setAlignment(Pos.CENTER);
        handBox.setSpacing(5.0);

        HBox rowBox = new HBox();
        Button placeBtn = new Button("Place");
        Button boneyardBtn = new Button("Draw from boneyard");
        placeBtn.setOnAction(event -> {
            board.addRow(player.getSelectedDomino());
            player.accessPlayerHand().remove(player.getSelectedDomino());
            handLabel.setText("You have " + player.accessPlayerHand().size() + " dominoes");
            handBox.getChildren().removeAll(player.getSelectedDomino().getImageView());

            for(int i = 0; i<board.getRow().size(); i++) {
                rowImages.add(board.getRow().get(i).getImageView());
            }
            updateRowBox(rowBox, rowImages);
        });
        boneyardBtn.setOnAction(event -> {
            player.drawBoneyard(boneyard);
            Domino newDomino = player.accessPlayerHand().get(player.accessPlayerHand().size()-1);
            newDomino.getImageView().setOnMouseClicked(event2 -> {
                player.setSelectedDomino(newDomino);
                selectedLabel.setText("You've selected " + player.getSelectedDomino().toString());
            });
            handBox.getChildren().add(newDomino.getImageView());
            boneyardLabel.setText("The boneyard has " + boneyard.getBoneyard().size() + " dominoes");
            handLabel.setText("You have " + player.accessPlayerHand().size() + " dominoes");
        });

        HBox btnBox = new HBox();
        btnBox.getChildren().add(placeBtn);
        btnBox.getChildren().add(boneyardBtn);
        handBox.getChildren().add(0, btnBox);
        root.setLeft(rowBox);
        root.setRight(selectedLabel);
        root.setBottom(handBox);
        root.setTop(labelBox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
