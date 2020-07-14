/*
 * MIT License
 *
 * Copyright (c) 2020 OpeningO Co.,Ltd.
 *
 *    https://openingo.org
 *    contactus(at)openingo.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.openingo.jdkits.date;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间工具 DateTimeKit
 *
 * @author Qicz
 */
public final class DateTimeKit {

    private DateTimeKit(){}

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String FULL_DATE_24HR_STYLE = "yyyy-MM-dd HH:mm:ss";

    /**
     * yyyy-MM-dd hh:mm:ss
     */
    public static final String FULL_DATE_12HR_STYLE = "yyyy-MM-dd hh:mm:ss";

    /**
     * yyyy-MM-dd HH:mm
     */
    public static final String DATE_TIME_24HR_STYLE = "yyyy-MM-dd HH:mm";

    /**
     * yyyy-MM-dd hh:mm
     */
    public static final String DATE_TIME_12HR_STYLE = "yyyy-MM-dd hh:mm";

    /**
     * yyyy-MM-dd
     */
    public static final String DATE_STYLE = "yyyy-MM-dd";

    /**
     * HH:mm
     */
    public static final String TIME_24HR_STYLE = "HH:mm";

    /**
     * hh:mm
     */
    public static final String TIME_12HR_STYLE = "hh:mm";

    /**
     * 时间进制
     */
    private enum HR {
        HR24,
        HR12,
    }

    private static String formatDateToFULLHRStyle(HR hr, String spacer, Date date) {
        if (null == date) {
            return "";
        }
        if (null == spacer) {
            spacer = "-";
        }
        String hh = "HH";
        if (hr == HR.HR12) {
            hh = "hh";
        }
        return (new SimpleDateFormat("yyyy" + spacer + "MM" + spacer + "dd " + hh + ":mm:ss").format(date));
    }

    /**
     * Calendar
     */
    private static Calendar cal = Calendar.getInstance();

    private static void setTime() {
        cal.setTime(now());
    }

    private static Date now() {
        return (new Date());
    }

    /*=============================================TO int ===================*/

    /**
     * 年
     *
     * @return
     */
    public static int year() {
        setTime();
        return cal.get(Calendar.YEAR);
    }

