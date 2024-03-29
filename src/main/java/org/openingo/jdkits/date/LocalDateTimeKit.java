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

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;

/**
 * LocalDateTimeKit
 *
 * @author Qicz
 */
public final class LocalDateTimeKit {

    private LocalDateTimeKit() {
    }

    /**
     * 日期之后的某个日期
     *
     * @param dateTime
     * @param plusVal
     * @param unit
     */
    private static LocalDateTime plus(LocalDateTime dateTime, long plusVal, TemporalUnit unit) {
        LocalDate localDate = dateTime.toLocalDate();
        localDate = localDate.plus(plusVal, unit);
        return LocalDateTime.of(localDate, dateTime.toLocalTime());
    }

    /**
     * hours小时之后的时间日期
     *
     * @param dateTime
     * @param hours
     */
    private static LocalDateTime plusHours(LocalDateTime dateTime, long hours) {
        LocalTime localTime = dateTime.toLocalTime();
        localTime = localTime.plusHours(hours);
        return LocalDateTime.of(dateTime.toLocalDate(), localTime);
    }

    /**
     * minutes分钟之后的时间日期
     *
     * @param dateTime
     * @param minutes
     */
    private static LocalDateTime plusMinutes(LocalDateTime dateTime, long minutes) {
        LocalTime localTime = dateTime.toLocalTime();
        localTime = localTime.plusMinutes(minutes);
        return LocalDateTime.of(dateTime.toLocalDate(), localTime);
    }

    /**
     * 当前日期之后的某个日期
     *
     * @param plusVal
     * @param unit
     */
    private static LocalDateTime plus(long plusVal, TemporalUnit unit) {
        return plus(LocalDateTime.now(), plusVal, unit);
    }

    /**
     * hours小时之后的时间日期
     *
     * @param hours
     */
    private static LocalDateTime plusHours(long hours) {
        return plusHours(LocalDateTime.now(), hours);
    }

    /**
     * minutes分钟之后的时间日期
     *
     * @param minutes
     */
    private static LocalDateTime plusMinutes(long minutes) {
        return plusMinutes(LocalDateTime.now(), minutes);
    }

    /**
     * years年之后的时间日期
     *
     * @param years
     */
    public static LocalDateTime afterYears(long years) {
        return plus(years, ChronoUnit.YEARS);
    }

    /**
     * months月之后的时间日期
     *
     * @param months
     */
    public static LocalDateTime afterMonths(long months) {
        return plus(months, ChronoUnit.MONTHS);
    }

    /**
     * weeks周之后的时间日期
     *
     * @param weeks
     */
    public static LocalDateTime afterWeeks(long weeks) {
        return plus(weeks, ChronoUnit.WEEKS);
    }

    /**
     * days天之后的时间日期
     *
     * @param days
     */
    public static LocalDateTime afterDays(long days) {
        return plus(days, ChronoUnit.DAYS);
    }

    /**
     * hours小时之后的时间日期
     *
     * @param hours
     */
    public static LocalDateTime afterHours(long hours) {
        return plusHours(hours);
    }

    /**
     * minutes分钟之后的时间日期
     *
     * @param minutes
     */
    public static LocalDateTime afterMinutes(long minutes) {
        return plusMinutes(minutes);
    }

    /**
     * years年之前的时间日期
     *
     * @param years
     */
    public static LocalDateTime beforeYears(long years) {
        return plus(-years, ChronoUnit.YEARS);
    }

    /**
     * months月之前的时间日期
     *
     * @param months
     */
    public static LocalDateTime beforeMonths(long months) {
        return plus(-months, ChronoUnit.MONTHS);
    }

    /**
     * weeks周之前的时间日期
     *
     * @param weeks
     */
    public static LocalDateTime beforeWeeks(long weeks) {
        return plus(-weeks, ChronoUnit.WEEKS);
    }

    /**
     * days天之前的时间日期
     *
     * @param days
     */
    public static LocalDateTime beforeDays(long days) {
        return plus(-days, ChronoUnit.DAYS);
    }

    /**
     * hours小时之前的时间日期
     *
     * @param hours
     */
    public static LocalDateTime beforeHours(long hours) {
        return plusHours(-hours);
    }

    /**
     * minutes分钟之前的时间日期
     *
     * @param minutes
     */
    public static LocalDateTime beforeMinutes(long minutes) {
        return plusMinutes(-minutes);
    }

    /**
     * years年之后的时间日期
     *
     * @param dateTime
     * @param years
     */
    public static LocalDateTime afterYears(LocalDateTime dateTime, long years) {
        return plus(dateTime, years, ChronoUnit.YEARS);
    }

