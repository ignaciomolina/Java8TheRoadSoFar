package com.paradigma.java8.time.units;

import java.time.Duration;

public class DurationExample {

  public static Duration aggregate(Duration ... amountsOfTime) {

    Duration aggregator = Duration.ZERO;

    for (Duration amount : amountsOfTime) {

      aggregator = aggregator.plus(amount);
    }

    return aggregator;
  }

  public static void main(String[] args) {

    Duration hours = Duration.ofHours(3);
    Duration minutes = Duration.ofMinutes(6);
    Duration nanos = Duration.ofNanos(6);

    System.out.println("total amounts: " + aggregate(hours, minutes, nanos).toNanos() + " nanoseconds.");
  }
}
