import java.util.*;

public class Deck {
    boolean toCreate;
    ArrayList<Card> deck;
    public static final int DECK_SIZE = 52;
    public static final int SHAPES_AMOUNT = 4;
    public static final int NUMBERS_AMOUNT = 13;

    public boolean getToCreate() { return this.toCreate; }
    public void setToCreate(boolean bool) { this.toCreate = bool; }


    public void addCard(Card card){
        deck.add(0, card);
    }

    public Card removeTopCard(){
        Card removed = (deck.get(0)); //uses clone builder
        deck.remove(0);
        return  removed;
    }

    boolean isEmpty(){
        return deck.isEmpty();
    }

    void shuffle(){
        for (int i=0; i<50; i++){
            Random rnd = new Random(); // object of class Random
            int rnd1 = rnd.nextInt(DECK_SIZE);
            int rnd2 = rnd.nextInt(DECK_SIZE);
            Card tmp = new Card(deck.get(rnd1)); //uses clone builder
            deck.set(rnd1,deck.get(rnd2));
            deck.set(rnd2,tmp);
        }
    }

    public Deck (boolean toCreate) {
        this.deck = new ArrayList<>();
        this.toCreate = toCreate;
        if (this.toCreate) {
            for (Shape shape : Shape.values()) {
                for (int j = 0; j < SHAPES_AMOUNT; j++) {
                    for (int i = 1; i < NUMBERS_AMOUNT; i++) {
                        deck.add(new Card(i, shape.getSymbol()));
                    }
                }
            }
        }
    }

}
