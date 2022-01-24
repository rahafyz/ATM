package database;

import Exeptions.ExceptionHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import static database.DbConfig.loadPropertiesFile;

public class DbConnector {
    private static Connection con = null;

    static {
        Properties prop = loadPropertiesFile();
        String url = prop.getProperty("MYSQLJDBC.url");
        String username = prop.getProperty("MYSQLJDBC.user");
        String password = prop.getProperty("MYSQLJDBC.password");
        try {
            con = DriverManager.getConnection(url, username, password);

            if (con != null) {
                System.out.println("connection created successfully");
            } else {
                System.out.println("unable to create connection");
            }

        } catch (Exception e) {
            ExceptionHandler.handleException(e);
        }

    }
        public static Connection getConnection(){
            return con;
        }

}
