public class Main {
    public static void main(String[] args) {
        CentralBank MainBank = CentralBank.GetInstance();
        var MyBank = MainBank.CreateBank();
        var BaseClient = new Client("Bob", "Booob");
        MyBank.CreateDebitBalance(BaseClient, 10);
        BaseClient.AddAddress("House 5");
        BaseClient.AddDocument(123456);
        MyBank.UpdateUserDataBalances(BaseClient);
        MyBank.PutMoneyOnBalance(1000, 0, -1);
        CentralBank.GetInstance().NextDay();
        MyBank.CountMoneyOnDebitAndDepositBalance();
        MyBank.GetBalance(0).Print();
        System.out.println("Done");
    }
}