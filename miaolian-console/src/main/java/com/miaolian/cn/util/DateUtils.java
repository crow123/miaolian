package com.miaolian.cn.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author crow
 * @date 2015/12/13 22:52
 * @desc Please write a comment here
 */
public class DateUtils {

    final static String LOCAL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String getDateStr(){
        return getDateStr(new Date());
    }

    public static String getDateDay(){
        return getDateStr(new Date(),"dd");
    }

    public static String getDateMonth(){
        return getDateStr(new Date(),"MM");
    }

    public static String getDateYear(){
        return getDateStr(new Date(),"yyyy");
    }

    public static String getDateStr(Date date){
        return formatDate(date,null);
    }

    public static String getDateStr(Date date,String formatStr){
        return formatDate(date,formatStr);
    }

    public static Date toDate(String dateStr){
        return parse(dateStr,null);
    }

    public static Date toDate(String dateStr,String formatStr){
        return parse(dateStr,formatStr);
    }

    public static Date parse(String dateStr, String formatStr){
        if(dateStr==null){
            return null;
        }
        if(formatStr==null){
            formatStr = LOCAL_DATE_FORMAT;
        }
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        try {
            return format.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    private static String formatDate(Date date,String formatStr){
        if(date==null){
            return null;
        }
        if(formatStr==null){
            formatStr = LOCAL_DATE_FORMAT;
        }
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.format(date);
    }

    public static Date getDate(){
        return new Date();
    }

    public static Long getDateLong(){
        Date date = new Date();
        return date.getTime();
    }

    /**
     * 功能: 将日期对象按照某种格式进行转换转换后的字符串
     *
     * @param date 日期对象
     * @param pattern 转换格式 yy-MM-dd
     */
    public static String DateToString(Date date, String pattern) {
        String strDateTime = null;
        SimpleDateFormat formater = new SimpleDateFormat(pattern);
        strDateTime = date == null ? null : formater.format(date);
        return strDateTime;
    }

    /**
     * 功能: 将传入的日期对象按照yyyy-MM-dd格式转换成字符串返回
     *
     * @param date 日期对象
     * @return String
     */
    public static String DateToString(Date date) {
        String _pattern = ";yyyy-MM-dd";;
        return date == null ? null : DateToString(date, _pattern);
    }
    /**
     * 功能: 将传入的日期对象按照yyyy-MM-dd HH:mm:ss格式转换成字符串返回
     *
     * @param date 日期对象
     * @return String
     */
    public static String DateTimeToString(Date date) {
        String _pattern = ";yyyy-MM-dd HH:mm:ss";;
        return date == null ? null : DateToString(date, _pattern);
    }

    /**
     * 功能: 将插入的字符串按格式转换成对应的日期对象
     *
     * @param str 字符串
     * @param pattern 格式
     * @return Date
     */
    public static Date StringToDate(String str, String pattern) {
        Date dateTime = null;
        try {
            if (str != null && !str.equals(";")) {
                SimpleDateFormat formater = new SimpleDateFormat(pattern);
                dateTime = formater.parse(str);
            }
        } catch (Exception ex) {
        }
        return dateTime;
    }
    /**
     * 功能: 将传入的字符串按yyyy-MM-dd格式转换成对应的日期对象
     *
     * @param str 需要转换的字符串
     * @return Date 返回值
     */
    public static Date StringToDate(String str) {
        String _pattern = ";yyyy-MM-dd";;
        return StringToDate(str, _pattern);
    }

/**
 * 功能: 将传入的字符串按yyyy-MM-dd HH:mm:ss格式转换成对应的日期对象
 *
 * @param str 需要转换的字符串
 * @return Date
 */
public static Date StringToDateTime(String str) {
    String _pattern = ";yyyy-MM-dd HH:mm:ss";;
    return StringToDate(str, _pattern);
}

    /**
     * 功能: 将传入的字符串转换成对应的Timestamp对象
     *
     * @param str 待转换的字符串
     * @return Timestamp 转换之后的对象
     * @throws Exception
     *             Timestamp
     */
    public static Timestamp StringToDateHMS(String str) throws Exception {
        Timestamp time = null;
        time = Timestamp.valueOf(str);
        return time;
    }
    /**
     * 功能: 根据传入的年月日返回相应的日期对象
     *
     * @param year 年份
     * @param month 月份
     * @param day 天
     * @return Date 日期对象
     */
    public static Date YmdToDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }

    /**
     * 功能: 将日期对象按照MM/dd HH:mm:ss的格式进行转后的字符串
     *
     * @param date 日期对象
     * @return String 返回值
     */
    public static String communityDateToString(Date date) {
        SimpleDateFormat formater = new SimpleDateFormat(";MM/dd HH:mm:ss");
        String strDateTime = date == null ? null : formater.format(date);
        return strDateTime;
    }

    public static Date getMaxDateOfDay(Date date) {
        if (date == null) {
            return null;
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(11, calendar.getActualMaximum(11));
            calendar.set(12, calendar.getActualMaximum(12));
            calendar.set(13, calendar.getActualMaximum(13));
            calendar.set(14, calendar.getActualMaximum(14));
            return calendar.getTime();
        }
    }
    public static Date getMinDateOfDay(Date date) {
        if (date == null) {
            return null;
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(11, calendar.getActualMinimum(11));
            calendar.set(12, calendar.getActualMinimum(12));
            calendar.set(13, calendar.getActualMinimum(13));
            calendar.set(14, calendar.getActualMinimum(14));
            return calendar.getTime();
        }
    }

    /**
     * 功能返回传入日期对象后afterDays天数的日期对象
     *
     * @param date 日期对象
     * @param afterDays 往后天数
     * @return java.util.Date 返回值
     */
    public static Date getAfterDay(Date date, int afterDays) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, afterDays);
        return cal.getTime();
    }

    // day
    /**
     * 功能: 返回date1与date2相差的天数
     *
     * @param date1
     * @param date2
     * @return int
     */
    public static int DateDiff(Date date1, Date date2) {
        int i = (int) ((date1.getTime() - date2.getTime()) / 3600 / 24 / 1000);
        return i;
    }

    // min
    /**
     * 功能: 返回date1与date2相差的分钟数
     *
     * @param date1
     * @param date2
     * @return int
     */
    public static int MinDiff(Date date1, Date date2) {
        int i = (int) ((date1.getTime() - date2.getTime()) / 1000 / 60);
        return i;
    }

    /**
     * 功能: 返回date1与date2相差的小时数
     *
     * @param date1
     * @param date2
     * @return int
     */
    public static int MinHour(Date date1, Date date2) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long diff = date1.getTime() - date2.getTime();
        // 计算差多少小时
        long hour = diff / nh;
        return (int) hour;
    }

    // second
    /**
     * 功能: 返回date1与date2相差的秒数
     *
     * @param date1
     * @param date2
     * @return int
     */
    public static int TimeDiff(Date date1, Date date2) {
        int i = (int) ((date1.getTime() - date2.getTime()));
        return i;
    }

}
