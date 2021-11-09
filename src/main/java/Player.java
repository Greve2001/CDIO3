// A class for the player which contains the score and name of the player

public class Player {
    private final Account playerAccount = new Account();

    private int playerNumber;
    private String name;
    private boolean hasExtraTurn = false;

    public Player() {
        this.name = this.playerNumber + "";
    }

    public void setName(String name){
        this.name = name;
    }

    public String toString() {
        return name;
    }

    public Account getAccount() {
        return playerAccount;
    }

    public void setPlayerAccount(int balance){
        playerAccount.setBalance(balance);
    }

    public void giveExtraTurn(){
        hasExtraTurn = true;
    }

    public boolean checkExtraTurn(){
        if (hasExtraTurn){
            hasExtraTurn = false;
            return true;
        }
        else{
            return false;
        }

    }
}
