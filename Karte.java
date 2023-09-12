import java.util.*;


public class Karte {
    
    private static final List<String> allcards = new ArrayList<>();

    static {
        allcards.add("Karo 7");
        allcards.add("Karo 8");
        allcards.add("Karo 9");
        allcards.add("Karo 10");
        allcards.add("Karo Ober");
        allcards.add("Karo Dame");
        allcards.add("Karo König");
        allcards.add("Karo Ass");
        allcards.add("Herz 7");
        allcards.add("Herz 8");
        allcards.add("Herz 9");
        allcards.add("Herz 10");
        allcards.add("Herz Ober");
        allcards.add("Herz Dame");
        allcards.add("Herz König");
        allcards.add("Herz Ass");
        allcards.add("Pik 7");
        allcards.add("Pik 8");
        allcards.add("Pik 9");
        allcards.add("Pik 10");
        allcards.add("Pik Ober");
        allcards.add("Pik Dame");
        allcards.add("Pik König");
        allcards.add("Pik Ass");
        allcards.add("Kreuz 7");
        allcards.add("Kreuz 8");
        allcards.add("Kreuz 9");
        allcards.add("Kreuz 10");
        allcards.add("Kreuz Ober");
        allcards.add("Kreuz Dame");
        allcards.add("Kreuz König");
        allcards.add("Kreuz Ass");
    }

    private static final Map<String,Integer> farbvals = new HashMap<>();

    static{
        farbvals.put("Karo",1);
        farbvals.put("Herz",2);
        farbvals.put("Pik",3);
        farbvals.put("Kreuz",4);
    }

    private static final Map<String,Integer> nameval = new HashMap<>();

    static{
        nameval.put("7",7);
        nameval.put("8",8);
        nameval.put("9",9);
        nameval.put("10",10);
        nameval.put("Ober",11);
        nameval.put("Dame",12);
        nameval.put("König",13);
        nameval.put("Ass",14);
    }

    private String kartenName;
    private int wins;

    public Karte(String kartenName) {
        this.kartenName = kartenName;
        this.wins=0;
    }

    public int getWins(){
        return wins;
    }

    public void increaseWins(){
        wins+=1;
    }
    public String getKartenName() {
        return kartenName;
    }

    public int getFarbValue(){
        return farbvals.get(kartenName.split(" ")[0]);
    }

    public int getWertValue(){
        return nameval.get(kartenName.split(" ")[1]);
    }

    public boolean besserAls(Karte k,boolean isFarbe){
        if(isFarbe){
            if (this.getFarbValue()>k.getFarbValue()){
                    return true;
            }else if (this.getFarbValue()==k.getFarbValue()){
                    return false;
            }else{
                    return false;
            }
        }else{
            if (this.getWertValue()>k.getWertValue()){
                    return true;
            }else if (this.getWertValue()==k.getWertValue()){
                    return false;
            }else{
                    return false;
            }
        }
        
    }

    public static List<Karte> getGemischtenKartensatz(){

        List<Karte> gemischteKarten=new ArrayList<>();

        for (int i=0;i<32;i++){
            gemischteKarten.add(new Karte(allcards.get(i)));
        }

  
        Collections.shuffle(gemischteKarten);

        return gemischteKarten;

    }

    public String getName(){
        return kartenName;
    }

}
