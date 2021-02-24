import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        ArrayList<String> rowOne = new ArrayList<>();
        ArrayList<String> rowTwo = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        Boneyard boneyard = new Boneyard();
        boneyard.populateBoneyard();
        Board board = new Board();
        Player player = new Player(boneyard);
        Computer computer = new Computer(boneyard);
        boolean madeMove = false;
        boolean shouldBreak = false;
        boolean placedLeft = false;
        boolean firstTurn = true;
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
                        if (!firstTurn) {
                            System.out.println("Left or right? (l/r)");
                            sideChoice = sc.next();
                            if(sideChoice.equals("l")) {
                                placedLeft = true;
                            }
                        }
                        System.out.println("Flip domino? (y/n)");
                        String flipChoice = sc.next();
                        try {
                            player.placeDomino(board, dominoIndex, flipChoice, sideChoice);
                            madeMove = true;
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid move, try again or draw from the boneyard.");
                        }
                    case "d" :
                        player.drawBoneyard(boneyard);
                        System.out.println("Boneyard has " + boneyard.getBoneyard().size() + " dominoes");
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
                } else {
                    rowOne.add(lastPlaced.toString());
                }

                System.out.println("Computer turn");
                computer.placeDomino(board);

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
