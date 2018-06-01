package com.paradigma.java8.utils;

import java.util.function.Predicate;

public class Predicates {

  public static <T> Predicate<T> not(Predicate<T> predicate) {

    return predicate.negate();
  }
}
