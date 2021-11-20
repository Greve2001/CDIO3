package Logic;

import MonopolyJunior.Player;

public class PositionHandler {

    Player[] players;

    int boardSize;
    int startBonus = 2; // should get this from controller

    public PositionHandler(Player[] players, int boardLength) {
        this.players = players;
        boardSize = boardLength;
    }

    public void movePlayer(Player player, int spacesToMove){
        int endPos = player.getPosition() + spacesToMove;

        // Make sure player loops on board
        if (endPos > boardSize){
            player.setPosition(endPos - boardSize);
            System.out.println(player.getName() + " position now at: " + (endPos - boardSize));
            payBonus(player);
        }
        else {
            player.setPosition(endPos);
            System.out.println(player.getName() + " position now at: " + endPos);
        }
    }

    public void setPlayerPosition(Player player, int endPos, boolean getsStartBonus){
        int prevPos = player.getPosition();

        // Make sure start is passed, and that the player is allowed a bonus.
        if (getsStartBonus && endPos < prevPos) payBonus(player);
        player.setPosition(endPos);
        System.out.println(player.getName() + " position now at: " + endPos);
    }

    private void payBonus(Player player){
        player.updateBalance(startBonus);
        System.out.println(player.getName() + "just got a bonus of " + startBonus);
    }
}

