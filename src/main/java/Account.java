public class Account {
    private int balance;

    public Account() {
        balance = 1000; // Default start amount
    }

    public void updateBalance(int balance) {
       this.balance = balance;
    }

    public int getBalance(){
        return balance;
    }
}
