package com.paradigma.java8.functional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.Arrays;
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

    assertEquals(HELLO_WORLD, callableSupplier.get().call());
  }

  @Test
  public void consumerTest() {

    Consumer<String> stringConsumer = System.out::print;

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
}
