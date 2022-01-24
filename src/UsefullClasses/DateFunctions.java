package UsefullClasses;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;

public class DateFunctions {
    public static Date stringToDate(String fDate){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(fDate);
            if(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isAfter(LocalDate.now())){
                System.err.println("Future date not allowed");
                return null;
            }
            return date;
        }catch (ParseException e){
            System.err.println("Invalid format!");
        }
        return null;
    }

    public static Date getDate(){
        Date date1 = null;
        while (date1 == null){
            String date = (String) MyMethod.getInput("",String.class);
            date1 = stringToDate(date);
        }
        return date1;
    }

    public static Integer getPeriodByYear(Date date){
        try {
            Instant instant = date.toInstant();
            ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
            LocalDate givenDate = zone.toLocalDate();
            Period period = Period.between(givenDate, LocalDate.now());
            Integer age = period.getYears();
            return age;
        }catch (Exception e){
            System.err.print(e.getMessage());
        }
        return null;
    }

    public static Date mysqlDateFormat(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatter.format(date);
        Date date2 = stringToDate(strDate);
        return date2;
    }
}
