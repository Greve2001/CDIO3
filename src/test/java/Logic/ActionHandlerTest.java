package Logic;

import Board.*;
import MonopolyJunior.ChanceCard;
import MonopolyJunior.Deck;
import MonopolyJunior.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActionHandlerTest {

    GameController gameController;
    ActionHandler actionHandler;
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
        actionHandler = new ActionHandler(gameController, board, positionHandler);

        currentPlayer = new Player("player1");
        player2 = new Player("player2");
        player3 = new Player("player3");
    }

    //test regarding free ticket booth chance cards.
    //in this test, the amusement with the color magenta is used, witch have position 12 & 13 on the board.
    @Test
    void drawAFreeTicketBoothCard() { //where neither are owned
        currentCard = new ChanceCard("Free ticket booth", "magenta"); //the chanceCard we are testing
        currentPlayer.setPosition(2);

        Amusement amusement1 = (Amusement) board.getSquare(12); //Amusement that match the color
        Amusement amusement2 = (Amusement) board.getSquare(13); //Amusement that match the color

        currentPlayer.setBooths(10); //to ensure that the player have booths to place on the board
        actionHandler.doChanceCard(currentCard, currentPlayer);

        assertEquals(9, currentPlayer.getBoothsOnHand());
        assertEquals(currentPlayer, amusement1.getBoothOwner());
        assertNull(amusement2.getBoothOwner());
        System.out.println(currentPlayer.getPosition());
        //assertEquals(0, currentPlayer.getBalance());
    }

    @Test
    void drawAFreeTicketBoothCard2(){ //if the first are owned and the second isn't
        board.addBooth(player2,12);
        //currentCard = deck.getCard();
        actionHandler.doChanceCard(currentCard, currentPlayer);
    }

    @Test
    void drawAFreeTicketBoothCard3(){ //if the second are owned and the first isn't
        board.addBooth(player2,13);
        //currentCard = deck.getCard();
        actionHandler.doChanceCard(currentCard, currentPlayer);
    }

    @Test
    void drawAFreeTicketBoothCard4(){ //if both are owned, but there isn't monopoly
        board.addBooth(player2,12);
        board.addBooth(player3,13);
        //currentCard = deck.getCard();
        actionHandler.doChanceCard(currentCard, currentPlayer);
    }

    @Test
    void drawAFreeTicketBoothCard5(){ //if monopoly eksisk
        board.addBooth(player2,12);
        board.addBooth(player2,13);
        //currentCard = deck.getCard();
        actionHandler.doChanceCard(currentCard, currentPlayer);
    }

    //test regarding chanceCard that make the player move.
    @Test
    void drawAMoveChanceCard(){
        //actionHandler.doChanceCard(deck.getCard());

        int expected = 9;
        int actual = currentPlayer.getPosition();
        assertEquals(expected, actual);

    }
}


