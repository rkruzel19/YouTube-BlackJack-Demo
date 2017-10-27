package blackjackdemo;

import java.util.Scanner;

public class BlackJack {

    public static void main(String[] args) {

        System.out.println("Welcome to YouTube BlackJack Demo");
        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        playingDeck.shuffle();

        Deck playerDeck = new Deck();
        Deck dealerDeck = new Deck();

        double playerMoney = 100.00;

        Scanner userInput = new Scanner(System.in);

        // Game Loop
        while (playerMoney > 0) {
            // Play on
            // Player bets
            System.out.println("You have $" + playerMoney + " How much would you like to bet?");
            double playerBet = userInput.nextDouble();
            if (playerBet > playerMoney) {
                System.out.println("You don't have enough money. Game Over.");
                break;
            }

            boolean endRound = false;

            //Start playing
            //Player gets two cards
            playerDeck.draw(playingDeck);
            playerDeck.draw(playingDeck);

            //Dealer gets two cards
            dealerDeck.draw(playingDeck);
            dealerDeck.draw(playingDeck);

            //Players action
            while (true) {
                System.out.println("Your hand");
                System.out.println("************");
                System.out.println(playerDeck.toString());
                System.out.println("************");
                System.out.println("Your hand is valued at: " + playerDeck.cardsValue());

                //Display dealer hand
                System.out.println("************");
                System.out.println("Dealer hand: " + dealerDeck.getCard(0).toString() + " and [Hidden]");
                System.out.println("************");

                //What does the player want to do
                System.out.println(("Would you like to (1) Hit or (2) Stand?"));
                int response = userInput.nextInt();

                //They Hit
                if (response == 1) {
                    playerDeck.draw(playingDeck);
                    System.out.println("You drew a: " + playerDeck.getCard(playerDeck.deckSize() - 1).toString());

                    if (playerDeck.cardsValue() > 21) {
                        System.out.println("Bust. Currently valued at :" + playerDeck.cardsValue());
                        playerMoney -= playerBet;
                        endRound = true;
                        break;
                    }

                } else if (response == 2) {
                    break;
                }


            }

            //Reveal Dealer Cards
            System.out.println("Dealer Cards: " + dealerDeck.toString());
            //See if Dealer has more points then player
            if ((dealerDeck.cardsValue() > playerDeck.cardsValue()) && endRound == false) {
                System.out.println("Dealer beats you.");
                playerMoney -= playerBet;
                endRound = true;
            }
            //Dealer draws at 16, stands at 17
            while ((dealerDeck.cardsValue() < 17) && endRound == false) {
                dealerDeck.draw(playingDeck);
                System.out.println("Dealer Draws: " + dealerDeck.getCard(dealerDeck.deckSize() - 1).toString());
            }
            //Display total value for dealer
            System.out.println("Dealer's Hand is valued at: " + dealerDeck.cardsValue());
            //Determine if dealer busted
            if ((dealerDeck.cardsValue() > 21) && endRound == false) {
                System.out.println("Dealer busts!!   YOU WIN!!!");
                playerMoney += playerBet;
                endRound = true;
            }

            //Determine push
            if ((playerDeck.cardsValue() == dealerDeck.cardsValue()) && endRound == false) {
                System.out.println("Push");
                endRound = true;
            }

            if ((playerDeck.cardsValue() > dealerDeck.cardsValue()) && endRound == false) {
                System.out.println("You win the Hand!!");
                playerMoney += playerBet;
                endRound = true;
            }

            else if(endRound == false) {
                System.out.println("You lose the hand.");
                playerMoney -= playerBet;
                endRound = true;
            }

            playerDeck.moveAllToDeck(playingDeck);
            dealerDeck.moveAllToDeck(playingDeck);
            System.out.println("End of Hand.");


        }

        System.out.println("Game Over. You are out of money.");

    }
}
