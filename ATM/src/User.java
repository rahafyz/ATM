import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String family;
    private int age;


    public User(){}

    public User(String name, String family, int age){
        this.name = name;
        this.family = family;
        this.age = age;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public String getFamily() {
        return family;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", age=" + age +
                '}';
    }
}
