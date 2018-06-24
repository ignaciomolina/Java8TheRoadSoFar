package com.paradigma.java8.concurrent.errors;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import com.paradigma.java8.utils.TimeWaiter;

public class ExceptionPriority {

  public static void main(String[] args) {

    CompletionStage<String> promise1 = CompletableFuture.supplyAsync(() -> {

      TimeWaiter.waitFor(Duration.ofSeconds(2));
      throw new RuntimeException("first");
//      return "ok";
    });

    CompletionStage<String> promise2 = CompletableFuture.supplyAsync(() -> {
      TimeWaiter.waitFor(Duration.ofSeconds(0));
      throw new RuntimeException("second");
//      return "ok";
    });

    CompletionStage<String> resultPromise = promise1.thenCombine(promise2, (obj1, obj2) -> {

      return "All right";
    });

//    CompletionStage<String> resultPromise = promise1.applyToEither(promise2, obj -> {
//
//      return "All right";
//    });

    String result = resultPromise.toCompletableFuture().join();

    System.out.println(result);
  }

}
