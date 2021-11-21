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

    final int BOARD_SIZE = 32;

    @BeforeEach
    void setup() {
        board = new Board();
        deck = new Deck();
        players = new Player[2];

        gameController = new GameController();
        positionHandler = new PositionHandler(players, BOARD_SIZE);
        ah = new ActionHandler(gameController, board, positionHandler);

        Player currentPlayer = new Player("player1");
    }

    @Test
    void drawAFreeTicketBoothCard() { //where neither are owned
        deck.setDrawCardCount(12);
        currentCard = deck.getCard();
        ah.doChanceCard(currentCard);

    }
}


