public class Game {
    public static void main(String[] args) {
        Boneyard boneyard = new Boneyard();
        boneyard.populateBoneyard();
        System.out.println(boneyard.getDominos());
        Player player = new Player();
        Player player2 = new Player();
        System.out.println(player.getPlayerHand());
        System.out.println(player2.getPlayerHand());
        System.out.println(boneyard.getDominos());
    }
}
