package belly.builders;

import belly.domain.Account;
import java.lang.Long;
import java.time.LocalDate;
import java.lang.Boolean;
import java.lang.Double;
import java.lang.String;
import belly.domain.Transaction;


public class TransactionBuilder {
    private Transaction element;
    private TransactionBuilder(){}

    public static TransactionBuilder oneTransaction() {
        TransactionBuilder builder = new TransactionBuilder();
        initializeStandardData(builder);
        return builder;
    }

    public static void initializeStandardData(TransactionBuilder builder) {
        builder.element = new Transaction();
        Transaction element = builder.element;


        element.setId(0L);
        element.setDescription("");
        element.setValue(0.0);
        element.setAccount(null);
        element.setDate(null);
        element.setStatus(false);
    }

    public TransactionBuilder withId(Long param) {
        element.setId(param);
        return this;
    }

    public TransactionBuilder withDescription(String param) {
        element.setDescription(param);
        return this;
    }

    public TransactionBuilder withValue(Double param) {
        element.setValue(param);
        return this;
    }

    public TransactionBuilder withAccount(Account param) {
        element.setAccount(param);
        return this;
    }

    public TransactionBuilder withDate(LocalDate param) {
        element.setDate(param);
        return this;
    }

    public TransactionBuilder withStatus(Boolean param) {
        element.setStatus(param);
        return this;
    }

    public Transaction createEntity() {
        return element;
    }
}
