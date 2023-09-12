public class Main {
    public static void main(String[] args) throws InterruptedException {
        Player s1 = new Player("Spieler 1",false);
        Player s2 = new Player("Spieler 2",false);

        GameManager newGame = new GameManager();
        newGame.addPlayer(s1);
        newGame.addPlayer(s2);

       

        newGame.init();
        newGame.gameLoop();
        
        
    }
}
