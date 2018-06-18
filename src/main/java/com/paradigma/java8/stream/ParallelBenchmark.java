package com.paradigma.java8.stream;

import java.time.Clock;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.paradigma.java8.functional.Action;

public class ParallelBenchmark {

  private static void sortBenchmark() {

    List<String> values = IntStream.range(0, 1200000)
                                   .mapToObj(i -> UUID.randomUUID())
                                   .map(String::valueOf)
                                   .collect(Collectors.toList());

    System.out.println(String.format("sequential sort took: %d ms", timeConsumedBySort(values::stream)));
    System.out.println(String.format("parallel sort took: %d ms", timeConsumedBySort(values::parallelStream)));
  }

  private static long timeConsumedBySort(Supplier<Stream<String>> source) {

    return measure(() -> source.get().sorted().count());
  }

  private static long measure(Action action) {

    Clock clock = Clock.systemUTC();

    long t0 = clock.millis();
    action.execute();
    long t1 = clock.millis();

    return t1 - t0;
  }

  public static void main(String[] args) {

    sortBenchmark();
  }
}
