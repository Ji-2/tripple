package com.example.tripple.common.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 날짜관련 Util클래스
 */
public class DateUtil {


    /**
     * @param date1
     * @param date2
     * @return 월 차이 계산     */
    public static int getMonthsDifference(LocalDate date1, LocalDate date2){


        /* 해당년도에 12를 곱해서 총 개월수를 구하고 해당 월을 더 한다. */
        int month1 = date1.getYear()* 12 + date1.getMonthValue();
        int month2 = date2.getYear() * 12 + date2.getMonthValue();

        return month2 - month1;
    }

    /**
     * @param format
     * @return 오늘 날짜 String 원하는 포멧 반환
     */
    public static String getTodayDate(String format) {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * @param format
     * @return 현재 시간 String 원하는 포멧 반환
     */
    public static String getTodayTime(String format) {
        return LocalTime.now().format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * @param format
     * @return 오늘 날짜 String 원하는 포멧 반환
     */
    public static String dateFormatTran(String format, String date) {
        return LocalDate.parse(date).format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * @param type - 1:일, 2:주, 3:월, 4:년
     * @param minus - 뺄 날짜
     * @param format - 반환 포멧
     * @return 오늘 날짜로 부터 년월주일 뺀 날짜 반환
     *
     */
    public static String getTodayMinusDate(int type, int minus, String format) {

        LocalDate today = LocalDate.now();
        if (type == 1) {
            today = today.minusDays(minus);
        } else if (type == 2) {
            today = today.minusWeeks(minus);
        } else if (type == 3) {
            today = today.minusMonths(minus);
        } else if (type == 4) {
            today = today.minusYears(minus);
        }

        return today.format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * @param type - 1:일, 2:주, 3:월, 4:년
     * @param minus - 뺄 날짜
     * @param format - 반환 포멧
     * @param searchDate - 날짜 지정
     * @return 지정한 날짜로 부터 년월주일 뺀 날짜 반환
     *
     */
    public static String getMinusDate(int type, int minus, String format, String searchDate) {

        LocalDate date = LocalDate.parse(searchDate);
        if (type == 1) {
            date = date.minusDays(minus);
        } else if (type == 2) {
            date = date.minusWeeks(minus);
        } else if (type == 3) {
            date = date.minusMonths(minus);
        } else if (type == 4) {
            date = date.minusYears(minus);
        }

        return date.format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * @param type - 1:일, 2:주, 3:월, 4:년
     * @param minus - 더할 날짜
     * @param format - 반환 포멧
     * @param searchDate - 날짜 지정
     * @return 지정한 날짜로 부터 년월주일 더한 날짜 반환
     *
     */
    public static String getPlusDate(int type, int minus, String format, String searchDate) {

        LocalDate date = LocalDate.parse(searchDate);
        if (type == 1) {
            date = date.plusMonths(minus);
        } else if (type == 2) {
            date = date.plusWeeks(minus);
        } else if (type == 3) {
            date = date.plusMonths(minus);
        } else if (type == 4) {
            date = date.plusYears(minus);
        }

        return date.format(DateTimeFormatter.ofPattern(format));
    }
}
