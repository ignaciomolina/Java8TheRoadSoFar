package com.paradigma.java8.utils;

import java.time.Duration;

public class TimeWaiter {

  public static void waitFor(Duration duration) {

    try {

      Thread.sleep(duration.toMillis());
    } catch (InterruptedException e) {

      throw new RuntimeException(e);
    }
  }
}
