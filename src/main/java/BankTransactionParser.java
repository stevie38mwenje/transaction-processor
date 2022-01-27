import java.util.List;

public interface BankTransactionParser {
    BankTransaction parseFrom(String line);
    List<BankTransaction> parseLinesFrom(final List<String> lines);
}
