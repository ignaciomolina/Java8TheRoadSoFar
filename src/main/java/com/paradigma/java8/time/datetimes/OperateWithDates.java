package com.paradigma.java8.time.datetimes;

import static com.paradigma.java8.utils.TimeWaiter.waitKey;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class OperateWithDates {

  private static void showsThePassageOfTime(Temporal currentDate) {
    
    int months = 6;
    int days = 10;
    int hours = 300;

    Temporal dateWithExtraMonths = currentDate.plus(months, ChronoUnit.MONTHS);
    Temporal dateWithExtraMonthsAndDays = dateWithExtraMonths.plus(days, ChronoUnit.DAYS);
    Temporal dateWithExtraMonthsAndDaysAndHours = dateWithExtraMonthsAndDays.plus(hours, ChronoUnit.HOURS);

    System.out.println("Now it is " + currentDate);
    System.out.println("If we wait a few months it will be " + dateWithExtraMonths);
    System.out.println("When " + days + " days had to pass then it will be " + dateWithExtraMonthsAndDays);
    System.out.println("And then again when " + hours + " hours had to pass it will be " + dateWithExtraMonthsAndDaysAndHours);
  }

  public static void main(String [] args) {

    ZonedDateTime date = ZonedDateTime.now();

    showsThePassageOfTime(date);
    waitKey();

    showsThePassageOfTime(date.toOffsetDateTime());
    waitKey();

    showsThePassageOfTime(date.toLocalDateTime());
  }
}
