package database;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;


public class DbConfig {
    public static Properties loadPropertiesFile(){

        Properties prop = new Properties();
        InputStream in = null;
        try {
            in = new FileInputStream("ATM/src/file/db.properties");
            prop.load(in);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return prop;
    }

}
