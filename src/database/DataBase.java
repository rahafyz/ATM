package database;

public class DataBase {
    private static DataBase dataBase = null;


    public static DataBase getInstance(){
        if (dataBase == null)
            dataBase = new DataBase();
        return dataBase;
    }
}
