public class WarGame {
    Player player1;
    Player player2;
    Deck player1Deck;
    Deck player2Deck;

    public WarGame(String name1, String name2){
        int namesComparison = name1.compareTo(name2);
        if (namesComparison < 0){
            this.player1 = new Player(name1);
            this.player2 = new Player(name2);
        }
        else{
            this.player1 = new Player(name2);
            this.player2 = new Player(name1);
        }
        this.player1Deck = new Deck(false);
        this.player2Deck = new Deck(false);
    }

    public void initializeGame(){
        Deck beginningDeck = new Deck(true);
        beginningDeck.shuffle();
        int beginningDeckSize = beginningDeck.deck.size();
        for (int i = 0; i < beginningDeckSize/2; i++) {
            Card cardToPlayer1 = beginningDeck.removeTopCard();
            this.player1.gameDeck.addCard(cardToPlayer1);
            Card cardToPlayer2 = beginningDeck.removeTopCard();
            this.player1.gameDeck.addCard(cardToPlayer2);
        }
    }
}
