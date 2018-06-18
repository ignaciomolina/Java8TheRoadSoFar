package com.paradigma.java8.time.units;

import java.time.Period;

public class PeriodExample {

  public static Period aggregate(Period ... amountsOfTime) {

    Period aggregator = Period.ZERO;

    for (Period amount : amountsOfTime) {

      aggregator = aggregator.plus(amount);
    }

    return aggregator;
  }

  public static void main(String[] args) {

    Period years = Period.ofYears(3);
    Period months = Period.ofMonths(11);
    Period weeks = Period.ofWeeks(3);
    Period days = Period.ofDays(11);

    Period totalPeriod = aggregate(years, months, weeks, days);
    System.out.println("Units: " + totalPeriod.getUnits());
    System.out.println("total years: " + totalPeriod.getYears());
    System.out.println("total months: " + totalPeriod.getMonths());
    System.out.println("total days: " + totalPeriod.getDays());


    System.out.println("Total: " + totalPeriod);
  }
}
