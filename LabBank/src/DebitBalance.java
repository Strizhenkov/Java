public class DebitBalance extends Balance {
    private final long Bet;
    public DebitBalance(int userId, String name, int balanceId, long bet) {
        UserId = userId;
        Name = name;
        BalanceId = balanceId;
        Cash = 0;
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
        return Cash - value > 0;
    }
    public long GetBet() {
        return Bet;
    }
}