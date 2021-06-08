import java.util.*;

public class Deck {
    boolean toCreate;
    public ArrayList<Card> deck;
    public static final int DECK_SIZE = 52;
    public static final int SHAPES_AMOUNT = 4;
    public static final int NUMBERS_AMOUNT = 13;

    public boolean getToCreate() {
        return this.toCreate;
    }

    public void setToCreate(boolean bool) {
        this.toCreate = bool;
    }


    public void addCard(Card card) {
        deck.add(0, card);
    }

    public void addCardByIndex(int index, Card card) {
        deck.add(index, card);
    }


    public Card removeTopCard() {
        Card removed = new Card(deck.get(0)); //uses clone builder
        deck.remove(0);
        return removed;
    }

    boolean isEmpty() {
        return deck.isEmpty();
    }

    void shuffle() {
        for (int i = 0; i < 50; i++) {
            int rnd1 = Main.rnd.nextInt(deck.size());
            int rnd2 = Main.rnd.nextInt(deck.size());
            Card tmp = deck.get(rnd1); //uses clone builder
            deck.set(rnd1, deck.get(rnd2));
            deck.set(rnd2, tmp);
        }
    }

    public Deck(boolean toCreate) {
        this.deck = new ArrayList<>();
        this.toCreate = toCreate;
        if (this.toCreate) {
            for (Shape shape : Shape.values()) {
                for (int i = 1; i <= NUMBERS_AMOUNT; i++) {
                    deck.add(new Card(i, shape.getSymbol()));
                }
            }
        }
    }

    public Deck(Deck other) {
        this.toCreate = false;
        this.deck = new ArrayList<Card>();
        for (int i = 0; i < other.deck.size(); i++) {
            this.deck.add(i, other.deck.get(i));
        }
    }
}
