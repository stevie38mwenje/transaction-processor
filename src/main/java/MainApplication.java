import java.io.IOException;

public class MainApplication {
    public static void main(final String... args) throws IOException {
        final BankTransactionAnalyzerSimple bankTransactionAnalyzerSimple = new BankTransactionAnalyzerSimple();
        final BankTransactionParser bankTransactionParser = new BankTransactionParserImpl();
        bankTransactionAnalyzerSimple.analyse(bankTransactionAnalyzerSimple.fileName,bankTransactionParser);
    }
}
