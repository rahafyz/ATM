public class Deposit extends Transaction{

    public Deposit(Integer userAccountNum, DataBase atmDatabase) {
        super(userAccountNum, atmDatabase);
    }


    public Deposit(int accountNum, String type, double amount) {
        super(accountNum, type, amount);
    }

    @Override
    public void changeBalance(Integer acc) {
        super.accountNum = acc;
        double balance1 = dataBase.showBalance(acc);
        super.amount = (double) MyMethod.getInput("Please Enter amount:", double.class);
        if (super.amount < balance1-10){
            dataBase.debit(acc,this.amount);
            MyMethod.print("Successfully Done!");
        }
        else {
            MyMethod.print("That's too much! try again");
            changeBalance(acc);
        }
    }
}
