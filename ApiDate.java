package ch11_java_api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class ApiDate {

	public static void main(String[] args) throws ParseException {
		// 현재 시간
		// 1. Date 클래스 사용
		Date dateToday = new Date();

		// Date 객체가 new Date()로 생성되는 순간, 해당 Date 객체(dateToday) 내부에 그 때의 시간이 세팅된다.
		System.out.println(dateToday);

		// 2022년 05월 18일 14시 05분 05초
		// 2022/05/18 14:05:05
		// 위와같이 날짜 포맷 설정
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
		String strToday = sdf.format(dateToday);
		System.out.println(strToday);

		sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		strToday = sdf.format(dateToday);
		System.out.println(strToday);

		// 2. Calendar 클래스 사용
		// .getInstance() 이지만 싱글톤 패턴이 적용되어 있지 않음
		// new Date()와 마찬가지로 Calendar.getInstance가 실행되는
		// 그 시간이 세팅된 객체를 생성
		Calendar calendar = Calendar.getInstance();
		String time = sdf.format(calendar.getTime());
		System.out.println(time);

		// 3. System 클래스 사용
		// 1970년 1월 1일부터 현재까지의 경과된 시간을 밀리초 단위로 리턴
		long longToday = System.currentTimeMillis();
		System.out.println(longToday);

		// 날짜 포맷 설정
		strToday = sdf.format(longToday);
		System.out.println(strToday);

		System.out.println("\n==========================\n");

		// 다양한 날짜 타입 만들기
		// 1. 22/5/18 14:27:20
		// 2. 2022-05-18 02:27:20
		// 3. 수 오후 02:27:20
		// 4. 수요일 14:27:20
		// @ 2022.05.18 PM 14:27:20

		// 1
		Date dateNow = new Date();
		sdf = new SimpleDateFormat("yy/M/dd HH:mm:ss");
		strToday = sdf.format(dateNow);
		System.out.println(strToday);

		// 2
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		strToday = sdf.format(dateNow);
		System.out.println(strToday);

		// 3
		sdf = new SimpleDateFormat("E a HH:mm:ss");
		strToday = sdf.format(dateNow);
		System.out.println(strToday);

		// 4
		sdf = new SimpleDateFormat("E요일 HH:mm:ss");
		strToday = sdf.format(dateNow);
		System.out.println(strToday);

		// 5
		sdf = new SimpleDateFormat("yyyy.MM.dd aa HH:mm:ss", Locale.UK);
		strToday = sdf.format(dateNow);
		System.out.println(strToday);

		// 현재 미국 날짜는?
		sdf = new SimpleDateFormat("yyyy/MM/dd E a HH:mm:ss", Locale.US);
		TimeZone timeZone = TimeZone.getTimeZone("America/Los_Angeles");
		sdf.setTimeZone(timeZone);
		strToday = sdf.format(dateNow);
		System.out.println(strToday);

		System.out.println("\n============날짜 <> 문자==============\n");

		// 날짜(Date 타입) <-> 문자열(String 타입)
		String strTomorrow = "2022-05-19 15:03:22";
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dateTomorrow = sdf.parse(strTomorrow);
		System.out.println(dateTomorrow);

		String strTime = "11:20:33";
		sdf = new SimpleDateFormat("HH:mm:ss");
		Date dateTime = sdf.parse(strTime);
		System.out.println(dateTime);

		String strYear = "2022.03.20";
		sdf = new SimpleDateFormat("yyyy.MM.dd");
		Date dateYear = sdf.parse(strYear);
		System.out.println(dateYear);

		// 날짜 세팅하기
		Calendar cal = Calendar.getInstance();
		// Month의 경우 1월이 0, 2월 1, 3월 2, ...
		cal.set(1992, 11 - 1, 25, 12, 30, 20);
		System.out.println(cal);
		sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		String strCal = sdf.format(cal.getTime());
		System.out.println(strCal);

		// Date 타입을 Calendar 타입으로 변환
		Date temp = new Date();
		Calendar calTemp = Calendar.getInstance();
		calTemp.setTime(temp);

		System.out.println("\n=====================\n");

		// 날짜 꺼내기
		String anyTime = "2020.06.21 23:11:21";
		Date dateAnyTime = sdf.parse(anyTime);

		System.out.println(dateAnyTime.getTime());
		Calendar anyCal = Calendar.getInstance();
		anyCal.setTime(dateAnyTime);

		// 년도
		System.out.println(anyCal.get(Calendar.YEAR));
		// 월
		System.out.println(anyCal.get(Calendar.MONTH) + 1);
		// 일
		System.out.println(anyCal.get(Calendar.DATE));
		// 시, 분, 초 쭉 그대로하면 됨

		System.out.println("\n==============asdfsda===========\n");

		// 날짜 연산
		String oneDay = "2022.05.17 12:22:34";
		String twoDay = "2022.05.19 15:32:22";

		// 먼저 문자열을 Date 타입으로 바꿔주어야 함
		sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		Date dateOneDay = sdf.parse(oneDay);
		Date dateTwoDay = sdf.parse(twoDay);
		System.out.println(dateOneDay);
		// 둘 중 최신 날짜?
		System.out.println(dateOneDay.getTime());
		System.out.println(dateTwoDay.getTime());
		// 밀리초 값이 더 클수록 미래

		long diffMillSec = dateTwoDay.getTime() - dateOneDay.getTime();
		System.out.println(diffMillSec + "밀리초 차이");
		long diffSec = diffMillSec / 1000;
		System.out.println(diffSec + "초 차이");
		long diffMin = diffSec / 60;
		System.out.println(diffMin + "분 차이");
		long diffHour = diffMin / 60;
		System.out.println(diffHour + "시간 차이");
		long diffDate = diffHour / 24;
		System.out.println(diffDate + "일 차이");
		long diffYear = diffDate / 365;
		System.out.println(diffYear + "년 차이");

		// 한줄로
		long diff = diffMillSec / (1000 * 60 * 60 * 24);
		System.out.println(diff + "일 차이");

		System.out.println("\n=========================\n");

		// 디데이 계산
		// 오늘 날짜 준비
		Date today = new Date();
		sdf = new SimpleDateFormat("yyyy.MM.dd");
		System.out.println(today);

		strToday = sdf.format(today);
		System.out.println(strToday);

		Date todate = sdf.parse(strToday);
		System.out.println(todate);

		String dday = "2022.06.01";
		Date ddate = sdf.parse(dday);

		diffMillSec = todate.getTime() - ddate.getTime();
		diff = diffMillSec / (1000 * 60 * 60 * 24);
		System.out.println("지방 선거까지 d-day = " + diff);

		dday = "2022.04.11";
		ddate = sdf.parse(dday);
		diffMillSec = todate.getTime() - ddate.getTime();
		diff = diffMillSec / (1000 * 60 * 60 * 24);
		System.out.println("교육기간 = " + diff);

		System.out.println("\n=========================\n");

		// 날짜 연산 Calendar
		// 한 날짜를 기준으로 날짜를 더하거나 뺄 때 유용

		Calendar toCal = Calendar.getInstance();
		sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		System.out.println(sdf.format(toCal.getTime()));

		// 3일 뒤
		toCal.add(Calendar.DATE, 3);
		System.out.println(sdf.format(toCal.getTime()));

		// 20일 뒤
		toCal.add(Calendar.DATE, 20);
		System.out.println(sdf.format(toCal.getTime()));

		// 7일 전
		toCal.add(Calendar.DATE, -7);
		System.out.println(sdf.format(toCal.getTime()));

		// 11달 뒤
		toCal.add(Calendar.MONTH, 11);
		System.out.println(sdf.format(toCal.getTime()));

		System.out.println("\n=========================\n");

		// 달력 만들기
		int year = 2022;
		int month = 6;

		Calendar calender = Calendar.getInstance();
		calender.set(year, month - 1, 1);
		System.out.println(sdf.format(calendar.getTime()));

		// 해당 달의 마지막 일자(DATE) 얻기
		int lastDay = calender.getActualMaximum(Calendar.DAY_OF_MONTH);
		System.out.println(lastDay);

		// 해당 달의 시작 요일
		// 1: 일요일, 2: 월요일, 3: 화요일, ..., 7: 토요일
		int startDay = calender.get(Calendar.DAY_OF_WEEK);
		System.out.println(startDay);

		System.out.println(year + "년 " + month + "월 달력");
		System.out.println("일\t월\t화\t수\t목\t금\t토");

		int currentDay = 1;
		for (int i = 1; i <= 42; i++) {
			if (i < startDay) {
				System.out.print("\t");
			} else {
				System.out.printf("%2d\t", currentDay);
				currentDay++;

				if (currentDay > lastDay) {
					break;
				}
			}

			if (i % 7 == 0) {
				System.out.println();
			}
		}

	}

}
