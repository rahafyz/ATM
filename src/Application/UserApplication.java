package Application;

import Dao.Dao;
import Dao.UserDao;
import Exeptions.NotFoundException;
import model.User;

import java.util.List;
import java.util.Optional;

public class UserApplication {

    private static Dao<User> userDao;
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

    public static void deleteUser(User user) {
        userDao.delete(user);
    }


}
