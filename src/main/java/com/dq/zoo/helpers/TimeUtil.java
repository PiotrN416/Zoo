package com.dq.zoo.helpers;

import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeUtil {

//    public static Timestamp getCurrentTimestamp() {
//        return new Timestamp(new Date().getTime());
//    }
//
//    public static Timestamp getPlusDaysTimestamp(int addDays) {
//        Timestamp timestamp = getCurrentTimestamp();
//        timestamp.setTime(timestamp.getTime() + TimeUnit.DAYS.toMillis(addDays));
//        return timestamp;
//    }
//
//    public static Timestamp plusDaysTimestamp(Timestamp timestamp, int addDays){
//        timestamp.setTime(timestamp.getTime() + TimeUnit.DAYS.toMillis(addDays));
//        return timestamp;
//    }

    @SuppressWarnings("deprecation")
    public static SimpleDate getCurrent() {
        Date date = new Date();
        return new SimpleDate(date.getYear() + 1900, date.getMonth() + 1, date.getDate());
    }

    public static SimpleDate getCurrentPlusDays(int days) {
        SimpleDate current = getCurrent();
        current.setDay(current.getDay() + days);
        return current;
    }
}
