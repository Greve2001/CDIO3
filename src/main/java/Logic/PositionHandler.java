package Logic;

import MonopolyJunior.Player;
import Utilities.Debug;

public class PositionHandler {

    Debug debug = Debug.getInstance();

    Player[] players;

    int boardSize;
    int startBonus = 2; // should get this from controller

    public PositionHandler(Player[] players, int boardLength) {
        this.players = players;
        boardSize = boardLength;
    }

    public void movePlayer(Player player, int spacesToMove){
        int endPos = player.getPosition() + spacesToMove;
        if (spacesToMove == Math.abs(spacesToMove)) {

            // Make sure player loops on board
            if (endPos > boardSize) {
                player.setPosition(endPos - boardSize);
                payBonus(player);
            } else {
                player.setPosition(endPos);
            }
        }else{
            if (endPos < 0)
                player.setPosition(endPos + boardSize);
            else
                player.setPosition(endPos);
        }

    }

    public void setPlayerPosition(Player player, int endPos, boolean getsStartBonus){
        int prevPos = player.getPosition();

        // Make sure start is passed, and that the player is allowed a bonus.
        if (getsStartBonus && endPos < prevPos) payBonus(player);
        player.setPosition(endPos);
    }

    private void payBonus(Player player){
        player.updateBalance(startBonus);
        debug.println(player.getName() + ", just got a bonus of " + startBonus);
    }
}

