package model;

import util.DateFunctions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class User {
    private Integer id;
    private String name;
    private String family;
    private Integer age;
    private String nationalId;
    private Date birthday;

    private DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy");


    public User(String name, String family, String nationalId, Date birthday) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.nationalId = nationalId;
        this.birthday = birthday;
        this.age = DateFunctions.getPeriodByYear(DateFunctions.stringToDate(String.valueOf(birthday)));
    }







//getters


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "{" +
                "name=" + name +
                ", family='" + family +
                ", age=" + age +
                ", nationalId=" + nationalId +
                ", birthday=" + df.format(birthday)+
                "}\n";
    }
}
