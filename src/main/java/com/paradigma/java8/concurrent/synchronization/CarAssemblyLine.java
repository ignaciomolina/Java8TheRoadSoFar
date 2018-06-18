package com.paradigma.java8.concurrent.synchronization;

import static com.paradigma.java8.utils.TimeWaiter.waitFor;
import static java.time.Duration.ofSeconds;
import static java.util.Arrays.asList;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.function.Supplier;

import com.paradigma.java8.utils.models.cars.Car;
import com.paradigma.java8.utils.models.cars.CarSpecifications;
import com.paradigma.java8.utils.models.cars.CarSpecifications.ColorSpecification;
import com.paradigma.java8.utils.models.cars.CarSpecifications.PiecesSpecification;
import com.paradigma.java8.utils.models.cars.CarSpecifications.WheelSpecification;
import com.paradigma.java8.utils.models.cars.Color;
import com.paradigma.java8.utils.models.cars.Piece;
import com.paradigma.java8.utils.models.cars.Wheel;

public class CarAssemblyLine {

  public CompletionStage<Car> buildCar(CarSpecifications specifications) {

    return CompletableFuture.supplyAsync(createPieces(specifications))
                            .thenApply(assemblyPieces(specifications))
                            .thenApply(paint(specifications))
                            .thenApply(addWheels(specifications))
                            .thenApply(submit());
  }

  private Supplier<Car.Builder> createPieces(CarSpecifications specifications) {

    return () -> {

      waitFor(Duration.ofSeconds(1));
      System.out.println("Building pieces...");

      return Car.builder();
    };
  }

  private Function<Car.Builder, Car.Builder> assemblyPieces(CarSpecifications specifications) {

    return builder -> {

      CarSpecifications.PiecesSpecification pieces = specifications.getPiecesDetails();

      System.out.println("Assemblying pieces...");
      waitFor(pieces.getAssemblyTime());

      return builder.pieces(pieces.getPieces());
    };
  }

  private Function<Car.Builder, Car.Builder> paint(CarSpecifications specifications) {

    return builder -> {
      ColorSpecification colorDetails = specifications.getColorDetails();

      System.out.println("Painting bodywork...");
      waitFor(colorDetails.getAssemblyTime());
      return builder.color(colorDetails.getColor());
    };
  }

  private Function<Car.Builder, Car.Builder> addWheels(CarSpecifications specifications) {

    return builder -> {

      WheelSpecification wheelsDetails = specifications.getWheelDetails();

      System.out.println("Coupling wheels...");
      waitFor(wheelsDetails.getAssemblyTime());

      return builder.wheels(wheelsDetails.getWheelModel());
    };
  }

  private Function<Car.Builder, Car> submit() {

    return Car.Builder::build;
  }

  public static void says(int seconds, String message) {

    waitFor(ofSeconds(seconds));
    System.out.println("Seller says: " + message);
  }

  public static void main(String [] args) {

    CarAssemblyLine assemblyLine = new CarAssemblyLine();

    PiecesSpecification pieces = new PiecesSpecification(ofSeconds(3), asList(Piece.DOORS, Piece.BODY, Piece.ENGINE, Piece.BRAKES));
    ColorSpecification color = new ColorSpecification(ofSeconds(2), Color.RED);
    WheelSpecification wheels = new WheelSpecification(ofSeconds(2), Wheel.BRIDGESTONE);

    CarSpecifications specifications = new CarSpecifications(pieces, color, wheels);

    CompletionStage<Car> carPromise = assemblyLine.buildCar(specifications);

    CompletableFuture.runAsync(() -> says(0, "Car order made!"))
                     .thenRun(() -> says(2, "You can wait there meanwhile..."))
                     .thenRun(() -> says(2, "Do you want something to drink?"))
                     .thenCombine(carPromise, (v, car) -> {
                       says(0, "Here is your great new car just as you wanted: " + car);
                       return car;
                     })
                     .toCompletableFuture()
                     .join();
  }
}
