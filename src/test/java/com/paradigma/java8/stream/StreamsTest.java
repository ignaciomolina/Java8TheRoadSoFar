package com.paradigma.java8.stream;

import com.paradigma.java8.utils.models.Car;
import com.paradigma.java8.utils.models.Color;
import com.paradigma.java8.utils.models.Piece;
import com.paradigma.java8.utils.models.Wheel;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsTest {

  private static List<Car> cars;

  @BeforeClass
  public static void setUp() {
    cars = IntStream.range(1, 101)
            .parallel()
            .mapToObj(i -> createCar())
            .collect(Collectors.toList());
  }

  @Test
  public void blackCars() {
    long blackCars = cars.stream()
            .map(Car::getColor)
            .filter(Color.BLACK::equals)
            .count();

    System.out.println("There are " + blackCars + " black cars.");
  }

  @Test
  public void pinkCarsWithAirbags() {

    Predicate<Car> isPinkCar = car -> car.getColor() == Color.PINK;
    Predicate<Car> hasAirbag = car -> car.getPieces().contains(Piece.AIRBAGS);
    Predicate<Car> pinkWithAirbags = isPinkCar.and(hasAirbag);

    boolean carsFound = cars.stream().anyMatch(pinkWithAirbags);

    System.out.println(carsFound ? "There is at least 1 pink car with airbags" : "There is not any pink car with airbags");
  }

  @Test
  public void carsWithAllPieces() {

    Stream<Car> filteredCars = cars.stream()
            .filter(car -> car.getPieces().size() == Piece.values().length)
            .peek(System.out::println);

    System.out.println(filteredCars.count() + " cars found with all pieces.");
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
}
