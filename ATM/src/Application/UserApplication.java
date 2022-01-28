package Application;

import Dao.UserDao;
import Exeptions.AgeException;
import Exeptions.InvalidInputException;
import Exeptions.NotFoundException;
import util.DateFunctions;
import util.MyMethod;
import model.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class UserApplication {

    private static UserDao userDao;
    static {
       userDao = new UserDao();
    }

    public static User getUser(Integer id){
        Optional<User> user = userDao.get(id);
        return user.orElseThrow(
                ()-> {
                    throw new NotFoundException("There is no user by this id");
                }
        );
    }

    public static List<User> getAllUsers() {
        return userDao.getAll();
    }

    public static void updateUser(User user, String[] params) {
        userDao.update(user, params);
    }

    public static void saveUser(User user) {
        userDao.save(user);
    }

    public static void deleteUser(Integer id) {
        userDao.delete(getUser(id));
    }





    public static User createUser(){

        User user = null;

        //get user information
        String fName = (String) MyMethod.getInput("Enter your Name :",String.class);
        String lName = (String) MyMethod.getInput("Enter your family :",String.class);
        String nId  = (String) MyMethod.getInput("Enter your national ID :",String.class);
        System.out.print("Enter your date of birth (yyyy-MM-dd): ");
        Date bDay = DateFunctions.getDate() ;

        //age validation
        if (DateFunctions.getPeriodByYear(bDay) < 18) {
            throw  new AgeException();
        }



        //check if user exist
        if(userDao.user_exist(nId)<1){
            user = new User(fName, lName, nId , bDay);


            //add user to database
            userDao.save(user);
        }
        else {
            throw new InvalidInputException("User already exist.");
        }



        return user;
    }


}
