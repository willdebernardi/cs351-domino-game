package GUI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;

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

    public void checkGameEnd(Boneyard boneyard, Player player, Computer computer,
                             Alert gameOver, String playerWin, String computerWin) {
        if(boneyard.getBoneyard().isEmpty() ||
                player.accessPlayerHand().isEmpty() ||
                computer.accessComputerHand().isEmpty()) {
            int playerSum = player.getHand().sum();
            int computerSum = computer.getHand().sum();

            if(playerSum > computerSum) {
                gameOver.setContentText(computerWin);
            } else {
                gameOver.setContentText(playerWin);
            }
            gameOver.showAndWait();
            System.exit(0);
        }
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

        Alert gameOver = new Alert(Alert.AlertType.CONFIRMATION);
        String computerWin = "You lost, the computer had less points than you :(";
        String playerWin = "You won! Congratulations.";

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
        HBox computerRowBox = new HBox();
        VBox rowsBox = new VBox();
        Button placeBtn = new Button("Place");
        placeBtn.setOnAction(event -> {
            checkGameEnd(boneyard, player, computer, gameOver, playerWin, computerWin);
            RadioButton selectedRowSide = (RadioButton) rowChoice.getSelectedToggle();
            String toggleGroupValue = selectedRowSide.getText();
            handBox.getChildren().removeAll(player.getSelectedDomino().getImageView());
            player.placeDomino(board, player.getSelectedDomino(), flipButton.isSelected(), toggleGroupValue);
            handLabel.setText("You have " + player.accessPlayerHand().size() + " dominoes");
            rowImages.add(player.getSelectedDomino().getImageView());
            if(leftButton.isSelected()) {
                updateRowBoxLeft(rowBox, rowImages);
            } else {
                updateRowBox(rowBox, rowImages);
            }
            computer.placeDomino(board);
            boneyardLabel.setText("The boneyard has " + boneyard.getBoneyard().size() + " dominoes");
            ImageView image = computer.getLastPlayed().getImageView();
            image.setFitWidth(100);
            image.setPreserveRatio(true);
            rowImages.add(image);
            updateRowBox(computerRowBox, rowImages);
        });

        Button boneyardBtn = new Button("Draw from boneyard");
        boneyardBtn.setOnAction(event -> {
            checkGameEnd(boneyard, player, computer, gameOver, playerWin, computerWin);
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
        computerRowBox.setTranslateX(50);
        btnBox.getChildren().add(placeBtn);
        btnBox.getChildren().add(boneyardBtn);
        handBox.getChildren().add(0, btnBox);
        handBox.getChildren().add(1, checkBox);
        rowsBox.getChildren().addAll(rowBox, computerRowBox);
        root.setLeft(rowsBox);
        root.setRight(selectedLabel);
        root.setBottom(handBox);
        root.setTop(labelBox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
