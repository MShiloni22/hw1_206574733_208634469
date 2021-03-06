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

    public Card drawCard() {
        return this.gameDeck.removeTopCard();
    }

    public boolean outOfCards(){
        if (this.gameDeck.isEmpty()){
            if (this.winDeck.isEmpty()) return true;

            // If winDeck isn't empty, do the following (and eventually return true):
            this.winDeck.shuffle(); //shuffle winDeck
            this.gameDeck = new Deck(this.winDeck); //move winDeck to gameDeck
            //empty winDeck
            int winDeckSize = this.winDeck.deck.size();
            for (int i = 0; i < winDeckSize; i++) {
                this.winDeck.removeTopCard();
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
