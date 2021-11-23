package Logic;

import MonopolyJunior.Player;
import Utilities.Debug;

public class Bank {

    public void payToBank(Player fromPlayer, int amount){
        int pay = ableToPay(fromPlayer, amount);
        fromPlayer.updateBalance(-pay);
        Debug.println(fromPlayer.getName() + ", paid $" + pay + " to the bank");
    }

    public void payToPlayer(Player fromPlayer, Player toPlayer, int amount){
        int pay = ableToPay(fromPlayer, amount);
        fromPlayer.updateBalance(-pay);
        toPlayer.updateBalance(pay);
        Debug.println(fromPlayer.getName() + ", paid $" + pay + " to " + toPlayer.getName());
    }

    private int ableToPay(Player player, int amount){
        if (player.getBalance() <= 0 || amount <= 0){
            return 0;
        }
        if (player.getBalance() - amount < 0){
            // Will pay as much as possible
            return player.getBalance();
        }
        return amount;
    }
}
