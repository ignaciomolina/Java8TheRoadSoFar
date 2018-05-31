package com.paradigma.java8.time.datetimes;

import static com.paradigma.java8.utils.TimeWaiter.waitKey;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class ParsingDates {

  private static void parseAndCompareDate(String date) {

    System.out.println("Parsing: " + date);
    ZonedDateTime zonedDate = ZonedDateTime.parse(date + "Z[Europe/London]");
    OffsetDateTime offsetDate = OffsetDateTime.parse(date + "Z");
    LocalDateTime localDate = LocalDateTime.parse(date);

    System.out.println("Are these date equals? " + compareDates(zonedDate, offsetDate, localDate));

    System.out.println("Zoned: " + zonedDate);
    System.out.println("Offset: " + offsetDate);
    System.out.println("Local: " + localDate);
  }

  private static boolean compareDates(ZonedDateTime zonedDate, OffsetDateTime offsetDate, LocalDateTime localDate) {
    return zonedDate.toInstant().equals(offsetDate.toInstant()) &&
           zonedDate.toInstant().equals(localDate.toInstant(ZoneOffset.UTC));
  }

  public static void main(String [] args) {

    String winterDate = "2018-01-23T00:00:00";
    parseAndCompareDate(winterDate);

    waitKey();

    String summerDate = "2018-08-23T00:00:00";
    parseAndCompareDate(summerDate);
  }
}
