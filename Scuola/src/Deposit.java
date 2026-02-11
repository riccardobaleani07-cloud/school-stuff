public class Deposit extends Transaction {
    public Deposit(double amount, long timestamp) {
        if (amount < 0){
            throw new IllegalArgumentException("amount cannot be negative");
        }
        super(amount, timestamp);
    }

    public double apply(double balance){
        return balance + amount;
    }

    @Override
    public String toString() {
        return "[amount = " + amount + ", timestamp = " + timestamp + "]";
    }
}
