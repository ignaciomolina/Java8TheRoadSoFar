package com.paradigma.java8.functional;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.function.BiFunction;

public class TriFunctionTest {

  @Test
  public void testMultiply() {

    TriFunction<Integer, Integer, Integer, Integer> triMultiply = (a, b, c) -> a * b * c;

    int result = triMultiply.apply(1, 2, 3);

    assertEquals(6, result);
  }

  @Test
  public void testPointCreation() {

    BiFunction<Float, Float, Point> toBiDimensionalPoint = Point::new;
    TriFunction<Float, Float, Float, Point> toTriDimensionalPoint = Point::new;

    String biPoint = toBiDimensionalPoint.andThen(Point::toString).apply(1.0F, 2.0F);
    String triPoint = toTriDimensionalPoint.andThen(Point::toString).apply(1.0F, 2.0F, 3.0F);

    assertEquals("Point represented by (1.0, 2.0, 0.0)", biPoint);
    assertEquals("Point represented by (1.0, 2.0, 3.0)", triPoint);
  }
}

class Point {

  private float x;
  private float y;
  private float z;

  public Point(float x, float y) {
    this.x = x;
    this.y = y;
    z = 0.0F;
  }

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