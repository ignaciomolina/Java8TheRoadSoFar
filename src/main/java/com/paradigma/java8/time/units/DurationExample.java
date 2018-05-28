package com.paradigma.java8.time.units;

import static com.paradigma.java8.utils.TimeWaiter.waitFor;
import static java.time.Duration.ofSeconds;

import java.time.Clock;

import com.paradigma.java8.time.clocks.TimeDisplayer;

public class DurationExample {
  
  public static void waitASec() {
    waitFor(ofSeconds(1L));
  }

  public static void main(String[] args) {
    TimeDisplayer displayer = new TimeDisplayer(Clock.systemUTC());

    System.out.println(displayer.displayMessage());
    waitASec();
    System.out.println(displayer.displayMessage());
    waitFor(ofSeconds(10L));
    System.out.println(displayer.displayMessage());
  }
}
