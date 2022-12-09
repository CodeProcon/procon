package com.huangpuguang.common.core.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 时间工具类
 *
 * @author procon
 */
@Slf4j
public class ProconDateUtils extends org.apache.commons.lang3.time.DateUtils
{


    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String PARSE_EXCEPTION_MSG = "日期转换异常:{}";

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};


    /**
     * 获取当前Date型日期
     *
     * @return Date() 当前日期
     */
    public static Date getNowDate()
    {
        return new Date();
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     *
     * @return String
     */
    public static String getDate()
    {
        return dateTimeNow(DatePattern.NORM_DATE_PATTERN);
    }


    public static String dateTimeNow(final String format)
    {
        return parseDateToStr(format, new Date());
    }

    public static String parseDateToStr(final String format, final Date date)
    {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static String datePath()
    {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * 日期路径 即年/月/日 如20180808
     */
    public static String dateTime()
    {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyyMMdd");
    }

    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseDate(Object str)
    {
        if (str == null)
        {
            return null;
        }
        try
        {
            return parseDate(str.toString(), parsePatterns);
        }
        catch (ParseException e)
        {
            return null;
        }
    }

    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate()
    {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date endDate, Date nowDate)
    {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        return day + "天" + hour + "小时" + min + "分钟";
    }

    /**
     * 获得指定日期之后一段时期的日期。例如某日期之后3天的日期等。
     * @param origDate 基准日期
     * @param amount 基准日期
     * @param timeUnit 时间单位，如年、月、日等。用Calendar中的常量代表
     * @return 指定日期之后一段时期的日期
     */
    public static Date dateAfter(Date origDate, int amount, int timeUnit) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(origDate);
        calendar.add(timeUnit, amount);
        return calendar.getTime();
    }

    /**
     * 增加 LocalDateTime ==> Date
     */
    public static Date toDate(LocalDateTime temporalAccessor)
    {
        ZonedDateTime zdt = temporalAccessor.atZone(ZoneId.systemDefault());
        return Date.from(zdt.toInstant());
    }

    /**
     * 增加 LocalDate ==> Date
     */
    public static Date toDate(LocalDate temporalAccessor)
    {
        LocalDateTime localDateTime = LocalDateTime.of(temporalAccessor, LocalTime.of(0, 0, 0));
        ZonedDateTime zdt = localDateTime.atZone(ZoneId.systemDefault());
        return Date.from(zdt.toInstant());
    }

    private static final String SDF_YM = "yyyy-MM";
    private static final String SDF_Y = "yyyy";



    /**
     * 获取时间段内每个月的最后一天
     *
     * @param startTime:
     * @param endTime:
     * @return List<String>
     *
     */
    public static List<String> getLastDayMonthList(String startTime, String endTime) {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(SDF_YM);
        String monthStartTime = startTime.substring(0, 7);
        String monthEndTime = endTime.substring(0, 7);
        String monthDate = DateUtil.endOfMonth(new Date()).toDateStr();

        // 存储每月最后一天日期结果集合
        List<String> dateList = new ArrayList<>();
        // 存储某时间段中的各个月份
        List<String> monthDateList = new ArrayList<>();

        if (!monthStartTime.equals(monthDate)) {
            try {
                calendar.setTime(sdf.parse(monthStartTime));
                monthDateList.add(monthStartTime);
                while (true) {
                    calendar.add(Calendar.MONTH, 1);
                    if (sdf.parse(monthEndTime).after(calendar.getTime())) {
                        monthDateList.add(sdf.format(calendar.getTime()));
                    } else {
                        break;
                    }
                }

            } catch (ParseException e) {
                log.info(PARSE_EXCEPTION_MSG,e.getMessage(),e);
            }

            if (CollUtil.isEmpty(monthDateList)) {
                return dateList;
            }
            //获取每月最后一天
            for (String month : monthDateList) {
                String endOfMonth = DateUtil.endOfMonth(DateUtil.parse(month, SDF_YM)).toDateStr();
                dateList.add(endOfMonth);
            }
        }

        return dateList;
    }


    /**
     * 获取时间段内每季度的最后一天
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return List<String>
     */
    public static List<String> getLastDayQuarterList(String startTime, String endTime) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(SDF_YM);
        String quarterStartTime = startTime.substring(0, 7);
        String quarterEndTime = endTime.substring(0, 7);
        String quarterDate = DateUtil.endOfQuarter(new Date()).toDateStr();
        // 存储每季度最后一天日期结果集合
        List<String> quarterList = new ArrayList<>();
        // 存储某时间段中的各个季度
        List<String> quarterDateList = new ArrayList<>();

        if (!quarterStartTime.equals(quarterDate)) {
            try {
                calendar.setTime(sdf.parse(quarterStartTime));
                String endOfStartTimeQuarter = DateUtil.endOfQuarter(DateUtil.parse(quarterStartTime, SDF_YM)).toDateStr();
                quarterDateList.add(endOfStartTimeQuarter);
                while (true) {
                    //一个季度三个月
                    calendar.add(Calendar.MONTH, 3);
                    if (sdf.parse(quarterEndTime).after(calendar.getTime())) {
                        quarterDateList.add(sdf.format(calendar.getTime()));
                    } else {
                        break;
                    }
                }

            } catch (ParseException e) {
                log.info(PARSE_EXCEPTION_MSG,e.getMessage(),e);
            }

            if (CollUtil.isEmpty(quarterDateList)) {
                return quarterList;
            }
            for (String quarter : quarterDateList) {
                DateTime endOfQuarter = DateUtil.endOfQuarter(DateUtil.parse(quarter,SDF_YM));
                quarterList.add(endOfQuarter.toDateStr());
            }
        }
        return quarterList;
    }

    /**
     * 获取时间段内每年的最后一天
     * @param startTime:开始时间
     * @param endTime:结束时间
     * @return List<String>
     */
    public static List<String> getLastDayYearList(String startTime, String endTime) {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(SDF_Y);
        String yearStartTime = startTime.substring(0, 4);
        String yearEndTime = endTime.substring(0, 4);
        String yearStr = DateUtil.endOfYear(new Date()).toDateStr();
        String year = yearStr.substring(0, 4);
        List<String> dateList = new ArrayList<>();
        List<String> yearDateList = new ArrayList<>();
        if (!yearStartTime.equals(year)) {
            try {
                // 设置开始时间
                calendar.setTime(sdf.parse(yearStartTime));
                yearDateList.add(yearStartTime);
                while (true) {
                    calendar.add(Calendar.YEAR, 1);
                    if (sdf.parse(yearEndTime).after(calendar.getTime())) {
                        yearDateList.add(sdf.format(calendar.getTime()));
                    } else {
                        break;
                    }
                }
            } catch (ParseException e) {
                log.info(PARSE_EXCEPTION_MSG,e.getMessage(),e);
            }
            if (CollUtil.isEmpty(yearDateList)) {
                return dateList;
            }
            for (String yearD : yearDateList) {
                String endOfYear = DateUtil.endOfYear(DateUtil.parse(yearD, SDF_Y)).toDateStr();
                dateList.add(endOfYear);
            }
        }
        return dateList;


    }



    /**
     * 每七天分组
     * @param startTime:开始时间
     * @param endTime:结束时间
     * @return List<String>
     */
    public static List<String> getWeekList(String startTime, String endTime){
        DateTime startDate = DateUtil.parseDate(startTime);
        DateTime endDate = DateUtil.parseDate(endTime);
        long betweenWeek = DateUtil.betweenWeek(startDate, endDate, true);
        List<String> weekList = new ArrayList<>();
        weekList.add(startTime);
        for (int i = 0; i < betweenWeek;i++){
            DateTime dateTime = DateUtil.offsetWeek(startDate, i + 1);
            weekList.add(DateUtil.formatDate(dateTime));
        }
        return weekList;
    }


    /**
     * 获取时间列表
     * @param dBegin:开始时间
     * @param dEnd:结束时间
     * @return List<String>
     */
    public static List<String> getDateList(String dBegin, String dEnd) {
        //装返回的日期集合容器
        List<String> datelist = new ArrayList<>();
        //日期工具类准备
        SimpleDateFormat format = new SimpleDateFormat(DatePattern.NORM_DATE_PATTERN);

        try {

            Calendar calBegin = Calendar.getInstance();
            calBegin.setTime(format.parse(dBegin));
            Calendar calEnd = Calendar.getInstance();
            calEnd.setTime(format.parse(dEnd));
            datelist.add(format.format(calBegin.getTime()));
            while (format.parse(dEnd).after(calBegin.getTime())) {
                calBegin.add(Calendar.DAY_OF_MONTH, 1);

                datelist.add(format.format(calBegin.getTime()));
            }
        } catch (ParseException e) {
            log.error(PARSE_EXCEPTION_MSG,e.getMessage(),e);
        }
        return datelist;
    }

    /**
     * 判断日期是否是周末
     * @param date 日期
     * @return boolean
     */
    public static boolean isWeekend(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY;
    }
}
