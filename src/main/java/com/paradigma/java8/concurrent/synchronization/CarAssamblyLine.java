package com.paradigma.java8.concurrent.synchronization;

import static com.paradigma.java8.utils.TimeWaiter.waitFor;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.function.Supplier;

import com.paradigma.java8.utils.models.Car;
import com.paradigma.java8.utils.models.CarSpecifications;
import com.paradigma.java8.utils.models.CarSpecifications.ColorSpecification;
import com.paradigma.java8.utils.models.CarSpecifications.PiecesSpecification;
import com.paradigma.java8.utils.models.CarSpecifications.WheelSpecification;

public class CarAssamblyLine {

  public CompletionStage<Car> buildCar(CarSpecifications specifications) {

    return CompletableFuture.supplyAsync(createPieces(specifications))
                            .thenApply(assamblyPieces(specifications))
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
  
  private Function<Car.Builder, Car.Builder> assamblyPieces(CarSpecifications specifications) {
    return builder -> {
      CarSpecifications.PiecesSpecification pieces = specifications.getPiecesDetails();

      System.out.println("Assamblying pieces...");
      waitFor(pieces.getAssamblyTime());
      return builder.pieces(pieces.getPieces());
    };
  }

  private Function<Car.Builder, Car.Builder> paint(CarSpecifications specifications) {

    return builder -> {
      ColorSpecification colorDetails = specifications.getColorDetails();

      System.out.println("Painting bodywork...");
      waitFor(colorDetails.getAssamblyTime());
      return builder.color(colorDetails.getColor());
    };
  }
  
  private Function<Car.Builder, Car.Builder> addWheels(CarSpecifications specifications) {

    return builder -> {
      WheelSpecification wheelsDetails = specifications.getWheelDetails();

      System.out.println("Coupling wheels...");
      waitFor(wheelsDetails.getAssamblyTime());
      return builder.wheels(wheelsDetails.getWheelModel());
    };
  }
  
  private Function<Car.Builder, Car> submit() {
    return Car.Builder::build;
  }

  public static void main(String [] args) {

    CarAssamblyLine assamblyLine = new CarAssamblyLine();

    PiecesSpecification pieces = new PiecesSpecification(Duration.ofSeconds(3), Arrays.asList("doors", "bodywork", "motor", "brakes"));
    ColorSpecification color = new ColorSpecification(Duration.ofSeconds(2), "Red");
    WheelSpecification wheels = new WheelSpecification(Duration.ofSeconds(1), "cool ones");

    CarSpecifications specifications = new CarSpecifications(pieces, color, wheels);

    CompletionStage<Car> promise = assamblyLine.buildCar(specifications);
    promise.thenAccept(car -> System.out.println("Here is your great new car just as you wanted: " + car));

    System.out.println("Car order made!");
    
    promise.toCompletableFuture().join();

  }
}
