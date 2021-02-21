import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Boneyard boneyard = new Boneyard();
        boneyard.populateBoneyard();
        Board board = new Board();
        Player player = new Player(boneyard);
        Computer computer = new Computer(boneyard);
        boolean madeMove = false;
        boolean shouldBreak = false;
        boolean placedLeft = false;
        String offset = "    ";
        System.out.println("Boneyard has " + boneyard.getBoneyard().size() + " dominoes");
        ArrayList<String> rowOne = new ArrayList<>();
        ArrayList<String> rowTwo = new ArrayList<>();

        do {
            while(!madeMove) {
                if(shouldBreak) {
                    break;
                }
                System.out.println("Tray: " + player.getPlayerHand());
                System.out.println("Human turn");
                System.out.println("[p] Play Domino" +
                        "\n[d] Draw from boneyard" +
                        "\n[q] Quit");
                String playChoice = sc.next();
                switch (playChoice) {
                    case "p" :
                        System.out.println("Which domino would you like to place?");
                        int dominoIndex = sc.nextInt();
                        System.out.println("Left or right? (l/r)");
                        String sideChoice = sc.next();
                        if(sideChoice.equals("l")) {
                            placedLeft = true;
                        }
                        System.out.println("Flip domino? (y/n)");
                        String flipChoice = sc.next();
                        player.placeDomino(board, dominoIndex, flipChoice, sideChoice);
                        madeMove = true;
                        break;
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
                    offset += "         ";
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

                System.out.println("Row: " + board.getRow().toString());
            }
            if (boneyard.getBoneyard().isEmpty()) {
                break;
            }
        } while(!player.getPlayerHand().isEmpty() ||
                !computer.getHand().isEmpty());
        System.out.println("Game over! Thanks for playing.");
    }
}
