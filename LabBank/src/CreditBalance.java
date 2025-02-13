public class CreditBalance extends Balance{
    private long CreditLimit;
    private long CreditFee;
    public CreditBalance(int userId, String name, int balanceId, long creditLimit, long creditFee) {
        UserId = userId;
        Name = name;
        BalanceId = balanceId;
        Cash = 0;
        CreditLimit = creditLimit;
        CreditFee = creditFee;
    }

    @java.lang.Override
    protected void PutMoney(long inputCash) {
        Cash += inputCash;
    }

    @java.lang.Override
    protected void WithdrawMoney(long outputCash) {
        if (Cash - outputCash <= 0) {
            outputCash += CreditFee;
        }
        Cash -= outputCash;
    }
    @java.lang.Override
    public boolean CheckWithdrawRules(long value) {
        return Cash - value > CreditLimit;
    }
    public long GetFee() {
        return CreditFee;
    }
}