    /**
     * 月
     */
    public static int month() {
        setTime();
        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     * 某月的第几日
     *
     * @return
     */
    public static int dayOfMonth() {
        setTime();
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 某年的第几天
     *
     * @return
     */
    public static int dayOfYear() {
        setTime();
        return cal.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 某周的第几天
     * SUN	= 7 MON = 1	TUE = 2	WED = 3
     * THU = 4	FRI = 5	SAT = 6
     *
     * @return
     */
    public static int dayOfWeek() {
        setTime();
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayofweek == 0) {
            dayofweek = 7;
        }
        return dayofweek;
    }

    /*=============================================TO String===================*/

    /**
     * 格式化时间
     *
     * @param style
     * @param date
     * @return
     * @see DateFormat
     */
    public static String formatDate(int style, Date date) {
        if (date == null) {
            return "";
        }
        if (style < 0) {
            style = DateFormat.DEFAULT;
        }
        return DateFormat.getDateInstance(style).format(date);
    }

    /**
     * 格式化时间
     *
     * @param style
     * @return
     * @see DateFormat
     */
    public static String formatNow(int style) {
        return formatDate(style, now());
    }

    /**
     * 格式化时间为DateFormat.FULL风格的String => 2015年5月4日 星期一
     *
     * @param date
     * @return
     */
    public static String formatDateToFull(Date date) {
        return formatDate(DateFormat.FULL, date);
    }

    /**
     * 格式化当前时间为DateFormat.FULL风格的String => 2015年5月4日 星期一
     *
     * @return
     */
    public static String formatNowToFull() {
        return formatNow(DateFormat.FULL);
    }

    /**
     * 格式化时间为DateFormat.MEDIUM风格的String => 2015-5-4
     *
     * @param date
     * @return
     */
    public static String formatDateToMedium(Date date) {
        return formatDate(DateFormat.MEDIUM, date);
    }

    /**
     * 格式化当前时间为DateFormat.MEDIUM风格的String => 2015-5-4
     *
     * @return
     */
    public static String formatNowToMedium() {
        return formatNow(DateFormat.MEDIUM);
    }

    /**
     * 格式化时间为DateFormat.SHORT风格的String => 15-5-4
     *
     * @param date
     * @return
     */
    public static String formatDateToShort(Date date) {
        return formatDate(DateFormat.SHORT, date);
    }

    /**
     * 格式化当前时间为DateFormat.SHORT风格的String => 15-5-4
     */
    public static String formatNowToShort() {
        return formatNow(DateFormat.SHORT);
    }

    /**
     * 格式化时间
     *
     * @param style 格式风格
     * @param date
     * @return
     */
    public static String formatDateToStyle(String style, Date date) {
        if (null == date) {
            return "";
        }
        if (null == style) {
            style = DateTimeKit.FULL_DATE_24HR_STYLE;
        }
        return (new SimpleDateFormat(style).format(date));
    }

    /**
     * 格式化时间
     *
     * @param style 格式风格
     * @return
     */
    public static String formatNowToStyle(String style) {
        return formatDateToStyle(style, now());
    }

    /**
     * 格式化时间为Full24HR，使用spacer间隔。如：spacer为/，在return=> 2015/05/05 10:20:20
     *
     * @param spacer 间隔号
     * @param date
     * @return
     */
    public static String formatDateToFULL24HRStyle(String spacer, Date date) {
        return formatDateToFULLHRStyle(HR.HR24, spacer, date);
    }

    /**
     * 格式化当前时间为Full24HR，使用spacer间隔。如：spacer为/，在return=> 2015/05/05 10:20:20
     *
     * @param spacer 间隔号
     * @return
     */
    public static String formatNowToFULL24HRStyle(String spacer) {
        return formatDateToFULL24HRStyle(spacer, now());
    }

    /**
     * 格式化时间为Full12HR，使用spacer间隔。如：spacer为/，在return=> 2015/05/05 18:20:20
     *
     * @param spacer 间隔号
     * @param date
     * @return
     */
    public static String formatDateToFULL12HRStyle(String spacer, Date date) {
        return formatDateToFULLHRStyle(HR.HR12, spacer, date);
    }

    /**
     * 格式化当前时间为Full12HR，使用spacer间隔。如：spacer为/，在return=> 2015/05/05 18:20:20
     *
     * @param spacer 间隔号
     */
    public static String formatNowToFULL12HRStyle(String spacer) {
        return formatDateToFULL12HRStyle(spacer, now());
    }

    /**
     * 格式化日期为时间格式
     *
     * @param hr
     * @param date
     */
    public static String formatDateToHRStyle(HR hr, Date date) {
        if (null == date) {
            return "";
        }
        String style = DateTimeKit.TIME_24HR_STYLE;
        if (hr == HR.HR12) {
            style = DateTimeKit.TIME_12HR_STYLE;
        }
        return formatDateToStyle(style, date);
    }

    /**
     * 格式化当前日期为时间格式
     *
     * @param hr
     * @return
     */
    public static String formatNowToHRStyle(HR hr) {
        return formatDateToHRStyle(hr, now());
    }

    /**
     * 格式化时间戳为制定的style
     *
     * @param style
     * @param unixtime
     * @return
     */
    public static String formatUnixTime(String style, BigInteger unixtime) {
        SimpleDateFormat sdf = new SimpleDateFormat(style);
        return sdf.format(unixtime);
    }

    /**
     * 格式化时间戳为制定的short 24hour style
     *
     * @param unixtime
     * @return
     */
    public static String formatUnixTimeToShort24HRStyle(BigInteger unixtime) {
        return DateTimeKit.formatUnixTime(DateTimeKit.DATE_TIME_24HR_STYLE, unixtime);
    }

    /**
     * 格式化时间戳为制定的24hour style
     *
     * @param unixtime
     * @return
     */
    public static String formatUnixTimeTo24HRStyle(BigInteger unixtime) {
        return DateTimeKit.formatUnixTime(DateTimeKit.FULL_DATE_24HR_STYLE, unixtime);
    }

    /**
     * 格式化时间戳为制定的short 12hour style
     *
     * @param unixtime
     * @return
     */
    public static String formatUnixTimeToShort12HRStyle(BigInteger unixtime) {
        return DateTimeKit.formatUnixTime(DateTimeKit.DATE_TIME_12HR_STYLE, unixtime);
    }

    /**
     * 格式化时间戳为制定的12hour style
     *
     * @param unixtime
     * @return
     */
    public static String formatUnixTimeTo12HRStyle(BigInteger unixtime) {
        return DateTimeKit.formatUnixTime(DateTimeKit.FULL_DATE_12HR_STYLE, unixtime);
    }

    /*=============================================TO Date ===================*/

    /**
     * 时间转换
     *
     * @param dateString
     */
    public static Date dateStringToDate(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat(DateTimeKit.FULL_DATE_24HR_STYLE);
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
        }
        return date;
    }

    /**
     * BigInteger 转Date
     *
     * @param unixtime
     */
    public static Date formatUnixTimeToDate(BigInteger unixtime) {
        return dateStringToDate(formatUnixTime(DateTimeKit.FULL_DATE_24HR_STYLE, unixtime));
    }

    /*=============================================TO long ===================*/

    /**
     * 获取本月的第一天的时间戳
     *
     * @return
     */
    public static Long getMonth1stDay() {
        cal.setTimeInMillis(System.currentTimeMillis());
        cal.set(Calendar.DATE, 1);
        return cal.getTimeInMillis();
    }

    /**
     * 获取本月的最后一天的时间戳
     */
    public static Long getMonthLastDay() {
        cal.setTimeInMillis(System.currentTimeMillis());
        cal.set(Calendar.DATE, 1);
        cal.roll(Calendar.DATE, -1);
        return cal.getTimeInMillis();
    }

    /**
     * 获取给定时间的周一的长整形表示
     */
    public static Long getMonday() {
        cal.setTimeInMillis(System.currentTimeMillis());
        cal.add(Calendar.DATE, -dayOfWeek() + 1);
        return cal.getTimeInMillis();
    }

    /**
     * 获取给定时间的周日的长整形表示
     */
    public static Long getSunday() {
        cal.setTimeInMillis(System.currentTimeMillis());
        cal.add(Calendar.DATE, -dayOfWeek() + 7);
        return cal.getTimeInMillis();
    }

    /**
     * 获取day天之后的UnixTime
     *
     * @param day
     */
    public static Long getUnixTimeAfterDay(int day) {
        return day * 24 * 60 * 60L + System.currentTimeMillis() / 1000;
    }

    /**
     * 获取day天之前的UnixTime
     *
     * @param day
     * @return
     */
    public static Long getUnixTimeBeforeDay(int day) {
        return System.currentTimeMillis() / 1000 - day * 24 * 60 * 60L;
    }

    /**
     * 获取当前的时间的UnixTime
     */
    public static Long getCurrentUnixTime() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 把time转为 unix time 到毫秒
     *
     * @param time
     */
    public static BigInteger getUnixTimeMillis(BigInteger time) {
        return time.multiply(new BigInteger("1000"));
    }

    /**
     * 把time转为 unix time 到毫秒
     *
     * @param time
     */
    public static BigInteger getUnixTimeMillis(Long time) {
        return new BigInteger(String.valueOf(time * 1000L));
    }
}
