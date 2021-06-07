enum Shape{
    SPADE("♠"),
    DIAMOND("♦"),
    CLUB("♣"),
    HEART("♥");

    private final String symbol;

    Shape (String symbol){
        this.symbol = symbol;
    }

    public String getSymbol(){
        return this.symbol;
    }
}