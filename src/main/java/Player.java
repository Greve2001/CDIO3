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

    public Token getToken(){
        return token;
    }

    public void setPlayerAccount(int balance){
        playerAccount.setBalance(balance);
    }
    }
