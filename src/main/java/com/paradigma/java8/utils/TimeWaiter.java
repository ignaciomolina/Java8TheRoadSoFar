package com.paradigma.java8.utils;

import java.time.Duration;
import java.util.Scanner;

import com.paradigma.java8.functional.Action;
import com.paradigma.java8.functional.Unchecked;

public class TimeWaiter {

  public static void waitFor(Duration duration) {

    Unchecked.action(() -> Thread.sleep(duration.toMillis()))
             .execute();
  }

  public static void waitKey() {

    Unchecked.action(() -> System.in.read())
             .execute();
  }

  public static double waitNumber() {
    return Unchecked.supplier(() -> new Scanner(System.in).nextDouble())
                    .get();
  }

  public static void doUntilKey(Action task) {

    Unchecked.action(() -> {

                      while (System.in.available() == 0) {

                        task.execute();
                      }
                    })
            .execute();
  }
}
