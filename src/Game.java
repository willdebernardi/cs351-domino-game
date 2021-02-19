import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Boneyard boneyard = new Boneyard();
        boneyard.populateBoneyard();
        Board board = new Board();
        Player player = new Player(boneyard);
        System.out.println("Boneyard: " + boneyard.getBoneyard());

        do {
            System.out.println("Hand: " + player.getPlayerHand());
            System.out.println("Which domino would you like to place?");
            int dominoIndex = sc.nextInt();
            System.out.println("Which row?");
            int rowChoice = sc.nextInt();
            System.out.println("Flip domino? (y/n)");
            String flipChoice = sc.next();
            player.placeDomino(board, dominoIndex, rowChoice, flipChoice);

            System.out.println("Row one: " + board.rowOneToString());
            System.out.println("Row two:     " + board.rowTwoToString());
        } while(!player.getPlayerHand().isEmpty());

        System.out.println("Game over! Thanks for playing.");
    }
}
