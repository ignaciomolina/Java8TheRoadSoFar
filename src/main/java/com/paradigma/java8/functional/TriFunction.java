package com.paradigma.java8.functional;

@FunctionalInterface
public interface TriFunction<T, U, V, R> {

  R transform(T t, U u, V v);
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