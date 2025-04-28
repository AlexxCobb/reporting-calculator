import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {

    FileReader fileReader = new FileReader();
    int year = 2021;

    ArrayList<Transaction> yearTransaction = new ArrayList<>();
    HashMap<String, Integer> profitForMonths = new HashMap<>();


    ArrayList<Transaction> readYearReport(String path) {
        ArrayList<String> yearLines = fileReader.readFileContents(path);
        for (int i = 1; i < yearLines.size(); i++) {
            String[] lineContents = yearLines.get(i).split(",");
            int month = Integer.parseInt(lineContents[0]);
            int amount = Integer.parseInt(lineContents[1]);
            boolean isExpense = Boolean.parseBoolean(lineContents[2]);

            Transaction transaction = new Transaction(month, amount, isExpense);
            yearTransaction.add(transaction);
        }
        return yearTransaction;
    }


    Integer allYearIncome() {
        int sum = 0;
        for (Transaction transaction : yearTransaction) {
            if (!transaction.isExpense) {
                sum += transaction.amount;
            }
        }
        return sum;
    }

    Integer allYearExpense() {
        int sum = 0;
        for (Transaction transaction : yearTransaction) {
            if (transaction.isExpense) {
                sum += transaction.amount;
            }
        }
        return sum;
    }

    Integer middleYearIncome() {
        int sum = 0;
        int count = 0;
        for (Transaction transaction : yearTransaction) {
            if (!transaction.isExpense) {
                count++;
                sum += transaction.amount;
            }
        }
        return sum / count;
    }

    Integer middleYearExpense() {
        int sum = 0;
        int count = 0;
        for (Transaction transaction : yearTransaction) {
            if (transaction.isExpense) {
                count++;
                sum += transaction.amount;
            }
        }
        return sum / count;
    }

    HashMap<String, Integer> profitToMonthOfYear() {
        int profit = 0;
        int expense = 0;
        int income = 0;
        String month = "";
        for (Transaction transaction : yearTransaction) {
            if (transaction.month == 1) {
                if (transaction.isExpense) {
                    expense = transaction.amount;
                } else {
                    income = transaction.amount;
                }
                profit = income - expense;
                month = "Январь";
                profitForMonths.put(month, profit);
            } else if (transaction.month == 2) {
                if (transaction.isExpense) {
                    expense = transaction.amount;
                } else {
                    income = transaction.amount;
                }
                profit = income - expense;
                month = "Февраль";
                profitForMonths.put(month, profit);
            } else if (transaction.month == 3) {
                if (transaction.isExpense) {
                    expense = transaction.amount;
                } else {
                    income = transaction.amount;
                }
                profit = income - expense;
                month = "Март";
                profitForMonths.put(month, profit);
            }
        }
        return profitForMonths;
    }

    void printProfitReport() {
        profitToMonthOfYear();
        for (String month : profitForMonths.keySet()) {
            Integer value = profitForMonths.get(month);
            System.out.println("Прибыль за " + month + " составила: " + value);
            System.out.println();
        }
    }
}
