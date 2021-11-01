// A class for the player which contains the score and name of the player

public class Player {
    private final String name;
    private final Account playerAccount;
    private boolean hasExtraTurn = false;

    public Player(String name) {
        this.name = name;
        playerAccount = new Account();
    }

    public String toString() {
        return name;
    }

    public Account getAccount() {
        return playerAccount;
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
