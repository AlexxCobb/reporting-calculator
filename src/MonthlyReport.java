import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {

    FileReader fileReader = new FileReader();
    HashMap<String, ArrayList<Transaction>> monthRep = new HashMap<>();


    HashMap<String, ArrayList<Transaction>> readMonthReport() {

        for (int i = 1; i < 4; i++) {
            String monthName = "";
            ArrayList<Transaction> monthTransaction = new ArrayList<>();
            String monthReport = "m.20210" + i + ".csv";
            ArrayList<String> monthLines = fileReader.readFileContents(monthReport);

            for (int j = 1; j < monthLines.size(); j++) {
                String[] lineContent = monthLines.get(j).split(",");
                String itemName = lineContent[0];
                boolean isExpense = Boolean.parseBoolean(lineContent[1]);
                int quantity = Integer.parseInt(lineContent[2]);
                int unitPrice = Integer.parseInt(lineContent[3]);

                Transaction transaction = new Transaction(itemName, isExpense, quantity, unitPrice);
                monthTransaction.add(transaction);
            }
            if (i == 1) {
                monthName = "Январь";
            } else if (i == 2) {
                monthName = "Февраль";
            } else {
                monthName = "Март";
            }
            monthRep.put (monthName, monthTransaction);
        }
        return monthRep;
    }


    void maxProfitProductOfMonth (String month) {
        int maxProfit = 0;
        String maxProfitItem = null;
        ArrayList<Transaction> stats = monthRep.get(month);
        System.out.println("Статистика за " + month);
        for (Transaction stat : stats) {
            if (!stat.isExpense) {
                int multi = stat.unitPrice * stat.quantity;
                if (multi > maxProfit) {
                    maxProfit = multi;
                    maxProfitItem = stat.itemName;
                }
            }
        }
        System.out.println("Самый прибыльный товар: " + maxProfitItem + " полученная сумма " + maxProfit);
        System.out.println();
    }

    void maxExpenseProductOfMonth (String month) {
        int maxExpense = 0;

        String maxExpenseItem = null;
        ArrayList<Transaction> stats = monthRep.get(month);
        System.out.println("Статистика за " + month);
        for (Transaction stat : stats) {
            if (stat.isExpense) {
                int currExpense = stat.unitPrice * stat.quantity;
                if (currExpense > maxExpense) {
                    maxExpense = currExpense;
                    maxExpenseItem = stat.itemName;
                }
            }
        }
        System.out.println("Самые большие траты были по категории: " + maxExpenseItem + " .Потрачено : " + maxExpense);
        System.out.println();
    }
}
