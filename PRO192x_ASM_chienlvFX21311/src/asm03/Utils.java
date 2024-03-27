package asm03;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Utils {
    public static String getDivider(){
        return "+-----------------------------------------------------------------+" ;
    }
    public static String getTitle(){
        return "BIEN LAI GIAO DICH";
    }

    public static String getDateTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static String formatBalance(double amount){
        return String.format("% ,.0f",amount)+"đ.";
    }
}
