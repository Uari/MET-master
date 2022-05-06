package secuwow.MET.function;

import java.util.Calendar;

public class DateUtil
{
    public static String getChineseCurrentDayName()
    {
        int daySeq = (int)(System.currentTimeMillis() / 1000 / 60 / 60 / 24) % 7;

        return getChineseDayName(daySeq);
    }

    public static int getFirstDaySequence()
    {
        Calendar cal = Calendar.getInstance();

        int currentDay   = cal.get(Calendar.DATE);

        long utcTime = 9 * 1000 * 60 * 60;

        int elapsedTime = (currentDay - 1) * 24 * 60 * 60 * 1000;

        int daySeq = (int)((System.currentTimeMillis() + utcTime - elapsedTime) / 1000 / 60 / 60 / 24) % 7;

        return daySeq;
    }

    public static String getChineseFirstDayName()
    {
        Calendar cal = Calendar.getInstance();

        int currentDay   = cal.get(Calendar.DATE);

        long utcTime = 9 * 1000 * 60 * 60;

        int elapsedTime = (currentDay - 1) * 24 * 60 * 60 * 1000;

        int daySeq = (int)((System.currentTimeMillis() + utcTime - elapsedTime) / 1000 / 60 / 60 / 24) % 7;

        return getChineseDayName(daySeq);
    }

    public static String getChineseDayName(int daySeq)
    {
        String day[] = {"木", "金", "土", "日", "月", "火", "水"};

        return day[daySeq];
    }

    public static String getEnglishMonthName(int monthSeq)
    {
        String month[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        return month[monthSeq - 1];
    }

    public static String getEnglishDayName(int daySeq)
    {
        String day[] = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        return day[daySeq - 1];
    }

    public static String getKoreanDayName(int daySeq)
    {
        String day[] = {"일", "월", "화", "수", "목", "금", "토"};

        return day[daySeq - 1];
    }
}
