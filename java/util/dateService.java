package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class dateService {

    public static String toDay(){

    LocalDate currentDate = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    String formattedDate = currentDate.format(formatter);
    return formattedDate;
    }
    
}
