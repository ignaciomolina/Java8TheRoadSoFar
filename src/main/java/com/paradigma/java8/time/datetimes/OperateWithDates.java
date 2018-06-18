package com.paradigma.java8.time.datetimes;

import static com.paradigma.java8.utils.TimeWaiter.waitKey;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;

public class OperateWithDates {

  private static void showsThePassageOfTime(Temporal currentDate) {

    TemporalAmount firstTimePassage = Period.ofMonths(4);
    int secondTimePassageInDays = 10;
    Duration thirdTimePassage = Duration.ofHours(300);

    Temporal dateWithExtraMonths = currentDate.plus(firstTimePassage);
    Temporal dateWithExtraMonthsAndDays = dateWithExtraMonths.plus(secondTimePassageInDays, ChronoUnit.DAYS);
    Temporal dateWithExtraMonthsAndDaysAndHours = dateWithExtraMonthsAndDays.plus(thirdTimePassage.toMinutes(), ChronoUnit.MINUTES);

    System.out.println("Now it is " + currentDate);
    System.out.println("If we wait a few months it will be " + dateWithExtraMonths);
    System.out.println("When " + secondTimePassageInDays + " days had to pass then it will be " + dateWithExtraMonthsAndDays);
    System.out.println("And then again when " + thirdTimePassage.toHours() + " hours had to pass it will be " + dateWithExtraMonthsAndDaysAndHours);
  }

  public static void main(String [] args) {

    showsThePassageOfTime(ZonedDateTime.now());
    waitKey();

    showsThePassageOfTime(OffsetDateTime.now());
    waitKey();

    showsThePassageOfTime(LocalDateTime.now());
  }
}
