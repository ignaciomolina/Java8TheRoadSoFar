package com.paradigma.java8.stream;

import java.time.Clock;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ParallelBenchmark {

  private static void sortBenchmark() {

    List<String> values = IntStream.range(0, 1200000)
            .mapToObj(i -> UUID.randomUUID())
            .map(String::valueOf)
            .collect(Collectors.toList());

    System.out.println(String.format("sequential sort took: %d ms", timeConsumed(values::stream)));
    System.out.println(String.format("parallel sort took: %d ms", timeConsumed(values::parallelStream)));
  }

  private static long timeConsumed(Supplier<Stream<String>> source) {

    Clock clock = Clock.systemUTC();

    long t0 = clock.millis();
    source.get().sorted().count();
    long t1 = clock.millis();

    return t1 - t0;
  }

  public static void main(String[] args) {

    sortBenchmark();
  }
}
