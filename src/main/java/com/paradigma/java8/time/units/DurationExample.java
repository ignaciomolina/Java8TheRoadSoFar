package com.paradigma.java8.time.units;

import static java.time.Duration.ofSeconds;

import java.time.Clock;
import java.time.Duration;

import com.paradigma.java8.time.clocks.TimeDisplayer;

public class DurationExample {

  public static void main(String[] args) throws Exception {
    TimeDisplayer displayer = new TimeDisplayer(Clock.systemUTC());

    System.out.println(displayer.displayMessage());
    waitASec();
    System.out.println(displayer.displayMessage());
    wait(ofSeconds(10));
    System.out.println(displayer.displayMessage());
  }
  
  public static void waitASec() throws Exception {
    wait(ofSeconds(1L));
  }
  
  public static void wait(Duration duration) throws InterruptedException {
    Thread.sleep(duration.toMillis());
  }
}
