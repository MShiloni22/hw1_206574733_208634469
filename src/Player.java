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
}
