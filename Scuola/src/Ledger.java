public class Ledger {
    private Transaction[] transaction;
    private int size;
    private double initialBalance;
    
    public Ledger(int initialBalance){
        if (initialBalance < 0){
            throw new IllegalArgumentException("initial balance cannot be negative");
        }
        this.initialBalance = initialBalance;
        size = 0;
        transaction = new Transaction[size];
    }
}
