package com.paradigma.java8.concurrent.executors;

import static com.paradigma.java8.utils.TimeWaiter.waitFor;
import static java.time.Duration.ofSeconds;
import static java.util.concurrent.CompletableFuture.runAsync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableForkJoin {

  public static void main(String [] args) {

    ExecutorService executor = Executors.newSingleThreadExecutor();

    Runnable task = () -> {

      waitFor(ofSeconds(1));
      System.out.println("This is thread " + Thread.currentThread().getName());
    };

    runAsync(task).thenRunAsync(task, executor)
                  .thenRun(task)
                  .thenRunAsync(task)
                  .toCompletableFuture()
                  .join();

    executor.shutdownNow();
  }
}
