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

        Amusement amusement1 = (Amusement) board.getSquare(12); //Amusement that match the color
        Amusement amusement2 = (Amusement) board.getSquare(13); //Amusement that match the color

        currentPlayer.setBooths(10); //to ensure that the player have booths to place on the board
        actionHandler.doChanceCard(currentCard, currentPlayer);

        assertEquals(9, currentPlayer.getBoothsOnHand());
        assertEquals(currentPlayer, amusement1.getBoothOwner());
        assertNull(amusement2.getBoothOwner());
    }

    @Test
    void drawAFreeTicketBoothCard2(){ //if the first are owned and the second isn't
        currentCard = new ChanceCard("Free ticket booth", "magenta"); //the chanceCard we are testing

        Amusement amusement1 = (Amusement) board.getSquare(12); //Amusement that match the color
        Amusement amusement2 = (Amusement) board.getSquare(13); //Amusement that match the color

        amusement1.setBoothOwner(player2);

        currentPlayer.setBooths(10); //to ensure that the player have booths to place on the board
        actionHandler.doChanceCard(currentCard, currentPlayer);

        assertEquals(9, currentPlayer.getBoothsOnHand());
        assertEquals(player2, amusement1.getBoothOwner());
        assertEquals(currentPlayer, amusement2.getBoothOwner());
    }

    @Test
    void drawAFreeTicketBoothCard3(){ //if the second are owned and the first isn't
        currentCard = new ChanceCard("Free ticket booth", "magenta"); //the chanceCard we are testing

        Amusement amusement1 = (Amusement) board.getSquare(12); //Amusement that match the color
        Amusement amusement2 = (Amusement) board.getSquare(13); //Amusement that match the color

        amusement2.setBoothOwner(player2);

        currentPlayer.setBooths(10); //to ensure that the player have booths to place on the board
        actionHandler.doChanceCard(currentCard, currentPlayer);

        assertEquals(9, currentPlayer.getBoothsOnHand());
        assertEquals(currentPlayer, amusement1.getBoothOwner());
        assertEquals(player2, amusement2.getBoothOwner());
    }

    @Test
    void drawAFreeTicketBoothCard4(){ //if both are owned, but there isn't monopoly
        currentCard = new ChanceCard("Free ticket booth", "magenta"); //the chanceCard we are testing

        Amusement amusement1 = (Amusement) board.getSquare(12); //Amusement that match the color
        Amusement amusement2 = (Amusement) board.getSquare(13); //Amusement that match the color

        amusement1.setBoothOwner(player2);
        amusement2.setBoothOwner(player3);

        currentPlayer.setBooths(10); //to ensure that the player have booths to place on the board
        actionHandler.doChanceCard(currentCard, currentPlayer);

        assertEquals(9, currentPlayer.getBoothsOnHand());
        assertEquals(currentPlayer, amusement1.getBoothOwner());
        assertEquals(player3, amusement2.getBoothOwner());
    }

    @Test
    //this test will result in drawing a new chanceCard, so the result may variate
    void drawAFreeTicketBoothCard5(){ //if monopoly eksists
        currentCard = new ChanceCard("Free ticket booth", "magenta"); //the chanceCard we are testing

        Amusement amusement1 = (Amusement) board.getSquare(12); //Amusement that match the color
        Amusement amusement2 = (Amusement) board.getSquare(13); //Amusement that match the color

        amusement1.setBoothOwner(player2);
        amusement2.setBoothOwner(player2);

        currentPlayer.setBooths(10); //to ensure that the player have booths to place on the board
        actionHandler.doChanceCard(currentCard, currentPlayer);

        assertEquals(10, currentPlayer.getBoothsOnHand());
        assertEquals(player2, amusement1.getBoothOwner());
        assertEquals(player2, amusement2.getBoothOwner());
    }

    @Test
    void drawAFreeTicketBoothCard6(){ //if it's possible to play a booth, but player doesn't have one
        currentCard = new ChanceCard("Free ticket booth", "magenta"); //the chanceCard we are testing

        Amusement amusement1 = (Amusement) board.getSquare(12); //Amusement that match the color
        Amusement amusement2 = (Amusement) board.getSquare(13); //Amusement that match the color

        amusement2.setBoothOwner(player2);

        currentPlayer.setBooths(10); //to ensure that the player have booths to place on the board
        actionHandler.doChanceCard(currentCard, currentPlayer);

        assertEquals(9, currentPlayer.getBoothsOnHand());
        assertEquals(currentPlayer, amusement1.getBoothOwner());
        assertEquals(currentPlayer, amusement2.getBoothOwner());
    }

    @Test
    void drawAFreeTicketBoothCard7(){ //if you own amusement1, and 2 is owned by another player
        currentCard = new ChanceCard("Free ticket booth", "magenta"); //the chanceCard we are testing

        Amusement amusement1 = (Amusement) board.getSquare(12); //Amusement that match the color
        Amusement amusement2 = (Amusement) board.getSquare(13); //Amusement that match the color

        amusement1.setBoothOwner(currentPlayer);
        amusement2.setBoothOwner(player2);

        currentPlayer.setBooths(10); //to ensure that the player have booths to place on the board
        player2.setBooths(9);
        actionHandler.doChanceCard(currentCard, currentPlayer);

        assertEquals(9, currentPlayer.getBoothsOnHand());
        assertEquals(10, player2.getBoothsOnHand());
        assertEquals(currentPlayer, amusement1.getBoothOwner());
        assertEquals(currentPlayer, amusement2.getBoothOwner());
    }

    @Test
    void drawAFreeTicketBoothCard8(){ //if it's possible to play a booth, but player doesn't have one
        currentCard = new ChanceCard("Free ticket booth", "magenta"); //the chanceCard we are testing

        Amusement amusement1 = (Amusement) board.getSquare(12); //Amusement that match the color
        Amusement amusement2 = (Amusement) board.getSquare(13); //Amusement that match the color

        amusement1.setBoothOwner(player2);
        amusement2.setBoothOwner(currentPlayer);

        currentPlayer.setBooths(10); //to ensure that the player have booths to place on the board
        player2.setBooths(9);
        actionHandler.doChanceCard(currentCard, currentPlayer);

        assertEquals(9, currentPlayer.getBoothsOnHand());
        assertEquals(10, player2.getBoothsOnHand());
        assertEquals(currentPlayer, amusement1.getBoothOwner());
        assertEquals(currentPlayer, amusement2.getBoothOwner());
    }

    //test regarding chanceCard that make the player move.
    @Test
    void drawAMoveChanceCard(){ //moving to WATER SHOW
        currentCard = new ChanceCard("GO to the FIREWORKS and pay 2 dollars", 9);

        currentPlayer.setPosition(2);
        currentPlayer.setBalance(10);

        actionHandler.doChanceCard(currentCard, currentPlayer);

        //position check
        int expected = currentCard.getDestination();
        int actual = currentPlayer.getPosition();
        assertEquals(expected, actual);

        //balance check
        expected = 8;
        actual = currentPlayer.getBalance();
        assertEquals(expected, actual);

    }

    @Test
    void drawAMoveChanceCard2(){ //moving to WATER SHOW and pass start
        currentCard = new ChanceCard("GO to the FIREWORKS and pay 2 dollars", 9);

        currentPlayer.setPosition(12);
        currentPlayer.setBalance(10);

        actionHandler.doChanceCard(currentCard, currentPlayer);

        //position check
        int expected = currentCard.getDestination();
        int actual = currentPlayer.getPosition();
        assertEquals(expected, actual);

        //balance check
        expected = 10;
        actual = currentPlayer.getBalance();
        assertEquals(expected, actual);
    }

    @Test
    void drawMoveToRestroom(){ //moving to Restroom and pass start
        currentCard = new ChanceCard("Pay 3 dollars to take the tramway to the restrooms", 11, 3);

        currentPlayer.setPosition(2);
        currentPlayer.setBalance(10);

        actionHandler.doChanceCard(currentCard, currentPlayer);

        //position check
        int expected = currentCard.getDestination();
        int actual = currentPlayer.getPosition();
        assertEquals(expected, actual);

        //balance check
        expected = 7;
        actual = currentPlayer.getBalance();
        assertEquals(expected, actual);
    }

    @Test
    void drawMoveToRestroomAndPastStart(){ //moving to Restroom and pass start
        currentCard = new ChanceCard("Pay 3 dollars to take the tramway to the restrooms", 11, 3);

        currentPlayer.setPosition(18);
        currentPlayer.setBalance(10);

        actionHandler.doChanceCard(currentCard, currentPlayer);

        //position check
        int expected = currentCard.getDestination();
        int actual = currentPlayer.getPosition();
        assertEquals(expected, actual);

        //balance check
        expected = 7;
        actual = currentPlayer.getBalance();
        assertEquals(expected, actual);
    }

    //using ChanceCard to move to amusements
    @Test
    void drawCardMoveToWaterSlideWhileNotOwned(){
        currentCard = new ChanceCard("GO to the WATER SLIDE", 15);

        currentPlayer.setPosition(2);
        currentPlayer.setBalance(10);
        currentPlayer.setBooths(10);

        actionHandler.doChanceCard(currentCard, currentPlayer);

        //position check
        int expected = currentCard.getDestination();
        int actual = currentPlayer.getPosition();
        assertEquals(expected, actual);

        //balance check
        expected = 7;
        actual = currentPlayer.getBalance();
        assertEquals(expected, actual);

        //Amount of booth in hand check
        expected = 9;
        actual = currentPlayer.getBoothsOnHand();
        assertEquals(expected, actual);

        //owner of amusement check
        Player exp = currentPlayer;
        Player act = ((Amusement) board.getSquare(currentPlayer.getPosition())).getBoothOwner();
        assertEquals(exp, act);
    }

    @Test
    void drawCardMoveToWaterSlideWhileNotOwnedAndPassingStart(){
        currentCard = new ChanceCard("GO to the WATER SLIDE", 15);

        currentPlayer.setPosition(21);
        currentPlayer.setBalance(10);
        currentPlayer.setBooths(10);

        actionHandler.doChanceCard(currentCard, currentPlayer);

        //position check
        int expected = currentCard.getDestination();
        int actual = currentPlayer.getPosition();
        assertEquals(expected, actual);

        //balance check
        expected = 9;
        actual = currentPlayer.getBalance();
        assertEquals(expected, actual);

        //Amount of booth in hand check
        expected = 9;
        actual = currentPlayer.getBoothsOnHand();
        assertEquals(expected, actual);

        //owner of amusement check
        Player exp = currentPlayer;
        Player act = ((Amusement) board.getSquare(currentPlayer.getPosition())).getBoothOwner();
        assertEquals(exp, act);
    }

    //testing of the doFieldAction method
    @Test
    void landingOnUnownedAmusement(){
        int position = 28;
        currentPlayer.setPosition(position); //using bumpercards for test
        currentPlayer.setBalance(10);
        currentPlayer.setBooths(10);

        actionHandler.doFieldAction(currentPlayer , position);

        //balance check
        int expected = 6;
        int actual = currentPlayer.getBalance();
        assertEquals(expected, actual);

        //Amount of booth in hand check
        expected = 9;
        actual = currentPlayer.getBoothsOnHand();
        assertEquals(expected, actual);

        //owner of amusement check
        Player exp = currentPlayer;
        Player act = ((Amusement) board.getSquare(currentPlayer.getPosition())).getBoothOwner();
        assertEquals(exp, act);
    }

    @Test
    void landingOnUnownedAmusementAndHaveNoBooths(){
        int position = 28;
        currentPlayer.setPosition(position); //using bumpercards for test
        currentPlayer.setBalance(10);
        currentPlayer.setBooths(0);

        actionHandler.doFieldAction(currentPlayer , position);

        //balance check
        int expected = 6;
        int actual = currentPlayer.getBalance();
        assertEquals(expected, actual);

        //Amount of booth in hand check
        expected = 0;
        actual = currentPlayer.getBoothsOnHand();
        assertEquals(expected, actual);

        //owner of amusement check
        Player act = ((Amusement) board.getSquare(currentPlayer.getPosition())).getBoothOwner();
        assertNull(act);
    }
}