package Logic;

import Board.Board;
import MonopolyJunior.ChanceCard;
import MonopolyJunior.Deck;
import MonopolyJunior.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActionHandlerTest {

    GameController gameController;
    ActionHandler ah;
    PositionHandler positionHandler;
    Board board;
    Deck deck;
    Player[] players;
    ChanceCard currentCard;
    Player currentPlayer;
    Player player2, player3;

    final int BOARD_SIZE = 32;

    @BeforeEach
    void setup() {
        board = new Board();
        deck = new Deck();
        players = new Player[2];

        gameController = new GameController();
        positionHandler = new PositionHandler(players, BOARD_SIZE);
        ah = new ActionHandler(gameController, board, positionHandler);

        currentPlayer = new Player("player1");
        player2 = new Player("player2");
        player3 = new Player("player3");
    }

    //test regarding free ticket booth chance cards.
    //in this test, the amusement with the color magenta is used, witch have position 12 & 13 on the board.
    @Test
    void drawAFreeTicketBoothCard() { //where neither are owned
        deck.setDrawCardCount(12); //draw the card, "place a ticket booth on magenta"
        currentCard = deck.getCard();
        ah.doChanceCard(currentCard);
    }

    @Test
    void drawAFreeTicketBoothCard2(){ //if the first are owned and the second isn't
        deck.setDrawCardCount(12); //draw the card, "place a ticket booth on magenta"
        board.addBooth(player2,12);
        currentCard = deck.getCard();
        ah.doChanceCard(currentCard);
    }

    @Test
    void drawAFreeTicketBoothCard3(){ //if the second are owned and the first isn't
        deck.setDrawCardCount(12); //draw the card, "place a ticket booth on magenta"
        board.addBooth(player2,13);
        currentCard = deck.getCard();
        ah.doChanceCard(currentCard);
    }

    @Test
    void drawAFreeTicketBoothCard4(){ //if both are owned, but there isn't monopoly
        deck.setDrawCardCount(12); //draw the card, "place a ticket booth on magenta"
        board.addBooth(player2,12);
        board.addBooth(player3,13);
        currentCard = deck.getCard();
        ah.doChanceCard(currentCard);
    }

    @Test
    void drawAFreeTicketBoothCard5(){ //if monopoly eksisk
        deck.setDrawCardCount(12); //draw the card, "place a ticket booth on magenta"
        board.addBooth(player2,12);
        board.addBooth(player2,13);
        currentCard = deck.getCard();
        ah.doChanceCard(currentCard);
    }

    //test regarding chanceCard that make the player move.
    @Test
    void drawAMoveChanceCard(){
        ah.setCurrentPlayer(currentPlayer);
        deck.setDrawCardCount(3);
        ah.doChanceCard(deck.getCard());

        int expected = 9;
        int actual = currentPlayer.getPosition();
        assertEquals(expected, actual);

    }
}


