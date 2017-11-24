package com.ddxq.boss.base.util.time;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

/** 
* @author jkc  E-mail: 754438390@qq.com
* @version 创建时间：2016年6月2日 上午10:18:04 
* 类说明 
*/
@Component("timeUtil")
public class TimeUtil {
        /**
         * 获取当前时间
         * 
         * @return
         */
        public static Date now() {
                return new Date();
        }

        /**
         * 新建一个日期
         * 
         * @param year
         * @param month
         * @param day
         * @return
         */
        public static Date newDate(int year, int month, int day) {
                Calendar c = Calendar.getInstance();
                c.clear();
                c.set(year, month - 1, day);
                return c.getTime();
        }

	    /**
	     * 新建一个日期
	     * 
	     * @param year
	     * @param month
	     * @param day
	     * @param hour
	     * @param minute
	     * @param second
	     * @return
	     */
	    public static Date newDate(int year, int month, int day, int hour,
	                    int minute, int second) {
	            Calendar c = Calendar.getInstance();
	            c.clear();
	            c.set(year, month - 1, day, hour, minute, second);
	            return c.getTime();
	    }

        /**
         * 指定时间的小时开始
         * 
         * @param date
         * @return ... HH:00:00.000
         */
        public static Date hourBegin(final Date date) {
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                c.set(Calendar.MINUTE, 0);
                c.set(Calendar.SECOND, 0);
                c.set(Calendar.MILLISECOND, 0);
                return c.getTime();
        }

        /**
         * 指定时间的小时最后一毫秒
         * 
         * @param date
         * @return ... HH:59:59.999
         */
        public static Date hourEnd(final Date date) {
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                c.set(Calendar.MINUTE, 59);
                c.set(Calendar.SECOND, 59);
                c.set(Calendar.MILLISECOND, 999);
                return c.getTime();
        }

        /**
         * 获取指定时间的那天 00:00:00.000 的时间
         * 
         * @param date
         * @return
         */
        public static Date dayBegin(final Date date) {
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                c.set(Calendar.HOUR_OF_DAY, 0);
                c.set(Calendar.MINUTE, 0);
                c.set(Calendar.SECOND, 0);
                c.set(Calendar.MILLISECOND, 0);
                return c.getTime();
        }

        /**
         * 获取今天 00:00:00.000 的时间
         * 
         * @param date
         * @return
         */
        public static Date dayBegin() {
                return dayBegin(now());
        }

        /**
         * 获取指定时间的那天 23:59:59.999 的时间
         * 
         * @param date
         * @return
         */
        public static Date dayEnd(final Date date) {
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                c.set(Calendar.HOUR_OF_DAY, 23);
                c.set(Calendar.MINUTE, 59);
                c.set(Calendar.SECOND, 59);
                c.set(Calendar.MILLISECOND, 999);
                return c.getTime();
        }

        /**
         * 获取今天 23:59:59.999 的时间
         * 
         * @param date
         * @return
         */
        public static Date dayEnd() {
                return dayEnd(now());
        }

        /**
         * 月首
         * 
         * @param date
         * @return
         */
        public static Date monthBegin(final Date date) {
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                c.set(Calendar.DAY_OF_MONTH, 1);
                c.set(Calendar.HOUR_OF_DAY, 0);
                c.set(Calendar.MINUTE, 0);
                c.set(Calendar.SECOND, 0);
                c.set(Calendar.MILLISECOND, 0);
                return c.getTime();
        }

        /**
         * 月末
         * 
         * @param date
         * @return
         */
        public static Date monthEnd(final Date date) {
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
                c.set(Calendar.HOUR_OF_DAY, 23);
                c.set(Calendar.MINUTE, 59);
                c.set(Calendar.SECOND, 59);
                c.set(Calendar.MILLISECOND, 999);
                return c.getTime();
        }

        /**
         * 年首
         * 
         * @param date
         * @return
         */
        public static Date yearBegin(final Date date) {
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                c.set(Calendar.DAY_OF_YEAR, 1);
                c.set(Calendar.HOUR_OF_DAY, 0);
                c.set(Calendar.MINUTE, 0);
                c.set(Calendar.SECOND, 0);
                c.set(Calendar.MILLISECOND, 0);
                return c.getTime();
        }

        /**
         * 年末
         * 
         * @param date
         * @return
         */
        public static Date yearEnd(final Date date) {
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                c.set(Calendar.DAY_OF_YEAR, c.getActualMaximum(Calendar.DAY_OF_YEAR));
                c.set(Calendar.HOUR_OF_DAY, 23);
                c.set(Calendar.MINUTE, 59);
                c.set(Calendar.SECOND, 59);
                c.set(Calendar.MILLISECOND, 999);
                return c.getTime();
        }

