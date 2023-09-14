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

    private static final Map<String,Double> cardRatings = new HashMap<>();

    static {
        cardRatings.put("Karo 7",0.109343284187474);
        cardRatings.put("Karo 8",0.167999628560662);
        cardRatings.put("Karo 9",0.290388948595961);
        cardRatings.put("Karo 10",0.436145699987406);
        cardRatings.put("Karo Ober",0.616032309492923);
        cardRatings.put("Karo Dame",0.856127615906367);
        cardRatings.put("Karo König",1.18222570798558);
        cardRatings.put("Karo Ass",1.65622964299699);
        cardRatings.put("Herz 7",0.361168876654572);
        cardRatings.put("Herz 8",0.364555710385577);
        cardRatings.put("Herz 9",0.373796718287335);
        cardRatings.put("Herz 10",0.460516131059573);
        cardRatings.put("Herz Ober",0.640402740565091);
        cardRatings.put("Herz Dame",0.880498046978532);
        cardRatings.put("Herz König",1.20659613905775);
        cardRatings.put("Herz Ass",1.68060007406916);
        cardRatings.put("Pik 7",0.740185012885494);
        cardRatings.put("Pik 8",0.7435718466165);
        cardRatings.put("Pik 9",0.752812854518257);
        cardRatings.put("Pik 10",0.776743524218871);
        cardRatings.put("Pik Ober",0.820149460324351);
        cardRatings.put("Pik Dame",0.979945865774481);
        cardRatings.put("Pik König",1.30604395785369);
        cardRatings.put("Pik Ass",1.78004789286511);
        cardRatings.put("Kreuz 7",1.45171968316331);
        cardRatings.put("Kreuz 8",1.45510651689431);
        cardRatings.put("Kreuz 9",1.46434752479607);
        cardRatings.put("Kreuz 10",1.48827819449668);
        cardRatings.put("Kreuz Ober",1.53168413060217);
        cardRatings.put("Kreuz Dame",1.61177902610634);
        cardRatings.put("Kreuz König",1.74103212274443);
        cardRatings.put("Kreuz Ass",2.07392511136898);
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

    public double getRating(){
        return cardRatings.get(kartenName);
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