    /**
     * months月之后的时间日期
     *
     * @param dateTime
     * @param months
     */
    public static LocalDateTime afterMonths(LocalDateTime dateTime, long months) {
        return plus(dateTime, months, ChronoUnit.MONTHS);
    }

    /**
     * weeks周之后的时间日期
     *
     * @param dateTime
     * @param weeks
     */
    public static LocalDateTime afterWeeks(LocalDateTime dateTime, long weeks) {
        return plus(dateTime, weeks, ChronoUnit.WEEKS);
    }

    /**
     * days天之后的时间日期
     *
     * @param dateTime
     * @param days
     */
    public static LocalDateTime afterDays(LocalDateTime dateTime, long days) {
        return plus(dateTime, days, ChronoUnit.DAYS);
    }

    /**
     * hours小时之后的时间日期
     *
     * @param dateTime
     * @param hours
     */
    public static LocalDateTime afterHours(LocalDateTime dateTime, long hours) {
        return plusHours(dateTime, hours);
    }

    /**
     * minutes分钟之后的时间日期
     *
     * @param dateTime
     * @param minutes
     */
    public static LocalDateTime afterMinutes(LocalDateTime dateTime, long minutes) {
        return plusMinutes(dateTime, minutes);
    }

    /**
     * years年之前的时间日期
     *
     * @param dateTime
     * @param years
     */
    public static LocalDateTime beforeYears(LocalDateTime dateTime, long years) {
        return plus(dateTime, -years, ChronoUnit.YEARS);
    }

    /**
     * months月之前的时间日期
     *
     * @param dateTime
     * @param months
     */
    public static LocalDateTime beforeMonths(LocalDateTime dateTime, long months) {
        return plus(dateTime, -months, ChronoUnit.MONTHS);
    }

    /**
     * weeks周之前的时间日期
     *
     * @param dateTime
     * @param weeks
     */
    public static LocalDateTime beforeWeeks(LocalDateTime dateTime, long weeks) {
        return plus(dateTime, -weeks, ChronoUnit.WEEKS);
    }

    /**
     * days天之前的时间日期
     *
     * @param dateTime
     * @param days
     */
    public static LocalDateTime beforeDays(LocalDateTime dateTime, long days) {
        return plus(dateTime, -days, ChronoUnit.DAYS);
    }

    /**
     * hours小时之前的时间日期
     *
     * @param dateTime
     * @param hours
     */
    public static LocalDateTime beforeHours(LocalDateTime dateTime, long hours) {
        return plusHours(dateTime, -hours);
    }

    /**
     * minutes分钟之前的时间日期
     *
     * @param dateTime
     * @param minutes
     */
    public static LocalDateTime beforeMinutes(LocalDateTime dateTime, long minutes) {
        return plusMinutes(dateTime, -minutes);
    }

    /**
     * Date转换为LocalDateTime
     *
     * @param date
     */
    public static LocalDateTime date2LocalDateTime(Date date) {
        Instant instant = date.toInstant();//An instantaneous point on the time-line.(时间线上的一个瞬时点。)
        ZoneId zoneId = ZoneId.systemDefault();//A time-zone ID, such as {@code Europe/Paris}.(时区)
        return instant.atZone(zoneId).toLocalDateTime();
    }

    /**
     * LocalDateTime转换为Date
     *
     * @param localDateTime
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);//Combines this date-time with a time-zone to create a  ZonedDateTime.
        return Date.from(zdt.toInstant());
    }

    /**
     * LocalDateTime装为String
     * @param localDateTime 待转换的localDateTime
     * @param pattern 完整的年月日时分秒格式
     */
    public static String localDateTime2String(LocalDateTime localDateTime, String pattern) {
        return DateTimeFormatter.ofPattern(pattern).format(localDateTime.atZone(ZoneId.systemDefault()));
    }

    /**
     * string转为LocalDateTime
     * @param string 待转换的字符串
     * @param pattern 完整的年月日时分秒格式
     */
    public static LocalDateTime string2LocalDateTime(String string, String pattern) {
        return LocalDateTime.parse(string, DateTimeFormatter.ofPattern(pattern)).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * localDate转String
     * @param localDate 待转的localDate
     * @param pattern 完整的年月日格式
     */
    public static String localDate2String(LocalDate localDate, String pattern) {
        return DateTimeFormatter.ofPattern(pattern).format(localDate);
    }

    /**
     * string转LocalDate
     * @param string 待转换的字符串
     * @param pattern 完整的年月日格式
     */
    public static LocalDate string2LocalDate(String string, String pattern) {
        return LocalDate.parse(string, DateTimeFormatter.ofPattern(pattern));
    }
}