public class Main {
    public static void main(String[] args) {
        MonopolyJunior monopoly = new MonopolyJunior(4);//need to changeable with some user input later.
        //MonopolyJunior.inputNames();//For name input later (might never go live)
        MonopolyJunior.giveStartMoney();
        //MonopolyJunior.whoGoesFirst();//let all roll, and highest goes first
        do {
            //todo logik
            monopoly.playRound();
        }while(!MonopolyJunior.win_condition);
        //MonopolyJunior.decideAndAnnounceWinner();//to find and Announce the winner
    }
}
