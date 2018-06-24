package com.paradigma.java8.concurrent.errors;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class ExceptionRecover {

  public static void main(String[] args) {

    CompletionStage<String> firstStage = CompletableFuture.supplyAsync(() -> {
      throw new RuntimeException();
//      return "Ok";
    });

    CompletionStage<String> failSafeStage = firstStage.exceptionally(throwable -> {
      return "after recovered";
    });

    String result = failSafeStage.toCompletableFuture().join();

    System.out.println("Finish " + result);
  }
}
