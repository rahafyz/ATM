public abstract class Transaction {

    protected static Integer accountNum;
    protected DataBase dataBase;
    protected String type;
    protected double amount;


    public Transaction(int accountNum, String type, double amount) {
        this.accountNum = accountNum;
        this.type = type;
        this.amount = amount;
    }

    public Transaction(Integer userAccountNum, DataBase atmDatabase) {
        this.accountNum = userAccountNum;
        this.dataBase = atmDatabase;
    }

    public Integer getAccountNumber(){
        return accountNum;
    }
    public DataBase getDatabase(){
        return dataBase;
    }





    abstract public void changeBalance(Integer acc);


    @Override
    public String toString() {
        return "Transaction{" +
                "accountNum=" + accountNum +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                '}';
    }
}
