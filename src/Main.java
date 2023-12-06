import javax.sound.midi.Soundbank;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        MonthlyReport monthlyReport = new MonthlyReport();
        YearlyReport yearlyReport = new YearlyReport();

        MonthTotalPerYear monthTotalPerYear = new MonthTotalPerYear (monthlyReport, yearlyReport);

        // Поехали!
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            int userInput = scanner.nextInt();
            if (userInput == 1) {
                monthlyReport.readMonthReport();
            } else if (userInput == 2) {
                yearlyReport.readYearReport();
            } else if (userInput == 3) {
                if(yearlyReport.yearTransaction.isEmpty()) {
                    System.out.println("Ошибка 404. Пожалуйста, сначала считайте данные из отчета. Команда 2.");
                    continue;
                }
                if(monthlyReport.monthRep.isEmpty()) {
                    System.out.println("Ошибка 404. Пожалуйста, сначала считайте данные из отчета. Команда 1.");
                    continue;
                }
                monthTotalPerYear.checkReports();
            } else if (userInput == 4) {
                if(monthlyReport.monthRep.isEmpty()) {
                    System.out.println("Ошибка 404. Пожалуйста, сначала считайте данные из отчета. Команда 1.");
                    continue;
                }
                monthlyReport.maxProfitProductOfMonth("Январь");
                monthlyReport.maxProfitProductOfMonth("Февраль");
                monthlyReport.maxProfitProductOfMonth("Март");
                monthlyReport.maxExpenseProductOfMonth("Январь");
                monthlyReport.maxExpenseProductOfMonth("Февраль");
                monthlyReport.maxExpenseProductOfMonth("Март");
            } else if (userInput == 5) {
                if(yearlyReport.yearTransaction.isEmpty()) {
                    System.out.println("Ошибка 404. Пожалуйста, сначала считайте данные из отчета. Команда 2.");
                    continue;
                }
                System.out.println("Рассматриваемый год: " + yearlyReport.year);
                System.out.println();
                yearlyReport.printProfitReport();
                System.out.println("Средний расход: " + yearlyReport.middleYearExpense());
                System.out.println();
                System.out.println("Средний доход: " + yearlyReport.middleYearIncome());
                System.out.println();
            } else if (userInput == 911) {
                System.out.println("Всего хорошего!");
                return;
            } else {
                System.out.println("Такой команды нет.");
            }
        }
    }


    static void printMenu() {
        System.out.println("Здравствуйте! Выберите номер команды, которую необходимо выполнить:");
        System.out.println("1 - Считать все месячные отчёты.");
        System.out.println("2 - Считать годовой отчёт.");
        System.out.println("3 - Сверить отчёты.");
        System.out.println("4 - Вывести информацию обо всех месячных отчётах.");
        System.out.println("5 - Вывести информацию о годовом отчёте.");
        System.out.println("911 - Завершить работу программы!");
        System.out.println();
    }


}