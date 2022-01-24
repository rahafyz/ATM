import Application.UserApplication;
import Dao.UserDao;
import Exeptions.ExceptionHandler;
import UsefullClasses.DateFunctions;
import UsefullClasses.MyMethod;
import database.DbConfig;
import database.DbConnector;
import model.ATM;
import model.User;

public class Runner {
    public static void main(String[] args) {




        java.util.Date utilStartDate = DateFunctions.stringToDate("1988-10-15");
        java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
        String[] update = {
                "sahar","razaghi","2123456",String.valueOf(sqlStartDate)
        };
        User user = new User("saba","akbari","21003342", sqlStartDate);
try {
//    User user2 = UserApplication.getUser(2);
//    UserApplication.updateUser(user2, update);
    User user3 = UserApplication.getUser(3);
    UserApplication.deleteUser(user3);
}catch (Exception e){
    ExceptionHandler.handleException(e);
}








//        model.Account account = new model.Account();
        /*Integer accountNumber = (Integer) UsefullClasses.MyMethod.getInput("please Enter your account Number:", Integer.class);
        Integer password = (Integer) UsefullClasses.MyMethod.getInput("please enter your password:", Integer.class);
        System.out.println(account.validation(accountNumber,password));*/







     /*   model.ATM atm = new model.ATM();
        atm.login();
        atm.showMenu();*/

    }
}