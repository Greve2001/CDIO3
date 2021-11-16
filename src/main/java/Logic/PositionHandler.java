package Logic;

import MonopolyJunior.Player;

public class PositionHandler {

    Player[] players;

    int boardSize;
    int startBonus = 2; // Evt få fra controlleren

    public PositionHandler(Player[] players, int boardLength) {
        this.players = players;
        boardSize = boardLength;
    }

    public void movePlayer(Player player, int spacesToMove){
        int prevPos = player.getPosition();
        int endPos = player.getPosition() + spacesToMove;

        // Make sure player loops on board
        if (endPos >= boardSize){
            player.setPosition(endPos - boardSize);
            payBonus(player);
        }
        else {
            player.setPosition(endPos);
        }

    }

    public void setPlayerPosition(Player player, int position, boolean getsStartBonus){
        int prevPos = player.getPosition();
        int endPos = position;

        // Make sure start is passed, and that the player is allowed a bonus.
        if (getsStartBonus && endPos < prevPos) payBonus(player);
        player.setPosition(position);
    }

    private void payBonus(Player player){
        player.updateBalance(startBonus);
    }





}

