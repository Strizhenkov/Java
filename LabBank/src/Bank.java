import java.util.ArrayList;
import java.util.Objects;

import static java.lang.Math.round;

public class Bank {
    private final int Id;
    private final ArrayList<Balance> BankBalances;
    private final ArrayList<Client> Clients;
    private long LocalTime;
    public Bank(int id){
        Id = id;
        LocalTime = 0;
        BankBalances = new ArrayList<>();
        Clients = new ArrayList<>();
    }
    public void UpdateUserDataBalances(Client user) {
        long localId = Clients.indexOf(user);
        for (Balance bankBalance : BankBalances) {
            if (bankBalance.GetUserId() == localId) {
                bankBalance.ChangeStatus(); 
            }
        }
    }
    public void CreateCreditBalance(Client user, long creditLimit, long creditFee) {
        AddClient(user);
        BankBalances.add(new CreditBalance(Clients.indexOf(user), user.GetName(), CentralBank.GetInstance().GetNewBalanceId(),  creditLimit, creditFee));
        if (CheckUserData(user)) {
            BankBalances.get(BankBalances.size() - 1).ChangeStatus();
        }
    }
    public void CreateDebitBalance(Client user, long bet) {
        AddClient(user);
        BankBalances.add(new DebitBalance(Clients.indexOf(user), user.GetName(), CentralBank.GetInstance().GetNewBalanceId(), bet));
        if (CheckUserData(user)) {
            BankBalances.get(BankBalances.size() - 1).ChangeStatus();
        }
    }
    public void CreateDepositBalance(Client user, long timeWait, long bet) {
        AddClient(user);
        BankBalances.add(new DepositBalance(Clients.indexOf(user), user.GetName(), CentralBank.GetInstance().GetNewBalanceId(), timeWait, bet));
        if (CheckUserData(user)) {
            BankBalances.get(BankBalances.size() - 1).ChangeStatus();
        }
    }

    //int anotherBalanceId = -1 В случае пополнения/снятия из банкомата (то есть не перевод)
    public void PutMoneyOnBalance(long value, int balanceId, int anotherBalanceId) {
        BankBalances.get(balanceId).PutMoney(value);
        Transaction newTransaction = new Transaction(CentralBank.GetInstance().GetNewTransactionId(), Id, value, balanceId, anotherBalanceId);
        CentralBank.GetInstance().AddTransaction(newTransaction);
    }
    public void WithdrawMoneyFromBalance(long value, int balanceId, int anotherBalanceId) {
        if (BankBalances.get(balanceId).CheckWithdrawRules(value)) {
            BankBalances.get(balanceId).WithdrawMoney(value);
            Transaction newTransaction = new Transaction(CentralBank.GetInstance().GetNewTransactionId(), Id, value, anotherBalanceId, balanceId);
            CentralBank.GetInstance().AddTransaction(newTransaction);
            if ((BankBalances.get(balanceId) instanceof CreditBalance) && (BankBalances.get(balanceId).GetCash() < 0)) {
                Transaction feeTransaction = new Transaction(CentralBank.GetInstance().GetNewTransactionId(), Id, ((CreditBalance)BankBalances.get(balanceId)).GetFee(), -1, balanceId);
                CentralBank.GetInstance().AddTransaction(feeTransaction);
            }
        }
    }
    public void SentMoneyToAnotherBalance(long value, int sentBalanceId, int recipientBalanceId) {
        WithdrawMoneyFromBalance(value, sentBalanceId, recipientBalanceId);
        PutMoneyOnBalance(value, recipientBalanceId, recipientBalanceId);
    }
    private boolean CheckUserData(Client client) {
        return !Objects.equals(client.GetAddress(), "") && client.GetDocumentId() != 0;
    }
    private void AddClient(Client client) {
        if (!Clients.contains(client)) {
            Clients.add(client);
        }
    }

    public void CancelOperation(Transaction transaction) {
        Transaction newTransaction;
        if (transaction.GetIncomeId() == -1) {
            PutMoneyOnBalance(transaction.GetValue(), transaction.GetIncomeId(), -1);
            newTransaction = new Transaction(CentralBank.GetInstance().GetNewTransactionId(), Id, transaction.GetValue(), transaction.GetIncomeId(), -1);
        } else if (transaction.GetOutcomeId() == -1) {
            WithdrawMoneyFromBalance(transaction.GetValue(), -1, transaction.GetOutcomeId());
            newTransaction = new Transaction(CentralBank.GetInstance().GetNewTransactionId(), Id, transaction.GetValue(), -1, transaction.GetOutcomeId());
        } else {
            SentMoneyToAnotherBalance(transaction.GetValue(), transaction.GetOutcomeId(), transaction.GetIncomeId());
            newTransaction = new Transaction(CentralBank.GetInstance().GetNewTransactionId(), Id, transaction.GetValue(), transaction.GetOutcomeId(), transaction.GetIncomeId());
        }
        CentralBank.GetInstance().AddTransaction(newTransaction);
    }

    public void CountMoneyOnDebitAndDepositBalance() {
        if (LocalTime != CentralBank.GetInstance().GetTime()) {
            LocalTime = CentralBank.GetInstance().GetTime();
            for (Balance bankBalance : BankBalances) {
                if (bankBalance instanceof DebitBalance) {
                    long betCash = round((bankBalance.GetCash() * ((float) ((DebitBalance) bankBalance).GetBet()) / 100));
                    Transaction betTransaction = new Transaction(CentralBank.GetInstance().GetNewTransactionId(), Id, betCash, bankBalance.BalanceId, -1);
                    CentralBank.GetInstance().AddTransaction(betTransaction);
                    PutMoneyOnBalance(betCash, bankBalance.BalanceId, -1);
                }
            }
        }
        for (Balance bankBalance : BankBalances) {
            if ((bankBalance instanceof DepositBalance) && (((DepositBalance) bankBalance).GetTimeWait() == CentralBank.GetInstance().GetTime())) {
                long betCash = round((bankBalance.GetCash() * ((float) ((DepositBalance) bankBalance).GetBet()) / 100));
                Transaction betTransaction = new Transaction(CentralBank.GetInstance().GetNewTransactionId(), Id, betCash, bankBalance.BalanceId, -1);
                CentralBank.GetInstance().AddTransaction(betTransaction);
                PutMoneyOnBalance(betCash, bankBalance.BalanceId, -1);
            }
        }
    }

    public Balance GetBalance(int id) {
        return BankBalances.get(id);
    }
}