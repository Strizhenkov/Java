public abstract class Balance {
    protected int BalanceId;
    protected int UserId;
    protected String Name;
    protected long Cash;
    protected boolean Status = false;
    protected abstract void PutMoney(long inputCash);
    protected abstract void WithdrawMoney(long outputCash);
    public abstract boolean CheckWithdrawRules(long value);
    protected void ChangeStatus() {
        Status = !Status;
    }
    public int GetUserId() {
        return UserId;
    }
    public boolean GetStatus() {
        return Status;
    }
    public long GetCash() {
        return Cash;
    }
    public void Print() {
        System.out.println(Cash + " " + Name);
    }
}