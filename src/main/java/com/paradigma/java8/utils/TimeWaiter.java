package com.paradigma.java8.utils;

import java.time.Duration;

import com.paradigma.java8.functional.Action;
import com.paradigma.java8.functional.ThrowingAction;

public class TimeWaiter {

  public static void waitFor(Duration duration) {

    handleCheckedException(() -> Thread.sleep(duration.toMillis()));
  }

  public static void waitKey() {

    handleCheckedException(() -> System.in.read());
  }

  public static void doUntilKey(Action task) {

    handleCheckedException(() -> {

      while (System.in.available() == 0) {

        task.execute();
      }
    });
  }

  private static void handleCheckedException(ThrowingAction task) {

    try {

      task.tryToExecute();
    } catch (Exception e) {

      throw new RuntimeException(e);
    }
  }
}
