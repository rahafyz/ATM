public class Withdraw extends Transaction{


    public Withdraw(int userAccountNum, DataBase atmDatabase) {
        super(userAccountNum, atmDatabase);
    }

    public Withdraw(int accountNum, String type, double amount) {
        super(accountNum, type, amount);
    }

    @Override
    public void changeBalance(Integer acc) {
        super.accountNum = acc;
        double balance1 = dataBase.showBalance(acc);
        super.amount = (double) MyMethod.getInput("Please Enter amount:", double.class);
        dataBase.withdraw(acc,super.amount);
    }
}
