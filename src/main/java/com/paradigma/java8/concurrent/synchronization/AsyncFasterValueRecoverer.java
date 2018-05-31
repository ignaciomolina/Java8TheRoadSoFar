package com.paradigma.java8.concurrent.synchronization;

import static com.paradigma.java8.utils.TimeWaiter.waitFor;
import static java.time.Duration.ofSeconds;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

public class AsyncFasterValueRecoverer {

  private static CompletableFuture<Integer>[] startJobs(int numberOfJobs) {
    return IntStream.range(0, numberOfJobs)
                    .mapToObj(AsyncFasterValueRecoverer::generateRandomNumber)
                    .toArray((IntFunction<CompletableFuture<Integer>[]>) CompletableFuture[]::new);
  }

  public static CompletableFuture<Integer> generateRandomNumber(int instance) {
    int number = ThreadLocalRandom.current().nextInt(0, 10);

    return CompletableFuture.supplyAsync(() -> {

      System.out.println("[" + instance + "] Generate number " + number + " and wait for equal number of seconds.");
      waitFor(ofSeconds(number));

      return number;
    });
  }

  public static void main(String [] args) {

    int numberOfJobs = 5;

    CompletableFuture<Integer>[] stages = startJobs(numberOfJobs);

    CompletableFuture.anyOf(stages)
                     .thenAccept(number -> {
                         System.out.println("Faster value generated was: " + number);
                       })
                     .toCompletableFuture().join();
  }
}
