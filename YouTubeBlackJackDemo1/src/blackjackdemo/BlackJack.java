package blackjackdemo;

public class BlackJack {

    public static void main(String[] args) {

        System.out.println("Welcome to YouTube BlackJack Demo");
        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        playingDeck.shuffle();

        System.out.println(playingDeck);

    }
}
