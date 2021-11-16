package Logic;

import MonopolyJunior.Player;

public class Bank {


    public void payToBank(Player fromPlayer, int amount){
        fromPlayer.updateBalance(-ableToPay(fromPlayer, amount));
        System.out.println(fromPlayer.getName() + " paid " + amount + " to the bank");
    }

    public void payToPlayer(Player fromPlayer, Player toPlayer, int amount){
        fromPlayer.updateBalance(-ableToPay(fromPlayer, amount));
        toPlayer.updateBalance(ableToPay(fromPlayer, amount));
        System.out.println(fromPlayer.getName() + " paid " + amount + " to " + toPlayer.getName());
    }

    public int ableToPay(Player player, int amount){
        if (player.getBalance() <= 0){
            return 0;
        }
        if (player.getBalance() - amount < 0){
            // Will pay as much as possible
            return player.getBalance();
        }
        return amount;
    }
}
