package logica.negocio;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static logica.negocio.FormatsManager.printDateFormat;
import static logica.negocio.FormatsManager.printNumberFormat;

public class Statement extends TransactionHistoric {
    private static final String LINEAS =
            "-------------------------------------------------------------------------------------";

    void printStatement() {
        List<Date> printOrder = new ArrayList<>(daysWithRecords());
        printOrder.sort(Comparator.reverseOrder());
        header();
        for (Date date : printOrder) {
            int credits = sumOperationsByType(date, "deposits");
            int debits = sumOperationsByType(date, "withdrawals");
            int balance = sumOperationsByType(date, "dayBalance");
            line(date, credits, debits, balance);
        }
        System.out.println(LINEAS);
    }

    private void header() {
        System.out.println(LINEAS);
        System.out.println("|Date                |Credit              |Debit               |Balance             |");
        System.out.println(LINEAS);
    }

    private void line(Date date, int credits, int debits, int balance) {
        System.out.println(
                "|" + printDateFormat(date) + "          |" +
                        partOfLine(credits) +
                        partOfLine(debits) +
                        partOfLine(balance));
    }

    private StringBuilder partOfLine(int number) {
        StringBuilder linea = new StringBuilder();
        linea.append(printNumberFormat(number));
        linea.append(" ".repeat(20 - printNumberFormat(number).length()));
        linea.append("|");
        return linea;
    }
}