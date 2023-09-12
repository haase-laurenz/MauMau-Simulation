public class Result {

    private Karte karte;
    private boolean isSuccess;
    private boolean isDraw;
    
    public Result(Karte karte, boolean isSuccess,boolean isDraw) {
        this.karte = karte;
        this.isSuccess = isSuccess;
        this.isDraw=isDraw;
    }

    public Karte getKarte() {
        return karte;
    }

    public boolean isSuccess() {
        return isSuccess;
    } 

    public boolean isDraw(){
        return isDraw;
    }
    
}