        /**
         * 是否是指定日期
         * 
         * @param date
         * @param day
         * @return
         */
        public static boolean isTheDay(final Date date, final Date day) {
                return date.getTime() >= TimeUtil.dayBegin(day).getTime()
                                && date.getTime() <= TimeUtil.dayEnd(day).getTime();
        }

        /**
         * 是否是今天
         * 
         * @param date
         * @return
         */
        public static boolean isToday(final Date date) {
                return isTheDay(date, TimeUtil.now());
        }

        /**
         * 增加、减少指定时数
         * 
         * @param date
         * @param hour
         *            要增加的时数（减少则为 负数）
         * @return
         */
        public static Date addHour(final Date date, int hour) {
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                c.add(Calendar.HOUR, hour);
                return c.getTime();
        }

        /**
         * 增加、减少指定天数
         * 
         * @param date
         * @param day
         *            要增加的天数（减少则为 负数）
         * @return
         */
        public static Date addDay(final Date date, int day) {
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                c.add(Calendar.DATE, day);
                return c.getTime();
        }

        /**
         * 增加、减少指定月数
         * 
         * @param date
         * @param month
         *            要增加的月数（减少则为 负数）
         * @return
         */
        public static Date addMonth(final Date date, int month) {
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                c.add(Calendar.MONTH, month);
                return c.getTime();
        }

        /**
         * 增加、减少指定年数
         * 
         * @param date
         * @param year
         *            要增加的年数（减少则为 负数）
         * @return
         */
        public static Date addYear(final Date date, int year) {
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                c.add(Calendar.YEAR, year);
                return c.getTime();
        }

        /**
         * 获取两个日期间的天数
         * 
         * @param startDate
         * @param endDate
         * @return
         */
        public static int amountDays(final Date startDate, final Date endDate) {
                long ms = endDate.getTime() - startDate.getTime();
                int ret = (int) (ms / 86400000L);
                return ret;
        }

        /**
         * 获取两个日期间的月数（待改进）
         * 
         * @param startDate
         * @param endDate
         * @return
         */
        public static int amountMonths(final Date startDate, final Date endDate) {
                Calendar c1 = Calendar.getInstance(), c2 = Calendar.getInstance();
                boolean rev = false; // 翻转（是否 startDate > endDate）
                Date d1 = startDate, d2 = endDate;
                if (startDate.getTime() > endDate.getTime()) {
                        rev = true;
                        d1 = endDate;
                        d2 = startDate;
                }
                c1.setTime(d1);
                c2.setTime(d2);
                int year1 = c1.get(Calendar.YEAR),
                        year2 = c2.get(Calendar.YEAR),
                        cy = year2 - year1; // count of year
                int month1 = c1.get(Calendar.MONTH), month2 = c2.get(Calendar.MONTH);
                int ret;
                if (cy > 0) {
                        ret = (cy - 1) * 12;
                        ret += month2;
                        ret += (12 - month1);
                } else {
                        ret = month2 - month1;
                }
                return rev ? (-1 * ret) : ret;
        }

        /**
         * 获取两个日期间的年数（待改进）
         * 
         * @param startDate
         * @param endDate
         * @return
         */
        public static int amountYears(final Date startDate, final Date endDate) {
                Calendar c = Calendar.getInstance();
                boolean rev = false; // 翻转（是否 startDate > endDate）
                Date d1 = startDate, d2 = endDate;
                if (startDate.getTime() > endDate.getTime()) {
                        rev = true;
                        d1 = endDate;
                        d2 = startDate;
                }
                c.setTime(d1);
                int y1 = c.get(Calendar.YEAR);
                c.setTime(d2);
                int y2 = c.get(Calendar.YEAR);
                int ret = y2 - y1;
                return rev ? (-1 * ret) : ret;
        }

        /** yyyy-MM-dd HH:mm:ss */
        public static final int YMDHMS = 1;
        /** yyyy-MM-dd HH:mm */
        public static final int YMDHM = 2;
        /** yyyy-MM-dd HH */
        public static final int YMDH = 3;
        /** yyyy-MM-dd */
        public static final int YMD = 4;
        /** yyyy-MM */
        public static final int YM = 5;
        /** yyyy */
        public static final int Y = 6;
        public static final int HMS=7;

        private static final SimpleDateFormat sdf1 = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss");
        private static final SimpleDateFormat sdf2 = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm");
        private static final SimpleDateFormat sdf3 = new SimpleDateFormat(
                        "yyyy-MM-dd HH");
        private static final SimpleDateFormat sdf4 = new SimpleDateFormat(
                        "yyyy-MM-dd");
        private static final SimpleDateFormat sdf5 = new SimpleDateFormat("yyyy-MM");
        private static final SimpleDateFormat sdf6 = new SimpleDateFormat("yyyy");
        private static final SimpleDateFormat sdf7 = new SimpleDateFormat("HH:mm:ss");


