import java.util.*;

public class Player {
    
    private String name;
    private boolean isPlayer;
    
    private List<Karte> kartenAufDerHand = new ArrayList<>();
    private static final Map<String,String> optimal_playstyles = new HashMap<>();

    static{
        optimal_playstyles.put("Karo 7", "Farbe");
        optimal_playstyles.put("Karo 8", "Wert");
        optimal_playstyles.put("Karo 9", "Wert");
        optimal_playstyles.put("Karo 10", "Wert");
        optimal_playstyles.put("Karo Ober", "Wert");
        optimal_playstyles.put("Karo Dame", "Wert");
        optimal_playstyles.put("Karo König", "Wert");
        optimal_playstyles.put("Karo Ass", "Wert");

        optimal_playstyles.put("Herz 7", "Farbe");
        optimal_playstyles.put("Herz 8", "Farbe");
        optimal_playstyles.put("Herz 9", "Farbe");
        optimal_playstyles.put("Herz 10", "Wert");
        optimal_playstyles.put("Herz Ober", "Wert");
        optimal_playstyles.put("Herz Dame", "Wert");
        optimal_playstyles.put("Herz König", "Wert");
        optimal_playstyles.put("Herz Ass", "Wert");

        optimal_playstyles.put("Pik 7", "Farbe");
        optimal_playstyles.put("Pik 8", "Farbe");
        optimal_playstyles.put("Pik 9", "Farbe");
        optimal_playstyles.put("Pik 10", "Farbe");
        optimal_playstyles.put("Pik Ober", "Farbe");
        optimal_playstyles.put("Pik Dame", "Wert");
        optimal_playstyles.put("Pik König", "Wert");
        optimal_playstyles.put("Pik Ass", "Wert");

        optimal_playstyles.put("Kreuz 7", "Farbe");
        optimal_playstyles.put("Kreuz 8", "Farbe");
        optimal_playstyles.put("Kreuz 9", "Farbe");
        optimal_playstyles.put("Kreuz 10", "Farbe");
        optimal_playstyles.put("Kreuz Ober", "Farbe");
        optimal_playstyles.put("Kreuz Dame", "Farbe");
        optimal_playstyles.put("Kreuz König", "Farbe");
        optimal_playstyles.put("Kreuz Ass", "Wert");
    }
    
    public Player(String name,boolean isPlayer){
        this.name=name;
        this.isPlayer=isPlayer;
    }

    public Karte getMVP(){
        Karte mvp=kartenAufDerHand.get(0);
        for (Karte card : kartenAufDerHand){
            if (card.getWins()>mvp.getWins()){
                mvp=card;
            }
        }
        return mvp;
    }
    public List<String> getHand(){
        List<String> handToString=new ArrayList<>();
        for (Karte card:kartenAufDerHand){
            handToString.add(card.getName());
        }
        return handToString;
    }
    public int getCardCount(){
        return kartenAufDerHand.size();
    }

    public void aufnehmen(Karte karte){
        kartenAufDerHand.add(0,karte);
    }

    public Karte ablegen(){
        return kartenAufDerHand.remove(kartenAufDerHand.size() - 1);
    }

    public void removeAllCards(){
        kartenAufDerHand.clear();
    }

    public boolean keineKartenMehr(){
        if (kartenAufDerHand.size()<3){
            return true;
        }else{
            return false;
        }
    }

    public Result drawOffense(boolean isFarbe){
        Karte karte = kartenAufDerHand.remove(kartenAufDerHand.size() - 1);
        return new Result(karte,isFarbe,false);
    }

    public Result Offense() {
        Karte karte = kartenAufDerHand.remove(kartenAufDerHand.size() - 1);
        boolean isSuccess; // true, wenn Farbangriff, false bei Wertangriff

        if ("Farbe".equals(optimal_playstyles.get(karte.getName()))) {
            isSuccess=true;
        }else{
            isSuccess=false;
        }
        return new Result(karte, isSuccess,false);
    }

    public Result Defense(Result offense) {
        Karte offenseCard=offense.getKarte();
        Karte defenseCard;
        if (kartenAufDerHand.size()==3){
            
            int bestindex=0;
            
            for (int i=1;i<kartenAufDerHand.size();i++){
                Karte card=kartenAufDerHand.get(i);
                if (card.besserAls(offenseCard,offense.isSuccess())){
                    bestindex=i;
                }
            }

            defenseCard=kartenAufDerHand.remove(bestindex);
        }else{
            defenseCard=kartenAufDerHand.remove(kartenAufDerHand.size() - 1);
        }
        

        if (offense.isSuccess()){
            if (defenseCard.getFarbValue()>offenseCard.getFarbValue()){
                    return new Result(defenseCard, true,false);
            }else if (defenseCard.getFarbValue()==offenseCard.getFarbValue()){
                    return new Result(defenseCard, false,true);
            }else{
                    return new Result(defenseCard, false,false);
            }
        }else{
            if (defenseCard.getWertValue()>offenseCard.getWertValue()){
                    return new Result(defenseCard, true,false);
            }else if (defenseCard.getWertValue()==offenseCard.getWertValue()){
                    return new Result(defenseCard, false,true);
            }else{
                    return new Result(defenseCard, false,false);
            }
        }

        
    }

    public String getName(){
        return name;
    }

    public double getHandValue(){
        double sum=0;
        for (Karte card : kartenAufDerHand){
            sum+=card.getRating();
        }
        return sum/kartenAufDerHand.size();
        
    }
}



