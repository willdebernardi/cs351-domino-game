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

/**
 * This class handles the creation of the GUI, as well as as some checking for the winner
 * Most of the logic - including placement - is handled by the EventHandler's on the buttons
 *
 * by: Will DeBernardi
 */
public class GUI extends Application {

    /**
     * Updates the row on the GUI with the last placed domino on the right
     * @param rowBox GUI row to update
     * @param list List to take the image from
     */
    public void updateRowBox(HBox rowBox, ArrayList<ImageView> list) {
        ImageView image = list.get(list.size() - 1);
        rowBox.getChildren().add(image);
    }

    /**
     * Updates the row on the GUI with the last placed domino on the left
     * @param rowBox GUI row to update
     * @param list List to take the image from
     */
    public void updateRowBoxLeft(HBox rowBox, ArrayList<ImageView> list) {
        ImageView image = list.get(list.size() - 1);
        rowBox.getChildren().add(0, image);
    }

    /**
     * Checks if the game-ending conditions have been met
     * @param boneyard Boneyard to check if empty
     * @param player Player to sum dominoes
     * @param computer Computer to sum dominoes
     */
    public void checkGameEnd(Boneyard boneyard, Player player, Computer computer) {
        Alert gameOver = new Alert(Alert.AlertType.CONFIRMATION);
        String computerWin = "You lost, the computer had less points than you :(";
        String playerWin = "You won! Congratulations.";

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

    /**
     * Obtains the image view of the domino, and adds event listener to change
     * the selected domino on click
     * @param player Player to access hand
     * @param handBox GUI element displaying the hand
     * @param i Index of the domino
     * @param selectedLabel Label for the selected domino
     */
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

    /**
     * Bulk of the logic, in which the GUI elements are created and changed as necessary
     * @param primaryStage Stage to which the GUI elements are attached
     */
    @Override
    public void start(Stage primaryStage) {
        // Images of the dominoes
        ArrayList<ImageView> rowImages = new ArrayList<>();
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 1200, 800);
        Boneyard boneyard = new Boneyard();
        boneyard.populateBoneyard();
        Board board = new Board();
        Player player = new Player(boneyard);
        Computer computer = new Computer(boneyard);


        // Labels at the top of the screen displaying the quantity
        // of dominoes in the boneyard and player hand
        Label boneyardLabel = new Label("The boneyard has " + boneyard.getBoneyard().size() + " dominoes");
        Label handLabel = new Label("You have " + player.accessPlayerHand().size() + " dominoes");
        boneyardLabel.setFont(new Font(32));
        handLabel.setFont(new Font(32));

        // HBox to which the labels are added and spaced
        HBox labelBox = new HBox();
        labelBox.setAlignment(Pos.TOP_CENTER);
        labelBox.setSpacing(10);
        labelBox.getChildren().addAll(boneyardLabel, handLabel);

        // HBox which displays the dominoes in the player hand
        HBox handBox = new HBox();
        Label selectedLabel = new Label("You've selected " + player.getSelectedDomino().toString());
        for(int i = 0; i < player.accessPlayerHand().size(); i++) {
            dominoImageView(player, handBox, i, selectedLabel);
        }
        handBox.setAlignment(Pos.CENTER);
        handBox.setSpacing(5.0);

        // VBox which contains the placement specifications
        VBox checkBox = new VBox();
        ToggleGroup rowChoice = new ToggleGroup();
        RadioButton leftButton = new RadioButton("Left");
        RadioButton rightButton = new RadioButton("Right");
        RadioButton flipButton = new RadioButton("Flip");
        leftButton.setToggleGroup(rowChoice);
        rightButton.setToggleGroup(rowChoice);
        rightButton.setSelected(true);
        checkBox.getChildren().addAll(leftButton, rightButton, flipButton);

        // Boxes for the rows representing the board
        HBox rowBox = new HBox();
        HBox computerRowBox = new HBox();
        VBox rowsBox = new VBox();

        // Button for placing the domino
        Button placeBtn = new Button("Place");
        placeBtn.setOnAction(event -> {
            // First checks if the game is over
            checkGameEnd(boneyard, player, computer);
            RadioButton selectedRowSide = (RadioButton) rowChoice.getSelectedToggle();
            String toggleGroupValue = selectedRowSide.getText();
            handBox.getChildren().removeAll(player.getSelectedDomino().getImageView());
            // Places domino, getting the specification of placement from the checkboxes
            player.placeDomino(board, player.getSelectedDomino(),
                    flipButton.isSelected(), toggleGroupValue);
            handLabel.setText("You have " + player.accessPlayerHand().size() + " dominoes");
            // Adds the image of the selected domino to the list of images
            rowImages.add(player.getSelectedDomino().getImageView());
            // Updates the GUI with the newly places domino
            if(leftButton.isSelected()) {
                updateRowBoxLeft(rowBox, rowImages);
            } else {
                updateRowBox(rowBox, rowImages);
            }
            //Computer move and updating the GUI with the placement
            computer.placeDomino(board);
            boneyardLabel.setText("The boneyard has " + boneyard.getBoneyard().size()
                    + " dominoes");
            ImageView image = computer.getLastPlayed().getImageView();
            image.setFitWidth(100);
            image.setPreserveRatio(true);
            rowImages.add(image);
            updateRowBox(computerRowBox, rowImages);
        });

        // Button for drawing from the boneyard
        Button boneyardBtn = new Button("Draw from boneyard");
        boneyardBtn.setOnAction(event -> {
            // First checks if game is over
            checkGameEnd(boneyard, player, computer);
            player.drawBoneyard(boneyard);
            // After drawing the new domino, updates the GUI and adds the event listener
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

        // Adding the elements to the scene
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

    /**
     * Method which starts launches the application
     * @param args Console arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
