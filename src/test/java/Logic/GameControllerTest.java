package Logic;

import MonopolyJunior.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


class GameControllerTest {

    GameController gameController;

    @BeforeEach
    void setUp() throws NoSuchMethodException {

        gameController = new GameController();
        gameController.setupGame();

        Method doChanceCardMethod = ActionHandler.class.getDeclaredMethod("doChanceCard", ChanceCard.class, Player.class);
        doChanceCardMethod.setAccessible(true);
        ActionHandler actionHandler = new ActionHandler(gameController, gameController.board, gameController.positionHandler);
        
    }

    @Test
    void testAllowedNumberOfPlayers() throws NoSuchFieldException, IllegalAccessException {

        int[] numOfPlayers = {0, 1, 2, 3, 4, 5, 6};
        int[] expectednumPlayers = {0, 0, 2, 3, 4, 0, 0};

        for (int i = 0; i < numOfPlayers.length; i++) {

            String[] playerNames = new String[numOfPlayers[i]]; // Just to use function
            for (int j = 0; j < numOfPlayers[i]; j++) {
                playerNames[j] = Integer.toString(j);

            }
            gameController.setupPlayers(playerNames);

            int acutalNumOfPlayers = gameController.getPlayers().length;

            assertEquals(expectednumPlayers[i], acutalNumOfPlayers);
        }
    }
}