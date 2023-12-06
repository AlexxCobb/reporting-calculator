public class Transaction {
    String itemName;
    Boolean isExpense;
    int quantity;
    int unitPrice;
    int month;
    int amount;

    public Transaction(int month, int amount, Boolean isExpense) {
        this.isExpense = isExpense;
        this.month = month;
        this.amount = amount;
    }

    public Transaction(String itemName, Boolean isExpense, int quantity, int unitPrice) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
}
