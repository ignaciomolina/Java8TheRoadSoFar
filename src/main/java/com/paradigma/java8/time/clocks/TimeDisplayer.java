package com.paradigma.java8.time.clocks;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Objects;

public class TimeDisplayer {

  private Clock clock;
  
  public TimeDisplayer(Clock clock) {
    this.clock = Objects.requireNonNull(clock, "Clock cannot be null.");
  }
  
  public String displayMessage() {
    return "The current date is: " + ZonedDateTime.now(clock);
  }
}
