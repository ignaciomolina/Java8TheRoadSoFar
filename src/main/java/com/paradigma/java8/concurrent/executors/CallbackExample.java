package com.paradigma.java8.concurrent.executors;

import static com.paradigma.java8.utils.TimeWaiter.waitFor;
import static com.paradigma.java8.utils.TimeWaiter.waitNumber;
import static java.time.Duration.ofSeconds;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class CallbackExample {

  public static void findNumberAndNotify(CompletableFuture<Double> future) {

    Runnable completeStage = () -> {
      waitFor(ofSeconds(5));

      future.complete((double) ThreadLocalRandom.current().nextInt(0, 100));
    };

    Executors.newSingleThreadExecutor()
             .submit(completeStage);
  }

  public static void main(String[] args) {

    CompletableFuture<Double> xPromise = new CompletableFuture<>();
    CompletableFuture<Double> yPromise = new CompletableFuture<>();

    CompletionStage<Void> stage = xPromise.thenCombine(yPromise, (x, y) -> x + y)
                                          .thenApply(x -> x / 2.0D)
                                          .thenAccept(System.out::println);

    findNumberAndNotify(yPromise);

    System.out.println("Give me a number");
    double number = waitNumber();
    xPromise.complete(number);

    stage.toCompletableFuture().join();
  }
}
