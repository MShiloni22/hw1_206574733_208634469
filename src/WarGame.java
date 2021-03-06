import java.util.Collections;

public class WarGame {
    Player player1;
    Player player2;
    Deck player1Deck;
    Deck player2Deck;
    static final int PLAYER_1_WON = 1;
    static final int PLAYER_2_WON = -1;
    static final int WAR_COUNTER = 2;
    private boolean isWar; // Will help determine if there is a war going on or not

    public boolean getIsWar(){return this.isWar;}
    public void setIsWar(boolean parameter){this.isWar = parameter;}

    public WarGame(String name1, String name2){
        // Determine who is the first and the second player by a lexicographic comparison
        int namesComparison = name1.compareTo(name2);
        if (namesComparison < 0){
            this.player1 = new Player(name1);
            this.player2 = new Player(name2);
        }
        else{
            this.player1 = new Player(name2);
            this.player2 = new Player(name1);
        }
        // Create a special deck for placing the cards after drawing them throughout the game
        this.player1Deck = new Deck(false);
        this.player2Deck = new Deck(false);
    }

    public void initializeGame(){
        Deck beginningDeck = new Deck(true);
        beginningDeck.shuffle();
        Collections.reverse(beginningDeck.deck); // In order to split the cards according to expected
        int beginningDeckSize = Deck.DECK_SIZE;
        for (int i = 0; i < beginningDeckSize/2; i++) {
            this.player2.gameDeck.addCard(beginningDeck.removeTopCard());
            this.player1.gameDeck.addCard(beginningDeck.removeTopCard());

        }
        // In order to split the cards according to expected:
        Collections.reverse(this.player1.gameDeck.deck);
        Collections.reverse(this.player2.gameDeck.deck);
        System.out.println("Initializing the game...");
    }

    public String start(){
        this.initializeGame();
        int roundsCounter = 0;
        while (true){
            // Checks if one of the players lost all his cards
            if (this.player1.outOfCards()) return this.player2.getName();
            if (this.player2.outOfCards()) return this.player1.getName();

            // Beginning a new round
            System.out.println("------------------------- Round number " + ++roundsCounter +
                    " -------------------------");
            boolean isRoundFinished = false;
            while (!isRoundFinished){
                drawAndDeclare(this.player1, this.player1Deck, this.player1 + " drew ", false);
                drawAndDeclare(this.player2, this.player2Deck, this.player2 + " drew ", false);

                // The comparison, after each player drew a card
                int cardCompare = player1Deck.deck.get(player1Deck.deck.size()-1).
                        compare(player2Deck.deck.get(player2Deck.deck.size()-1));
                int tempDeckSize = player1Deck.deck.size();
                if (cardCompare == PLAYER_1_WON){
                    cardsCollector(this.player1, tempDeckSize, this.getIsWar());
                    isRoundFinished = true;
                }
                else if (cardCompare == PLAYER_2_WON){
                    cardsCollector(this.player2, tempDeckSize, this.getIsWar());
                    isRoundFinished = true;
                }
                else{
                    // Checks if one of the players lost all his cards, before drawing again
                    if (this.player1.outOfCards()) return this.player2.getName();
                    if (this.player2.outOfCards()) return this.player1.getName();

                    System.out.println("Starting a war...");
                    this.setIsWar(true);
                    for (int i = 0; i < WAR_COUNTER; i++) {
                        // Checks if one of the players lost all his cards, before drawing again
                        if (this.player1.outOfCards()) return this.player2.getName();
                        if (this.player2.outOfCards()) return this.player1.getName();

                        drawAndDeclare(this.player1, this.player1Deck, this.player1 + " drew a war card",
                                this.getIsWar());
                        drawAndDeclare(this.player2, this.player2Deck, this.player2 + " drew a war card",
                                this.getIsWar());
                    }
                    // Checks if one of the players lost all his cards, before drawing again
                    if (this.player1.outOfCards()) return this.player2.getName();
                    if (this.player2.outOfCards()) return this.player1.getName();
                }
            }
        }
    }

    // Collects the cards to the winner's wins deck, in the right order & declaring who won the round
    public void cardsCollector(Player winner, int tempDeckSize, boolean isWar){
        for (int i = 0; i < tempDeckSize; i++){
            Card cardFromPlayer2 = player2Deck.removeTopCard();
            Card cardFromPlayer1 = player1Deck.removeTopCard();
            winner.winDeck.addCard(cardFromPlayer2);
            winner.winDeck.addCard(cardFromPlayer1);
        }
        if (isWar){
            System.out.println(winner + " won the war");
            this.setIsWar(false);
        }
        else{
            System.out.println(winner + " won");
        }

    }

    // Combine the draw action and the following declaration, according to relevant stage in the game (war/regular)
    public void drawAndDeclare(Player currentPlayer, Deck currentPlayerDeck, String message, boolean isWar){
        currentPlayerDeck.addCard(currentPlayer.drawCard());
        if (isWar){
            System.out.println(message);
            return;
        }
        System.out.println(message + currentPlayerDeck.deck.get(currentPlayerDeck.deck.size()-1));
    }

}