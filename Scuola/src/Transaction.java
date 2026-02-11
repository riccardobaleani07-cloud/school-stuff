public abstract class Transaction {
    protected double amount;
    protected long timestamp;

    public Transaction(double amount, long timestamp) {
        if (amount == 0){
            throw new IllegalArgumentException("amount cannot be 0");
        }
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public double getAmount(){
        return amount;
    }

    public long getTimestamp(){
        return timestamp;
    }

    public abstract double apply(double balance);

    @Override
    public String toString() {
        return "[amount = " + amount + ", timestamp = " + timestamp + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Transaction){
            Transaction trj = (Transaction) obj;
            if (trj.amount == this.amount || trj.timestamp == this.timestamp){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        long longT = Double.doubleToLongBits(amount);
        int intT = (int) (timestamp ^ (timestamp >>> 32)) + (int) (longT ^ (longT >>> 32));
        return 31 + intT;
    }
}
