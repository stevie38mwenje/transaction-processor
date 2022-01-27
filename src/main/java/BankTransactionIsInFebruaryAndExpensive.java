import java.time.Month;

public class BankTransactionIsInFebruaryAndExpensive implements BankTransactionFilter{
    @Override
    public boolean test(final BankTransaction bankTransaction) {
            return bankTransaction.getAmount() >= 1_000 && bankTransaction.getDate().getMonth() == Month.FEBRUARY;
    }
}
