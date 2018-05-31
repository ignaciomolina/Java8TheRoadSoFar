package com.paradigma.java8.functional;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TriFunctionTest {

  @Test
  public void testMultiply() {

    TriFunction<Integer, Integer, Integer, Integer> triMultiply = (a, b, c) -> a * b * c;

    int result = triMultiply.transform(1, 2, 3);

    assertEquals(6, result);
  }

  @Test
  public void testPointCreation() {

    TriFunction<Float, Float, Float, Point> toPoint = Point::new;

    String result = toPoint.andThen(Point::toString).transform(1.0F, 2.0F, 3.0F);

    assertEquals("Point represented by (1.0, 2.0, 3.0)", result);
  }
}
