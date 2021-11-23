package Logic;

import MonopolyJunior.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

class GameControllerTest {

    GameController gameController;

    @BeforeEach
    void setUp() throws NoSuchMethodException {

        gameController = new GameController();
        gameController.setupGame(4);

        Method doChanceCardMethod = ActionHandler.class.getDeclaredMethod("doChanceCard", ChanceCard.class, Player.class);
        doChanceCardMethod.setAccessible(true);
        ActionHandler actionHandler = new ActionHandler(gameController, gameController.board, gameController.positionHandler);
        
    }

    @Test
    void setupGameTest(){

        int amountOfPlayer = 4;

        gameController.setupGame(amountOfPlayer);

    }


}