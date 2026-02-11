public class InventoryItem {
    private int id;
    private String name;
    private int[] stockHistory = new int[0];
    private int maxCapacity;

    public String getName(){
        return name;
    }

    public int[] getQuantities(){
        return stockHistory.clone();
    }

    public int getQuantityAt(int index) throws IndexOutOfBoundsException{
        int[] quantities = stockHistory;
        if (index < 0 || index >= quantities.length){
            throw new IndexOutOfBoundsException("index out of bounds");
        }
        return quantities[index];
    }

    public int getCurrentStock(){
        if (stockHistory.length == 0){
            return 0;
        } else {
            return stockHistory[stockHistory.length-1];
        }
    }

    public int getMaxCapacity(){
        return maxCapacity;
    }

    public InventoryItem(int id, String name, int maxCapacity){
        if (id < 0){
            throw new IllegalArgumentException("id cannot be negative");
        } else if (name == null || name.isBlank()){
            throw new IllegalArgumentException("name cannot be null or empty");
        } else if (maxCapacity <= 0){
            throw new IllegalArgumentException("maxCapacity needs to be > 0");
        }
        this.id = id;
        this.maxCapacity = maxCapacity;
        this.name = name;
    }

    public void addStock(int amount){
        if (amount <= 0){
            throw new IllegalStateException("amount cannot be 0 or negative");
        }

        int newLength = stockHistory.length + 1;
        int[] newStockHistory = new int[newLength];
        for (int i = 0; i < stockHistory.length; i++){
            newStockHistory[i] = stockHistory[i];
        }
        if (stockHistory.length == 0){
            if (amount > maxCapacity){
                throw new IllegalStateException("stock reached max capacity");
            }
            newStockHistory[0] = amount;
        } else {
            int temp = amount + stockHistory[stockHistory.length-1];
            if (temp > maxCapacity){
                throw new IllegalStateException("stock reached max capacity");
            }
            newStockHistory[newLength-1] = temp;
        }
        stockHistory = newStockHistory;
    }

    public void addStock(int amount, int times){
        if (amount <= 0){
            throw new IllegalStateException("amount cannot be 0 or negative");
        } else if (times <= 0){
            throw new IllegalStateException("times cannot be 0 or negative");
        }

        int newLength = stockHistory.length + times;
        int[] newStockHistory = new int[newLength];
        for (int i = 0; i < stockHistory.length; i++){
            newStockHistory[i] = stockHistory[i];
        }
        if (stockHistory.length == 0){
            if (amount > maxCapacity){
                throw new IllegalStateException("stock reached max capacity");
            }
            newStockHistory[0] = amount;
        } else {
            int temp = amount + stockHistory[stockHistory.length-1];
            if (temp > maxCapacity){
                throw new IllegalStateException("stock reached max capacity");
            }
            newStockHistory[stockHistory.length] = temp;
        }
        for (int i = stockHistory.length + 1; i < newLength ; i++){
            int temp = amount + newStockHistory[i-1];
            if (temp > maxCapacity){
                throw new IllegalStateException("stock reached max capacity");
            }
            newStockHistory[i] = temp;
        }
        stockHistory = newStockHistory;
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof InventoryItem){
            InventoryItem secondO = (InventoryItem) o;
            return this.id == secondO.id;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Item[id=" + hashCode() + ", name=" + getName() + ", stock=" + getCurrentStock() + "/" + getMaxCapacity() + "]";
    }

}
