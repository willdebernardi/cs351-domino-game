package CLI;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main game loop, creates instances of neccessary objects, and then uses a while loop
 * to handle player move, then initiates computer. After confiditons are met, checks for
 * the winner.
 *
 * By: Will DeBernardi
 */
public class Game {
    public static void main(String[] args) {
        // Rows for string representation of board
        ArrayList<String> rowOne = new ArrayList<>();
        ArrayList<String> rowTwo = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        Boneyard boneyard = new Boneyard();
        boneyard.populateBoneyard();
        Board board = new Board();
        Player player = new Player(boneyard);
        Computer computer = new Computer(boneyard);
        // Booleans to track certain player conditions
        boolean madeMove = false;
        boolean shouldBreak = false;
        boolean placedLeft = false;
        boolean firstTurn = true;
        // Offset for the second-row stagger
        StringBuilder offset = new StringBuilder("    ");
        System.out.println("Boneyard has " + boneyard.getBoneyard().size() + " dominoes");

        do {
            while(!madeMove) {
                if(shouldBreak) {
                    break;
                }
                System.out.println("Tray: " + player.accessPlayerHand());
                System.out.println("Human turn");
                System.out.println("[p] Play Domino" +
                        "\n[d] Draw from boneyard" +
                        "\n[q] Quit");
                String playChoice = sc.next();
                switch (playChoice) {
                    case "p" :
                        System.out.println("Which domino would you like to place?");
                        int dominoIndex = sc.nextInt();
                        String sideChoice = "r";
                        // Only asks for row placement after the first turn
                        if (!firstTurn) {
                            System.out.println("Left or right? (l/r)");
                            sideChoice = sc.next();
                            if(sideChoice.equals("l")) {
                                placedLeft = true;
                            }
                        }
                        System.out.println("Flip domino? (y/n)");
                        String flipChoice = sc.next();
                        // Attempts to place the domino, if there is an invalid move then
                        // the loop begins again
                        try {
                            player.placeDomino(board, dominoIndex, flipChoice, sideChoice);
                            madeMove = true;
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid move, try again or draw from the boneyard.");
                            madeMove = false;
                            break;
                        }
                    case "d" :
                        player.drawBoneyard(boneyard);
                        System.out.println("GUI.Boneyard has " + boneyard.getBoneyard().size() + " dominoes");
                        if (boneyard.getBoneyard().isEmpty()) {
                            shouldBreak = true;
                        }
                        continue;
                    case "q" :
                        System.out.println("Quitting game, thanks for playing!");
                        System.exit(0);
                    default :
                        System.out.println("Invalid selection, please try again");
                }
            }
            firstTurn = false;

            // Loop for the computer turn
            while(madeMove) {
                madeMove = false;
                Domino lastPlaced = board.getRow().get(board.getRow().size() - 1);
                // Checks if domino was placed to the left, and if so,
                // the last placed domino is changed to the furthest left
                // and then is added to the first row
                if(placedLeft) {
                    lastPlaced = board.getRow().get(0);
                    rowOne.add(0, lastPlaced.toString());
                    // Adds spaces to the offset if domino is placed to the left
                    // in order to preserve the formatting
                    offset.append("         ");
                    placedLeft = false;
                } else {
                    rowOne.add(lastPlaced.toString());
                }

                System.out.println("Computer turn");
                computer.placeDomino(board);

                // Displays the properly formatted string representation of the board
                // with the computer moves being staggered
                System.out.println("Boneyard has " + boneyard.getBoneyard().size() + " dominoes");
                lastPlaced = board.getRow().get(board.getRow().size() - 1);
                rowTwo.add(lastPlaced.toString());

                System.out.println(rowOne.toString());
                System.out.println(offset + rowTwo.toString());
            }
            if (boneyard.getBoneyard().isEmpty()) {
                break;
            }
        } while(!player.accessPlayerHand().isEmpty() ||
                !computer.accessComputerHand().isEmpty());

        // Checks for points of hands and displays the winner
        int playerSum = player.getHand().sum();
        int computerSum = computer.getHand().sum();

        if(playerSum > computerSum) {
            System.out.println("You had more points, you lose, sorry.");
        } else {
            System.out.println("You beat the computer, you win!");
        }
        System.out.println("Game over! Thanks for playing.");
    }


}
