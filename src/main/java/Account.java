public class Account {
    private int balance;

    public Account() {
        balance = 1000; // Default start amount
    }

    public void updateBalance(int value) {
        // Ensures balance never gets beneath 0.
        if (balance + value <= 0)
            balance = 0;
        else
            balance += value;
    }

    public int getBalance(){
        return balance;
    }

}
