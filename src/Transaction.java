public class Transaction {
    Boolean isExpense;
    int month;
    int amount;

    public Transaction(int month, int amount, Boolean isExpense) {
        this.isExpense = isExpense;
        this.month = month;
        this.amount = amount;
    }
}
