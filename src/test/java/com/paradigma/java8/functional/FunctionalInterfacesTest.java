package com.paradigma.java8.functional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.paradigma.java8.utils.models.cars.Car;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class FunctionalInterfacesTest {

  private static final String HELLO_WORLD = "Hello World!";

  @Test
  public void predicateTest() {

    Predicate<Integer> oldIsGreaterThan10 = new Predicate<Integer>() {
      @Override
      public boolean test(Integer number) {
        return number > 10;
      }
    };

    Predicate<Integer> isGreaterThan10 = number -> number > 10;

    assertTrue(isGreaterThan10.test(12));
  }

  @Test
  public void supplierTest() throws Exception {

    Supplier<Callable<String>> oldSupplier = new Supplier<Callable<String>>() {
      @Override
      public Callable<String> get() {

        return new Callable<String>() {
          @Override
          public String call() throws Exception {
            return HELLO_WORLD;
          }
        };
      }
    };

    Supplier<Callable<String>> callableSupplier = () -> () -> HELLO_WORLD;

    assertEquals(HELLO_WORLD, oldSupplier.get().call());
    assertEquals(HELLO_WORLD, callableSupplier.get().call());
  }

  @Test
  public void consumerTest() {

    Consumer<String> stringConsumer = System.out::println;

    stringConsumer.accept(HELLO_WORLD);
  }

  @Test
  public void unaryOperatorAndFunctionTest() {

    UnaryOperator<String> toUpperCase = String::toUpperCase;
    UnaryOperator<String> trim = String::trim;
    Function<String, String> upperCaseThenTrim = toUpperCase.andThen(trim);

    assertEquals("HELLO WORLD!", toUpperCase.apply(HELLO_WORLD));
    assertEquals("HI", upperCaseThenTrim.apply("Hi "));

    Function<List<?>, Integer> countElements = List::size;
    Function<List<?>, List<?>> duplicateNumberOfElements =
            list -> list.stream()
                    .map(elem -> Arrays.asList(elem, elem))
                    .flatMap(List::stream)
                    .collect(Collectors.toList());

    assertEquals(2, countElements.apply(Arrays.asList("a", "b")).intValue());
    assertEquals(4, duplicateNumberOfElements.andThen(countElements).apply(Arrays.asList("a", "b")).intValue());
  }

  @Test
  public void runnableTest() {

    new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("I'm an old runnable!");
      }
    }).start();

    new Thread(() -> System.out.println("I'm a modern runnable!")).start();

    new Thread(this::runnableExample).start();
  }

  private void runnableExample() {
    System.out.println("I'm a modern runnable called by method reference!");
  }

  @Test
  public void comparatorTest() {

    Comparator<Car> oldComparator = new Comparator<Car>() {
      @Override
      public int compare(Car c1, Car c2) {
        return c1.getColor().compareTo(c2.getColor());
      }
    };

    Comparator<Car> newComparator = (c1, c2) -> c1.getColor().compareTo(c2.getColor());

    Comparator<Car> evenNewerComparator = Comparator.comparing(Car::getColor);
  }
}
