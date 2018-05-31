package com.paradigma.java8.functional;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface TriFunction<T, U, V, R> {

  R transform(T t, U u, V v);

  default <S> TriFunction<T, U, V, S> andThen(Function<? super R, ? extends S> after) {
    Objects.requireNonNull(after);
    return (T t, U u, V v) -> after.apply(transform(t, u, v));
  }
}

class Point {
  private float x;
  private float y;
  private float z;

  public Point(float x, float y, float z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  @Override
  public String toString() {
    return "Point represented by (" + x + ", " + y + ", " + z + ")";
  }
}