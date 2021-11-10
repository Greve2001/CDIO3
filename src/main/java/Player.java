// A class for the player which contains the score and name of the player

public class Player {
    private final Account playerAccount = new Account();
    private final Token token = new Token();

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

    public int getPosition(){
        return token.getPosition();
    }

    public void setPosition(int update) {
        token.setPosition(update);
    }
}
