public class Account {
    private int balance;

    public void setBalance(int balance){
        this.balance = balance;
    }

    public void updateBalance(int update){
        if (update > 0 || balance > Math.abs(update))
            this.balance += update;
        else
            this.balance = 0;
    }

    public int getBalance(){
        return balance;
    }
}
