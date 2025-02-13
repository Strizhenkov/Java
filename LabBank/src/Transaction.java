public class Transaction {
    private final long Id;
    private final int BankId;
    private final long Value;
    private final int IncomeId;
    private final int OutcomeId;

    public Transaction(long id, int bankId, long value, int incomeId, int outcomeId) {
        Id = id;
        BankId = bankId;
        Value = value;
        IncomeId = incomeId;
        OutcomeId = outcomeId;
    }

    public long GetValue() {
        return Value;
    }
    public int GetIncomeId() {
        return IncomeId;
    }
    public int GetOutcomeId() {
        return OutcomeId;
    }
    public long GetId() {
        return Id;
    }
    public int GetBankId() {
        return BankId;
    }
}
