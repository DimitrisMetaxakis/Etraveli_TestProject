package dtos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Travel {
    public String fromCity = "Athens";
    public String toCity = "Thessaloniki";
    public String fromDate = "Tue Mar 28 2023";
    public String toDate = "Fri Mar 31 2023";
    public String month = "March 2023";

//    public String fromDate = getDatePlusToday(26, "EEE MMM d yyyy");
//    public String toDate = getDatePlusToday(29, "EEE MMM d yyyy");
//    public String month = getDatePlusToday(26, "MMMM yyyy");

    /***
     * Gets current day and adds x days
     * and returns the date with the provided format
     * @param formatPattern
     * @param numDays
     * */
    public static String getDatePlusToday(int numDays, String formatPattern) {
        // get current date
        LocalDate currentDate = LocalDate.now();
        // add input integer to current date to get the new date
        LocalDate newDate = currentDate.plusDays(numDays);
        // format the new date in the desired format with English locale
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern, Locale.ENGLISH);
        String formattedDate = newDate.format(formatter);
        return formattedDate;
    }




}
