import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BankStatementProcessor {

    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(final List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public double calculateTotalAmount() {
        double total = 0;
        for(final BankTransaction bankTransaction:bankTransactions) {
            total += bankTransaction.getAmount();
        }
        return total;
    }

    public double calculateTotalInMonth(final Month month) {
//        double total = 0d;
//        for(final BankTransaction bankTransaction:bankTransactions) {
//            if(bankTransaction.getDate().getMonth() == month){
//                total += bankTransaction.getAmount();
//            }
//        }
//        return total;
        return summarizeTransactions((accumulator, bankTransaction) -> bankTransaction.getDate().getMonth().equals(month)?accumulator + bankTransaction.getAmount():accumulator);
    }


    public double calculateTotalInCategory(final String category) {
//        double total = 0d;
//        for(final BankTransaction bankTransaction:bankTransactions) {
//            if(Objects.equals(bankTransaction.getDescription(), category)){
//                total += bankTransaction.getAmount();
//            }
//        }
//        return total;
        return summarizeTransactions((accumulator, bankTransaction) -> bankTransaction.getDescription().equals(category)?accumulator + bankTransaction.getAmount():accumulator);
    }

    public List<BankTransaction> selectInMonth(final Month month) {
       // return findTransaction(bankTransaction -> bankTransaction.getDate().getMonth()==month);
        return bankTransactions.stream().filter(bankTransaction -> bankTransaction.getDate().getMonth()==month).collect(Collectors.toList());
    }

    public List<BankTransaction> selectGreaterThanOrEqualAmount(double amount) {
        return bankTransactions.stream().filter(bankTransaction -> bankTransaction.getAmount()>=amount).collect(Collectors.toList());
    }

    public List<BankTransaction> selectinMonthAndGreaterThanOrEqualAmount(final Month month,double amount) {
        return bankTransactions.stream()
                        .filter(bankTransaction -> bankTransaction.getDate().getMonth()==month && bankTransaction.getAmount()>=amount)
                        .collect(Collectors.toList());
    }

    public List<BankTransaction> findTransaction(final BankTransactionFilter bankTransactionFilter) {
        final List<BankTransaction> result = new ArrayList<>();
        for(final BankTransaction bankTransaction:bankTransactions) {
            if(bankTransactionFilter.test(bankTransaction)){
                result.add(bankTransaction);
            }
        }
        return bankTransactions;
    }

    private double summarizeTransactions(final BankTransactionSummarizer bankTransactionSummarizer) {
        double total = 0d;
        for(final BankTransaction bankTransaction:bankTransactions) {
            total = bankTransactionSummarizer.summarize(total,bankTransaction);
        }
        return total;
    }

}
