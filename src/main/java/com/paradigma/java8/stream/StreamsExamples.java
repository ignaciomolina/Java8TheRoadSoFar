package com.paradigma.java8.stream;

import com.paradigma.java8.utils.models.Car;
import com.paradigma.java8.utils.models.Color;
import com.paradigma.java8.utils.models.Piece;
import com.paradigma.java8.utils.models.Wheel;

import java.time.Clock;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsExamples {

  private static List<Car> cars;

  private static void blackCars() {

    long blackCars = cars.stream()
            .map(Car::getColor)
            .filter(Color.BLACK::equals)
            .count();

    System.out.println("There are " + blackCars + " black cars.");
  }

  private static void pinkCarsWithAirbags() {

    Predicate<Car> isPinkCar = car -> car.getColor() == Color.PINK;
    Predicate<Car> hasAirbag = car -> car.getPieces().contains(Piece.AIRBAGS);
    Predicate<Car> pinkWithAirbags = isPinkCar.and(hasAirbag);

    boolean carsFound = cars.stream().anyMatch(pinkWithAirbags);

    System.out.println(carsFound ? "There is at least 1 pink car with airbags" : "There is not any pink car with airbags");
  }

  private static void carsWithAllPieces() {

    Stream<Car> filteredCars = cars.stream()
            .filter(car -> car.getPieces().size() == Piece.values().length)
            .peek(System.out::println);

    System.out.println(filteredCars.count() + " cars found with all pieces.");
  }

  private static void groupByColor() {

    Map<Color, List<Car>> carsByColor = cars
            .stream()
            .collect(Collectors.groupingBy(Car::getColor));

    carsByColor.forEach((key, value) -> {
      System.out.println(key);
      value.forEach(car -> System.out.println("\t" + car));
    });

  }

  private static void measureSort() {

    List<String> values = IntStream.range(0, 1000000)
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

  private static Car createCar() {

    int numberOfPieces = ThreadLocalRandom.current().nextInt(1, Piece.values().length + 1);
    List<Piece> pieces = Arrays.stream(Piece.values(), 0, numberOfPieces).collect(Collectors.toList());

    Color color = Color.values()[ThreadLocalRandom.current().nextInt(Color.values().length)];
    Wheel wheel = Wheel.values()[ThreadLocalRandom.current().nextInt(Wheel.values().length)];
    int weight = ThreadLocalRandom.current().nextInt(800, 2200);

    return Car.builder()
            .pieces(pieces)
            .color(color)
            .wheels(wheel)
            .weight(weight)
            .build();
  }

  public static void main(String[] args) {

    cars = IntStream.range(1, 101)
            .parallel()
            .mapToObj(i -> createCar())
            .collect(Collectors.toList());

    blackCars();
    pinkCarsWithAirbags();
    carsWithAllPieces();
    groupByColor();
    measureSort();
  }
}