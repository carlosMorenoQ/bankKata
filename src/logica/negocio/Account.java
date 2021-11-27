package logica.negocio;

import java.util.Date;

public class Account extends TransactionsAssistant {
    private Statement statement;

    public Account(Statement statement) {
        this.statement = statement;
    }

    void deposit(int amount, Date date) {
        String operation = "deposits";
        executeOperation(amount, date, operation);
    }

    void withdrawal(int amount, Date date) {
        String operation = "withdrawals";
        executeOperation(amount, date, operation);

    }

    void printStatement() {
        statement.historicOfOperations = this.historicOfOperations;
        statement.printStatement();
    }

}
