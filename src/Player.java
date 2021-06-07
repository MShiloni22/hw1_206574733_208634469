import java.util.*;

public class Player {
    private final String name;
    Deck gameDeck;
    Deck winDeck;

    public Player(String name){
        this.name = name;
        this.gameDeck = new Deck(false);
        this.winDeck = new Deck(false);
    }

    public String getName(){return this.name;}

    public Card drawCard(){
        return this.gameDeck.removeTopCard();
    }

    public boolean outOfCards(){
        return (this.gameDeck.isEmpty() && this.winDeck.isEmpty());
    }

    @Override
    public String toString(){
        return this.name;
    }
}
