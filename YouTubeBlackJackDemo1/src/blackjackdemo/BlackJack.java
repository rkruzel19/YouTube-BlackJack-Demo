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
            System.out.println("You have $" + playerMoney + ". How much would you like to bet?");
            double playerBet = userInput.nextDouble();
            if (playerBet > playerMoney) {
                System.out.println("You don't have enough money. Game Over.");
                break;
            }

            //Start playing
            //Player gets two cards
            playerDeck.draw(playingDeck);
            playerDeck.draw(playingDeck);

            //Dealer gets two cards
            dealerDeck.draw(playingDeck);
            dealerDeck.draw(playingDeck);

            while (true) {
                System.out.println("Your hand");
                System.out.print(playerDeck.toString());
            }






        }

        System.out.println("Game Over. You are out of money.");

    }
}
