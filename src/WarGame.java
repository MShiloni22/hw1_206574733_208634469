public class WarGame {
    Player player1;
    Player player2;
    Deck player1Deck;
    Deck player2Deck;
    static final int PLAYER_1_WON = 1;
    static final int PLAYER_2_WON = -1;

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
        System.out.println("Initializing the game...");
    }

    public String start(){
        this.initializeGame();
        int roundsCounter = 0;
        while (true){
            if (this.player1.outOfCards()) return this.player2.getName();
            if (this.player2.outOfCards()) return this.player1.getName();
            System.out.println("------------------------- Round number " + ++roundsCounter +
                    "-------------------------");
            Card player1Card = this.player1.drawCard();
            System.out.println(this.player1 + "drew" + player1Card);
            Card player2Card = this.player1.drawCard();
            System.out.println(this.player1 + "drew" + player2Card);
            int cardCompare = player1Card.compare(player2Card);
            if (cardCompare == PLAYER_1_WON){

            }
            else if (cardCompare == PLAYER_2_WON){

            }
            else{

            }
        }
    }
}
