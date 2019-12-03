package com.springboot.demo.localdate;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class LocalDateDemo {

    public static void main(String[] args) {

        //2019-10-30
        LocalDate localDate = LocalDate.now();
        //2019-09-10
        LocalDate localDate1 = LocalDate.of(2019, 9, 10);
        //2019-10-30T19:58:29.519
        LocalDateTime localDateTime = LocalDateTime.now();
        //19:58:29.520
        LocalTime localTime = LocalTime.now();

        //获取年:2019
        int year = localDate.getYear();
        //获取年:2019
        int year1 = localDate.get(ChronoField.YEAR);
        //获取月:OCTOBER
        Month month = localDate.getMonth();
        //获取月:10
        int month1 = localDate.get(ChronoField.MONTH_OF_YEAR);
        //获取天:30
        int day = localDate.getDayOfMonth();
        //获取天:30
        int day1 = localDate.get(ChronoField.DAY_OF_MONTH);
        //获取星期:WEDNESDAY
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        int dayOfMonth = localDate.getDayOfMonth();
        int dayOfYear = localDate.getDayOfYear();
        //获取星期:3
        int dayOfWeek1 = localDate.get(ChronoField.DAY_OF_WEEK);

        /**
         * LocalDateTime
         * 获取年月日时分秒，等于LocalDate+LocalTime
         */
        //2019-10-30T20:01:30.666
        LocalDateTime localDateTime0 = LocalDateTime.now();
        //2019-09-10T14:46:56
        LocalDateTime localDateTime1 = LocalDateTime.of(2019, Month.SEPTEMBER, 10, 14, 46, 56);
        //2019-10-30T20:01:30.665
        LocalDateTime localDateTime2 = LocalDateTime.of(localDate, localTime);
        //2019-10-30T20:01:30.665
        LocalDateTime localDateTime3 = localDate.atTime(localTime);
        //2019-10-30T20:01:30.665
        LocalDateTime localDateTime4 = localTime.atDate(localDate);

        /**
         * 获取LocalDate
         */
        //2019-10-30
        LocalDate localDate2 = localDateTime.toLocalDate();

        /**
         * 获取LocalTime
         */
        //20:03:45.084
        LocalTime localTime2 = localDateTime.toLocalTime();

        /**
         * 获取秒数
         */
        Instant instant = Instant.now();
        //秒数:1572437086
        long currentSecond = instant.getEpochSecond();
        //毫秒:1572437086198
        long currentMilli = instant.toEpochMilli();
        long nowMilli = System.currentTimeMillis();//更方便

        /**
         * 日期计算
         */
        //2019-09-10T14:46:56
        LocalDateTime localDateTime11 = LocalDateTime.of(2019, Month.SEPTEMBER, 10,
                14, 46, 56);
        //增加一年:2020-09-10T14:46:56
        LocalDateTime localDateTime12 = localDateTime11.plusYears(1);
        //增加一年:2020-09-10T14:46:56
        LocalDateTime localDateTime13 = localDateTime11.plus(1, ChronoUnit.YEARS);
        //减少一个月:2019-08-10T14:46:56
        LocalDateTime localDateTime14 = localDateTime11.minusMonths(1);
        //减少一个月:2019-08-10T14:46:56
        LocalDateTime localDateTime15 = localDateTime11.minus(1, ChronoUnit.MONTHS);

        /**
         * 通过With修改值
         */
        //修改年2020:2020-10-30T20:11:54.369
        LocalDateTime localDateTime16 = localDateTime.withYear(2020);
        //修改为2022:2022-10-30T20:11:54.369
        LocalDateTime localDateTime17 = localDateTime.with(ChronoField.YEAR, 2022);

        /**
         * 格式化时间
         */
        //2019-09-10
        LocalDate localDate11 = LocalDate.of(2019, 9, 10);
        //20191030
        String s1 = localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
        //2019-10-30
        String s2 = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        //自定义格式化
        DateTimeFormatter dateTimeFormatter =   DateTimeFormatter.ofPattern("dd/MM/yyyy");
        //30/10/2019
        String s3 = localDate.format(dateTimeFormatter);

        //解析时间
        //2019-09-10
        LocalDate localDate12 = LocalDate.parse("20190910", DateTimeFormatter.BASIC_ISO_DATE);
        //2019-09-10
        LocalDate localDate13= LocalDate.parse("2019-09-10", DateTimeFormatter.ISO_LOCAL_DATE);

    }
}
