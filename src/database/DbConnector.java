package database;

import Exeptions.ExceptionHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import static database.DbConfig.loadPropertiesFile;

public class DbConnector {
    private static DbConnector dbConnector;


    public static DbConnector getInstance() {
        DbConnector localInstance = dbConnector;
        if (localInstance == null) {
            synchronized (DbConnector.class) {
                localInstance = dbConnector;
                if (localInstance == null) {
                    dbConnector = localInstance = new DbConnector();
                }
            }
        }
        return localInstance;
    }


    public Connection getConnection(){
        Properties prop = loadPropertiesFile();


        String url = prop.getProperty("MYSQLJDBC.url");
        String username = prop.getProperty("MYSQLJDBC.user");
        String password = prop.getProperty("MYSQLJDBC.password");

        Connection con = null;

        try {
            con = DriverManager.getConnection(url, username, password);

            if (con != null) {
                System.out.println("connection created successfully");
            }

            else {
                System.out.println("unable to create connection");
            }

        }catch (Exception e) {
            ExceptionHandler.handleException(e);
        } finally {

            try {
//                if (con != null) {
//                    con.close();
//                }
            } catch (Exception ex) {
                ExceptionHandler.handleException(ex);
            }
        }
        return con;
    }
}
