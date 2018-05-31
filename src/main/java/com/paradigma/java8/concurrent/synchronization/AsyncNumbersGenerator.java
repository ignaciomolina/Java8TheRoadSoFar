package com.paradigma.java8.concurrent.synchronization;

import static com.paradigma.java8.utils.TimeWaiter.waitFor;
import static java.time.Duration.ofSeconds;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AsyncNumbersGenerator {

  private int max;

  public AsyncNumbersGenerator(int max) {

    this.max = Optional.of(max)
                       .filter(value -> value > 0)
                       .orElseThrow(() -> new IllegalArgumentException("Max value must be a positive number."));
  }

  private CompletableFuture<Integer>[] generate(int numberOfJobs) {

    return IntStream.range(0, numberOfJobs)
                    .mapToObj(this::generateRandomNumber)
                    .toArray((IntFunction<CompletableFuture<Integer>[]>) CompletableFuture[]::new);
  }

  private CompletableFuture<Integer> generateRandomNumber(int instance) {

    int number = ThreadLocalRandom.current().nextInt(0, max);

    return CompletableFuture.supplyAsync(() -> {

      System.out.println("[" + instance + "] Generating number " + number + ", this could take a while.");
      waitFor(ofSeconds(number));

      return number;
    });
  }

  public static void main(String [] args) {

    AsyncNumbersGenerator generator = new AsyncNumbersGenerator(10);
    
    CompletableFuture<Integer>[] promises = generator.generate(5);

    CompletableFuture.anyOf(promises)
                     .thenAccept(number -> {
                         System.out.println("Faster value generated was: " + number);
                       });

    CompletableFuture.allOf(promises)
                     .thenAccept(v -> {
                       List<Integer> numbers = Arrays.stream(promises)
                                                     .map(CompletionStage::toCompletableFuture)
                                                     .map(CompletableFuture::join)
                                                     .collect(Collectors.toList());

                       System.out.println("All generated values were: " + numbers);
                     })
                     .toCompletableFuture()
                     .join();
  }
}
