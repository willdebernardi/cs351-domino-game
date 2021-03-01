package GUI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GUI extends Application {

    public void updateRowBox(HBox rowBox, ArrayList<ImageView> list) {
        ImageView image = list.get(list.size() - 1);
        rowBox.getChildren().add(image);
    }

    public void updateRowBoxLeft(HBox rowBox, ArrayList<ImageView> list) {
        ImageView image = list.get(list.size() - 1);
        rowBox.getChildren().add(0, image);
    }

    public void dominoImageView(Player player, HBox handBox, int i, Label selectedLabel) {
        Domino domino = player.accessPlayerHand().get(i);
        ImageView tempImageView = domino.getImageView();
        tempImageView.setFitWidth(100);
        tempImageView.setPreserveRatio(true);
        handBox.getChildren().add(tempImageView);
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
        Computer computer = new Computer(boneyard);

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
        VBox checkBox = new VBox();
        ToggleGroup rowChoice = new ToggleGroup();
        RadioButton leftButton = new RadioButton("Left");
        RadioButton rightButton = new RadioButton("Right");
        RadioButton flipButton = new RadioButton("Flip");
        leftButton.setToggleGroup(rowChoice);
        rightButton.setToggleGroup(rowChoice);
        rightButton.setSelected(true);
        checkBox.getChildren().addAll(leftButton, rightButton, flipButton);

        HBox rowBox = new HBox();
        Button placeBtn = new Button("Place");
        placeBtn.setOnAction(event -> {
            RadioButton selectedRowSide = (RadioButton) rowChoice.getSelectedToggle();
            String toggleGroupValue = selectedRowSide.getText();
            handBox.getChildren().removeAll(player.getSelectedDomino().getImageView());
            player.placeDomino(board, player.getSelectedDomino(), flipButton.isSelected(), toggleGroupValue);
            handLabel.setText("You have " + player.accessPlayerHand().size() + " dominoes");
//            for(int i = 0; i<board.getRow().size(); i++) {
//                rowImages.add(board.getRow().get(i).getImageView());
//            }
            rowImages.add(player.getSelectedDomino().getImageView());
            if(leftButton.isSelected()) {
                updateRowBoxLeft(rowBox, rowImages);
            } else {
                updateRowBox(rowBox, rowImages);
            }
            computer.placeDomino(board);
            rowImages.add(computer.getLastPlayed().getImageView());
            updateRowBox(rowBox, rowImages);
        });
        Button boneyardBtn = new Button("Draw from boneyard");
        boneyardBtn.setOnAction(event -> {
            player.drawBoneyard(boneyard);
            Domino newDomino = player.accessPlayerHand().get(player.accessPlayerHand().size()-1);
            newDomino.getImageView().setOnMouseClicked(event2 -> {
                player.setSelectedDomino(newDomino);
                selectedLabel.setText("You've selected " + player.getSelectedDomino().toString());
            });
            ImageView tempImageView = newDomino.getImageView();
            tempImageView.setFitWidth(100);
            tempImageView.setPreserveRatio(true);
            handBox.getChildren().add(tempImageView);
            boneyardLabel.setText("The boneyard has " + boneyard.getBoneyard().size() + " dominoes");
            handLabel.setText("You have " + player.accessPlayerHand().size() + " dominoes");
        });

        HBox btnBox = new HBox();
        btnBox.getChildren().add(placeBtn);
        btnBox.getChildren().add(boneyardBtn);
        handBox.getChildren().add(0, btnBox);
        handBox.getChildren().add(1, checkBox);
        root.setLeft(rowBox);
        root.setRight(selectedLabel);
        root.setBottom(handBox);
        root.setTop(labelBox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
