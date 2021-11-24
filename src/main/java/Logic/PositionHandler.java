package Logic;

import MonopolyJunior.GUIController2;
import MonopolyJunior.Player;
import Utilities.Debug;

public class PositionHandler {

    private Player[] players;

    private int boardSize;
    private int startBonus;

    public PositionHandler(Player[] players, int boardLength, int startBonus) {
        this.players = players;
        this.startBonus = startBonus;
        boardSize = boardLength;
    }

    public void movePlayer(Player player, int spacesToMove){
        int endPos = player.getPosition() + spacesToMove;
        int newPos;
        if (spacesToMove == Math.abs(spacesToMove)) {
            // Make sure player loops on board
            if (endPos > boardSize) {
                newPos = endPos - boardSize;
                payBonus(player);
            } else {
                newPos = endPos;
            }
        }else{
            if (endPos < 0)
                newPos = endPos + boardSize;
            else
                newPos = endPos;
        }
        player.setPosition(newPos);
        GUIController2.movePlayer(player, newPos);

    }

    public void setPlayerPosition(Player player, int endPos, boolean getsStartBonus){
        int prevPos = player.getPosition();

        // Make sure start is passed, and that the player is allowed a bonus.
        if (getsStartBonus && endPos < prevPos) payBonus(player);

        player.setPosition(endPos);
        GUIController2.movePlayer(player, endPos);
    }

    private void payBonus(Player player){
        player.updateBalance(startBonus);
        Debug.println(player.getName() + ", just got a bonus of " + startBonus);
    }
}

