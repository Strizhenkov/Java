public class DepositBalance extends Balance {
    long TimeWait;
    long Bet;
    public DepositBalance(int userId, String name, int balanceId, long timeWait, long bet) {
        UserId = userId;
        Name = name;
        BalanceId = balanceId;
        Cash = 0;
        TimeWait = timeWait;
        Bet = bet;
    }

    @java.lang.Override
    protected void PutMoney(long inputCash) {
        Cash += inputCash;
    }

    @java.lang.Override
    protected void WithdrawMoney(long outputCash) {
        Cash -= outputCash;
    }

    public boolean CheckWithdrawRules(long value) {
        return (Cash - value > 0) && (TimeWait >= CentralBank.GetInstance().GetTime());
    }
    public long GetBet() {
        return Bet;
    }
    public long GetTimeWait() {
        return Bet;
    }
}
