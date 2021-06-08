public class WarGame {
    Player player1;
    Player player2;
    Deck player1Deck;
    Deck player2Deck;
    static final int PLAYER_1_WON = 1;
    static final int PLAYER_2_WON = -1;
    static final int WAR_COUNTER = 2;

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
        //System.out.println("entered init methode!");
        Deck beginningDeck = new Deck(true);
        //System.out.println("new Deck created!");
        beginningDeck.shuffle();
        System.out.println("Deck shuffled!");
        for (int i=0 ; i<Deck.DECK_SIZE; i++)
            System.out.println(beginningDeck.deck.get(i).toString());  //wanted to see if deck is really shuffled
        //int beginningDeckSize = beginningDeck.deck.size();
        int beginningDeckSize = Deck.DECK_SIZE;
        //System.out.println("size is" + beginningDeckSize);
        for (int i = 0; i < beginningDeckSize/2; i++) {
            // also need to check if problem not caused due to it is a complicated type
            //Card cardToPlayer1 = new Card(beginningDeck.removeTopCard());
            this.player1.gameDeck.addCard(beginningDeck.removeTopCard());
            //Card cardToPlayer2 = new Card(beginningDeck.removeTopCard());
            this.player2.gameDeck.addCard(beginningDeck.removeTopCard());

        }
        //here the problem starts //almost fixed it
        System.out.println("Player 1's deck:");
        for (int j=0 ; j<Deck.DECK_SIZE/2; j++)
            System.out.println(this.player1.gameDeck.deck.get(j).toString());  //wanted to see if deck is really seperated
        System.out.println("Player 2's deck:");
        for (int j=0 ; j<Deck.DECK_SIZE/2; j++)
            System.out.println(this.player2.gameDeck.deck.get(j).toString());  //wanted to see if deck is really seperated

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
                    "-------------------------");
            boolean isRoundFinished = false;
            while (!isRoundFinished){
                drawAndDeclare(this.player1, this.player1Deck, this.player1 + " drew ");
                drawAndDeclare(this.player2, this.player2Deck, this.player2 + " drew ");

                // The comparison, after each player drew a card
                // i think here we have wrong comparison. you already drew a card. you now use the next card, no?
                int cardCompare = player1Deck.deck.get(0).compare(player2Deck.deck.get(0));
                int tempDeckSize = player1Deck.deck.size();
                if (cardCompare == PLAYER_1_WON){
                    cardsCollector(this.player1, tempDeckSize);
                    isRoundFinished = true;
                }
                else if (cardCompare == PLAYER_2_WON){
                    cardsCollector(this.player2, tempDeckSize);
                    isRoundFinished = true;
                }
                else{
                    // Checks if one of the players lost all his cards, before drawing again
                    if (this.player1.outOfCards()) return this.player2.getName();
                    if (this.player2.outOfCards()) return this.player1.getName();

                    System.out.println("Starting a war...");
                    for (int i = 0; i < WAR_COUNTER; i++) {
                        // Checks if one of the players lost all his cards, before drawing again
                        if (this.player1.outOfCards()) return this.player2.getName();
                        if (this.player2.outOfCards()) return this.player1.getName();

                        drawAndDeclare(this.player1, this.player1Deck, this.player1 + " drew a war card");
                        drawAndDeclare(this.player2, this.player2Deck, this.player2 + " drew a war card");
                    }

                    // Checks if one of the players lost all his cards, before drawing again
                    if (this.player1.outOfCards()) return this.player2.getName();
                    if (this.player2.outOfCards()) return this.player1.getName();
                }
            }
        }
    }

    // Collects the cards to the winner's wins deck, in the right order & declaring who won the round
    public void cardsCollector(Player winner, int tempDeckSize){
        for (int i = 0; i < tempDeckSize; i++){
            Card cardFromPlayer2 = player2Deck.removeTopCard();
            Card cardFromPlayer1 = player1Deck.removeTopCard();
            winner.winDeck.addCard(cardFromPlayer2);
            winner.winDeck.addCard(cardFromPlayer1);
        }
        System.out.println(winner + " won");
    }

    // Combine the draw action and the following declaration, according to relevant stage in the game (war/regular)
    public void drawAndDeclare(Player currentPlayer, Deck currentPlayerDeck, String message){
        currentPlayerDeck.addCard(currentPlayer.drawCard());
        System.out.println(message + currentPlayerDeck.deck.get(0));
    }
}
