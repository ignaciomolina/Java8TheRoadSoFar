package com.paradigma.java8.optional;

import static com.paradigma.java8.utils.Predicates.not;
import static com.paradigma.java8.utils.TimeWaiter.waitFor;
import static com.paradigma.java8.utils.TimeWaiter.waitKey;
import static java.time.Duration.ofSeconds;

import java.util.Optional;

public class OptionalExample {

  public static String recoverMessageFromSource() {

    System.out.println("Requesting to source...");

    waitFor(ofSeconds(5));

    return "We need to improve our efficiency!";
  }

  private static void printIfAny(String veryImportantMessage) {
    Optional.of(veryImportantMessage)
            .ifPresent(msj -> {
              System.out.println("Finally! what you should know is: " + msj);
            });
  }

  public static void main(String[] args) {

    String message = "Do only what you need to do and do it only when you need to";

    String veryImportantMessage = Optional.ofNullable(message)
                                          .filter(text -> !text.isEmpty())
                                          .map(String::toUpperCase)
                                          .orElse(recoverMessageFromSource());

    printIfAny(veryImportantMessage);

    waitKey();

    message = "Make It Better!";

    veryImportantMessage = Optional.ofNullable(message)
                                   .filter(not(String::isEmpty))
                                   .map(String::toUpperCase)
                                   .orElseGet(OptionalExample::recoverMessageFromSource);

    printIfAny(veryImportantMessage);
  }
}
