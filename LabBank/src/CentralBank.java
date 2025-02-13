import java.util.ArrayList;
public class CentralBank {
    private int BalancesCount;
    private int TransactionCount;
    private long CurrentTime;
    private ArrayList<Transaction> AllTransactions;
    private ArrayList<Bank> AllBanks;
    private CentralBank(){
        AllTransactions = new ArrayList<>();
        AllBanks = new ArrayList<>();
        BalancesCount = -1;
        CurrentTime = 0;
    }
    private static class CentralBankHolder {
        public static final CentralBank HOLDER_INSTANCE = new CentralBank();
    }
    public static CentralBank GetInstance() {
        return CentralBankHolder.HOLDER_INSTANCE;
    }
    public int GetNewBalanceId() {
        BalancesCount++;
        return BalancesCount;
    }
    public int GetNewTransactionId() {
        TransactionCount++;
        return TransactionCount;
    }
    public Bank CreateBank() {
        AllBanks.add(new Bank(AllBanks.size()));
        return AllBanks.get(AllBanks.size() - 1);
    }
    public void AddTransaction(Transaction transaction) {
        AllTransactions.add(transaction);
    }

    public String CancelTransaction(long id) {
        int i = 0;
        while (i <= AllTransactions.size() && AllTransactions.get(i).GetId() != id) {
            i++;
        }
        if (i > AllTransactions.size()) {
            return "No transaction with current id";
        } else {
            AllBanks.get(AllTransactions.get(i).GetBankId()).CancelOperation(AllTransactions.get(i));
            return "Transaction canceled";
        }
    }
    public long GetTime() {
        return CurrentTime;
    }
    public void NextDay() {
        CurrentTime++;
    }
}