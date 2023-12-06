import java.util.ArrayList;
import java.util.HashMap;
import java.util.IllegalFormatCodePointException;

public class MonthTotalPerYear {

    public MonthlyReport monthlyReport;
    public YearlyReport yearlyReport;
    HashMap<Integer, String> calendar = new HashMap<>();




    public MonthTotalPerYear(MonthlyReport monthlyReport, YearlyReport yearlyReport) {
        this.monthlyReport = monthlyReport;
        this.yearlyReport = yearlyReport;
        calendar.put(1, "Январь");
        calendar.put(2, "Февраль");
        calendar.put(3, "Март");
    }





    boolean checkReports () {
        HashMap<String, HashMap<Integer,Integer>> monthReport = new HashMap<>();
        HashMap<String, HashMap<Integer,Integer>> yearReport = new HashMap<>();
        boolean isValid = true;

        HashMap<String, ArrayList<Transaction>> keys = monthlyReport.monthRep;

        for (String month : keys.keySet()) {
            HashMap<Integer,Integer> expensesToProfit = new HashMap<>();
            ArrayList<Transaction> reportMonth = monthlyReport.monthRep.get(month);
            int sumExp = 0;
            int sumProfit = 0;
            for (Transaction transaction : reportMonth) {
                int multi = transaction.unitPrice * transaction.quantity;
                if (transaction.isExpense) {
                    sumExp += multi;
                } else {
                    sumProfit += multi;
                }
            }
            expensesToProfit.put(sumExp,sumProfit);
            monthReport.put(month,expensesToProfit);
        }


        ArrayList<Transaction> yearRep = yearlyReport.yearTransaction;

        for (int i = 0; i < yearRep.size(); i += 2) {

            HashMap<Integer,Integer> expensesToProfit = new HashMap<>();

            int sumExp;
            int sumProfit;
            Transaction transaction = yearRep.get(i);
            Transaction transaction1 = yearRep.get(i + 1);

            if (transaction.isExpense) {
                sumExp = transaction.amount;
                sumProfit = transaction1.amount;
            }
            else {
                sumExp = transaction1.amount;
                sumProfit = transaction.amount;
            }
            expensesToProfit.put(sumExp,sumProfit);

            yearReport.put(calendar.get(transaction.month),expensesToProfit);
        }

        for (String month : monthReport.keySet()) {
            HashMap<Integer, Integer> monthReportExpToProf = monthReport.get(month);
            HashMap<Integer, Integer> yearReportExpToProf = yearReport.get(month);

            for (Integer expNumber : monthReportExpToProf.keySet()) {
                int profitMonth = monthReportExpToProf.get(expNumber);
                int profitYear = yearReportExpToProf.getOrDefault(expNumber, -1);

                if (profitMonth != profitYear) {
                    System.out.println("Отчет не совпал в " + month);
                    isValid = false;
                }
            }
        }
        return isValid;
    }
}