        /**
         * yyyy-MM-dd HH:mm:ss
         * 
         * @param date
         * @return
         */
        public static String toString(final Date date) {
                if (date == null)
//	                        return "null";
                        return "";
                return sdf1.format(date);
        }

        /**
         * 转换到字符串
         * 
         * @param date
         * @param type
         *            DateUtils.YMDHMS : "yyyy-MM-dd HH:mm:ss", DateUtils.YMD :
         *            "yyyy-MM-dd", DateUtils.YM : "yyyy-MM", DateUtils.Y : "yyyy"
         * @return
         */
        public static String toString(final Date date, int type) {
                if (date == null)
                        return "null";
                switch (type) {
                case HMS:
                	return sdf7.format(date);
                case YMDHMS:
                        return sdf1.format(date);
                case YMDHM:
                        return sdf2.format(date);
                case YMDH:
                        return sdf3.format(date);
                case YMD:
                        return sdf4.format(date);
                case YM:
                        return sdf5.format(date);
                case Y:
                        return sdf6.format(date);
                }
                return "unknow";
        }

        /**
         * 转换到字符串
         * 
         * @param date
         * @param fmt
         *            日期格式化字符串
         * @return
         */
        public static String toString(final Date date, String fmt) {
                SimpleDateFormat sdf = new SimpleDateFormat(fmt);
                return sdf.format(date);
        }


        /**
         * 从字符串解析为日期型
         * @param s
         * @param fmt
         * @return
         */
        public static Date parse(final String s, final String fmt) {
                SimpleDateFormat sdf = new SimpleDateFormat(fmt);
                try {
                        return sdf.parse(s);
                } catch (ParseException e) { }
                return null;
        }

        /**
         * 从字符串(yyyy-MM-dd HH:mm:ss)解析为日期型
         * @param s
         * @return
         */
        public static Date parse(final String s) {
                try {
                        return sdf1.parse(s);
                } catch (ParseException e) { }
                return null;
        }

        public static long parseLong(final String s, final String fmt) {
                SimpleDateFormat sdf = new SimpleDateFormat(fmt);
                try {
                        Date t = sdf.parse(s);
                        return t == null ? 0L : t.getTime();
                } catch (ParseException e) { }
                return 0L;
        }

        public static long parseLong(final String s) {
                try {
                        Date t = sdf1.parse(s);
                        return t == null ? 0L : t.getTime();
                } catch (ParseException e) { }
                return 0L;
        }
        /**
         * 
         * @Title: TimetamptoString
         * @Description: TODO(今天转化为HH:mm:ss,不是今天转换为yyyy-MM-dd)
         * @ @param timestamp
         * @ @return   
         * @return: String   
         * @throws
         */
        public static final String TimetamptoString(Timestamp timestamp){
        	String debate_time = null;
        	if(null== timestamp){
        		return null;
        	}
			long time =  timestamp.getTime();
			Date debate = new Date(time);
			Date timenow = new Date();
		
          //  if( time >= dayBegin(timenow).getTime() && time <= dayEnd(timenow).getTime() ){//判断是否是今天
            	debate_time = toString(debate,1);//7
/*            }else{
            	debate_time = toString(debate,4) ;
            }*/
        	return debate_time;
        }
        /**
         * 
         * @Title: StringToSting
         * @Description: TODO(字符串解析为Date，判断是否为今天，今天返回HH：mm:ss,否者返回yyyy-MM-dd)
         * @ @param debate_time
         * @ @return   
         * @return: String   
         * @throws
         */
        public static final String StringToSting(String debate_time){
        	if(null == debate_time){
        		return null;
        	}
        	String result=null;
        	Date dat = parse(debate_time);
        	long time = dat.getTime();
			Date timenow = new Date();
//            if( time >= dayBegin(timenow).getTime() && time <= dayEnd(timenow).getTime() ){//判断是否是今天
            	result = toString(dat,1);
/*            }else{
            	result = toString(dat,4) ;
            }*/
        	return result;
        }
        /**
         * 
         * @Title: DateToString
         * @Description: TODO(将date类型的数据转为String 如果是今天 HH：mm：ss，否则：yyyy-MM-dd)
         * @ @param date
         * @ @return   
         * @return: String   
         * @throws
         */
        public static final String DateToString(Date date){
        	if(null==date){
        		return null; 
        	}
        	String result = null;
        	long time = date.getTime();
			Date timenow = new Date();
//            if( time >= dayBegin(timenow).getTime() && time <= dayEnd(timenow).getTime() ){//判断是否是今天
            	result = toString(date,1);
/*            }else{
            	result = toString(date,4) ;
            }*/
        	return result;
        }

}

