import java.util.*;

    	
        // Jetzt können Sie auf das Dictionary optimal_playstyles zugreifen.
    


public class GameManager{

    
    private int gamePauseInMilliseconds=1000;
    private List<Karte> Stapel;
    private List<Player> Mitspieler;
    private int activePlayer;
    private int roundInCurrentGame;
    private int gamesPlayed=0;
    private int totalRounds=0;

    public GameManager(){
        

        this.activePlayer=0;
        
        this.Mitspieler = new ArrayList<>();
    }

    public void addPlayer(Player p){

        Mitspieler.add(p);
    }

    public Player getAktuellerSpieler() {
        return Mitspieler.get(activePlayer);
    }

    public Player naechsterSpieler(){
        activePlayer=(activePlayer+1)%2;
        return Mitspieler.get(activePlayer);
    }


    public void init(){
        roundInCurrentGame=0;
        Stapel=Karte.getGemischtenKartensatz();
        int index = activePlayer;
        for (Player spieler:Mitspieler){
            spieler.removeAllCards();
        }

        while (!Stapel.isEmpty()) {
            Player nextSpieler =Mitspieler.get(index);
            index++;
            if (index >= Mitspieler.size()) {
                index = 0;
            }
            nextSpieler.aufnehmen(Stapel.remove(Stapel.size() - 1));
        }
    }

    public void gameLoop() throws InterruptedException {
        for (int i=0;i<5;i++){
            System.out.println("");
        }
        System.out.println("--------------NEW GAME--------------");
        for (int i=0;i<5;i++){
            System.out.println("");
        }


        
        

        gamesPlayed+=1;



        while(true) {
            
            roundInCurrentGame+=1;

            Player active = getAktuellerSpieler();
            Player passive = naechsterSpieler();
            
            Result offense=active.Offense();

            Result defense=passive.Defense(offense);
            
            if (offense.isSuccess()){
                System.out.println(active.getName()+" played: " +offense.getKarte().getName() +" FARBE");
            }else{
                System.out.println(active.getName()+" played: " +offense.getKarte().getName() +" WERT");
            }

            
            System.out.println(passive.getName()+" played: " +defense.getKarte().getName());

            List<Karte> possibleDraw=new ArrayList<>();

            while (defense.isDraw()){
                System.out.println("STECHEN");
                possibleDraw.add(offense.getKarte());
                possibleDraw.add(defense.getKarte());
                
                if (checkWin(active, passive)!=0){
                    this.init();
                    Thread.sleep(gamePauseInMilliseconds);
                    this.gameLoop();
                }
                
                possibleDraw.add(active.ablegen());
                possibleDraw.add(passive.ablegen());
                
                if (checkWin(active, passive)!=0){
                    this.init();
                    Thread.sleep(gamePauseInMilliseconds);
                    this.gameLoop();
                    
                    
                }

                offense=active.drawOffense(offense.isSuccess());
                defense=passive.Defense(offense);

                if (offense.isSuccess()){
                    System.out.println(active.getName()+" played: " +offense.getKarte().getName() +" FARBE");
                }else{
                    System.out.println(active.getName()+" played: " +offense.getKarte().getName() +" WERT");
                }
                System.out.println(passive.getName()+" played: " +defense.getKarte().getName());
            }
            
            if (!defense.isSuccess()){
                for (Karte card : possibleDraw){
                    active.aufnehmen(card);
                }
                offense.getKarte().increaseWins();
                active.aufnehmen(offense.getKarte());
                active.aufnehmen(defense.getKarte());
                
                naechsterSpieler();
                System.out.println("OFFENSE WON");
            }else{
                for (Karte card : possibleDraw){
                    passive.aufnehmen(card);
                }
                defense.getKarte().increaseWins();
                passive.aufnehmen(offense.getKarte());
                passive.aufnehmen(defense.getKarte());
                System.out.println("DEFENDS WON");
            }

            for (Player player : Mitspieler){
                System.out.println(player.getName()+": "+player.getCardCount());
                System.out.println(player.getHand()+" - Value: "+player.getHandValue());
            }
            
            

            System.out.println("");

            if (checkWin(active, passive)!=0){
                this.init();
                Thread.sleep(gamePauseInMilliseconds);
                this.gameLoop();
            }


        }

    }

    public int checkWin(Player p1,Player p2){
        if (p1.keineKartenMehr()){
            totalRounds+=roundInCurrentGame;
            System.out.println(p2.getName()+" WON!"+" - "+roundInCurrentGame+" rounds");
            System.out.println(p2.getName()+" MVP: "+p2.getMVP().getName()+" - "+p1.getMVP().getWins()+" Wins");

            return 1;
        }
        if (p2.keineKartenMehr()){
            totalRounds+=roundInCurrentGame;
            System.out.println(p1.getName()+" WON!"+" - "+roundInCurrentGame+" rounds");
            System.out.println(p1.getName()+" MVP: "+p1.getMVP().getName()+" - "+p1.getMVP().getWins()+" Wins");
       
            return 2;
        }

        return 0;
    }




}


