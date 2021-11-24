package Logic;

import MonopolyJunior.GUIController2;
import MonopolyJunior.Player;
import Utilities.Debug;

public class PositionHandler {

    private final int BOARDSIZE;
    private final int STARTBONUS;

    public PositionHandler(int boardLength, int STARTBONUS) {
        this.STARTBONUS = STARTBONUS;
        this.BOARDSIZE = boardLength;
    }

    public void movePlayer(Player player, int spacesToMove){
        int endPos = player.getPosition() + spacesToMove;
        int newPos;
        if (spacesToMove == Math.abs(spacesToMove)) {
            // Make sure player loops on board
            if (endPos > BOARDSIZE) {
                newPos = endPos - BOARDSIZE;
                payBonus(player);
            } else {
                newPos = endPos;
            }
        }else{
            if (endPos < 0)
                newPos = endPos + BOARDSIZE;
            else
                newPos = endPos;
        }
        player.setPosition(newPos);
        GUIController2.movePlayer(player, newPos);

    }

    public void setPlayerPosition(Player player, int endPos, boolean getsSTARTBONUS){
        int prevPos = player.getPosition();

        // Make sure start is passed, and that the player is allowed a bonus.
        if (getsSTARTBONUS && endPos < prevPos) payBonus(player);

        player.setPosition(endPos);
        GUIController2.movePlayer(player, endPos);
    }

    private void payBonus(Player player){
        player.updateBalance(STARTBONUS);
        Debug.println(player.getName() + ", just got a bonus of " + STARTBONUS);
    }
}

