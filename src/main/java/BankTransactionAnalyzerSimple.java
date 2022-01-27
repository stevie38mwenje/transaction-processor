import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

public class BankTransactionAnalyzerSimple {
    public static final String RESOURCE = "src/main/resources/20210906174137700-0810295233054 (1).csv";
    final String fileName = "20210906174137700-0810295233054 (1).csv";

    public void analyse(final String fileName, final BankTransactionParser bankTransactionParser) throws IOException {
    final Path path = Paths.get(RESOURCE);
    final List<String> lines = Files.readAllLines(path);
    final List<BankTransaction> bankTransactions = bankTransactionParser.parseLinesFrom(lines);
    final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);
    collectSummary(bankStatementProcessor);
    }

    private static void collectSummary(BankStatementProcessor bankStatementProcessor) {
        System.out.println("Total for all the transactions is : "+ bankStatementProcessor.calculateTotalAmount());
        System.out.println("Total for the transactions in JANUARY is : "+ bankStatementProcessor.calculateTotalInMonth(Month.JANUARY));
        System.out.println("Total for the transactions on rent is : "+ bankStatementProcessor.calculateTotalInCategory("Rent"));
        System.out.println("Transactions in JANUARY are : "+ bankStatementProcessor.selectInMonth(Month.JANUARY));
        System.out.println("Expensive transactions are : "+ bankStatementProcessor.findTransaction(new BankTransactionIsInFebruaryAndExpensive()));
        System.out.println("Transactions greater than=3000 are : "+ bankStatementProcessor.selectinMonthAndGreaterThanOrEqualAmount(Month.FEBRUARY,2000));
    }


}
