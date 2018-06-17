package com.paradigma.java8.stream;

import static com.paradigma.java8.utils.TimeWaiter.waitKey;

import com.paradigma.java8.utils.models.Car;
import com.paradigma.java8.utils.models.Color;
import com.paradigma.java8.utils.models.Piece;
import com.paradigma.java8.utils.models.Wheel;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CarOutlet {

  private List<Car> cars;

  public CarOutlet(List<Car> cars) {
    this.cars = cars;
  }

  private void blackCars() {

    long blackCars = cars.stream()
            .map(Car::getColor)
            .filter(Color.BLACK::equals)
            .count();

    System.out.println("There are " + blackCars + " black cars.");
  }

  private void pinkCarsWithAirbags() {

    Predicate<Car> isPinkCar = car -> car.getColor() == Color.PINK;
    Predicate<Car> hasAirbag = car -> car.getPieces().contains(Piece.AIRBAGS);
    Predicate<Car> pinkWithAirbags = isPinkCar.and(hasAirbag);

    boolean carsFound = cars.stream().anyMatch(pinkWithAirbags);

    System.out.println(carsFound ? "There is at least 1 pink car with airbags" : "There is not any pink car with airbags");
  }

  private void carsWithAllPieces() {

    Stream<Car> filteredCars = cars.stream()
            .filter(car -> car.getPieces().size() == Piece.values().length)
            .peek(System.out::println);

    System.out.println(filteredCars.count() + " cars found with all pieces.");
  }

  private void groupByColor() {

    Map<Color, List<Car>> carsByColorV1 = cars.stream()
            .collect(Collectors.toMap(
                    Car::getColor,
                    v -> cars.stream()
                            .filter(car -> car.getColor() == v.getColor())
                            .collect(Collectors.toList()),
                    (list1, list2) -> Stream.concat(list1.stream(), list2.stream())
                            .parallel()
                            .distinct()
                            .collect(Collectors.toList()))
            );

    Map<Color, List<Car>> carsByColorV2 = cars
            .stream()
            .collect(Collectors.groupingBy(Car::getColor));

    carsByColorV1.forEach((key, value) -> {
      System.out.println(key);
      value.forEach(car -> System.out.println("\t" + car));
    });

    System.out.println(carsByColorV1.size() == carsByColorV2.size() ? "Maps have the same size" : "Error in maps size!");
  }

  private String piecesNotUsed() {

    return cars.stream().flatMap(car -> car.getPieces().stream())
            .filter(piece -> !Arrays.asList(Piece.values()).contains(piece))
            .findAny()
            .map(Piece::toString)
            .orElseThrow(RuntimeException::new);
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

    List<Car> cars = IntStream.range(1, 101)
            .parallel()
            .mapToObj(i -> createCar())
            .collect(Collectors.toList());

    CarOutlet outlet = new CarOutlet(cars);

    outlet.blackCars();
    waitKey();

    outlet.pinkCarsWithAirbags();
    waitKey();

    outlet.carsWithAllPieces();
    waitKey();

    outlet.groupByColor();
    waitKey();

    outlet.piecesNotUsed();
  }
}