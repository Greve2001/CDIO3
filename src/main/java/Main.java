public class Main {
    public static void main(String[] args) {
        MonopolyJunior monopoly = new MonopolyJunior(4);//need to be changeable with some user input later.
        monopoly.giveStartMoney();
        //monopoly.inputNames();//For name input later (might never go live)
        //monopoly.whoGoesFirst();//let all roll, and highest goes first
        do {
            monopoly.takeTurn();
            if (!monopoly.isWin_condition())
                monopoly.changePlayer();
        }while(!monopoly.isWin_condition());
        //MonopolyJunior.decideAndAnnounceWinner();//to find and Announce the winner
    }
}
