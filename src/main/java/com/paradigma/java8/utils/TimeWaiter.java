package com.paradigma.java8.utils;

import java.io.IOException;
import java.time.Duration;

import com.paradigma.java8.functional.Action;

public class TimeWaiter {

  public static void waitFor(Duration duration) {

    try {

      Thread.sleep(duration.toMillis());
    } catch (InterruptedException e) {

      throw new RuntimeException(e);
    }
  }
  
  public static void waitKey() {
    try {

      System.in.read();
    } catch (IOException e) {

      throw new RuntimeException(e);
    }
  }
  
  public static void doUntilKey(Action task) {
    
    try {
      while (System.in.available() == 0) {
        task.execute();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
