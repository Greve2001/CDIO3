public class Main {
    public static void main(String[] args) {
        MonopolyJunior monopoly = new MonopolyJunior(4);
        MonopolyJunior.giveStartMoney();
        monopoly.playGame();
    }
}