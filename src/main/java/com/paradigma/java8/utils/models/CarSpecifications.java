package com.paradigma.java8.utils.models;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CarSpecifications {

  private PiecesSpecification pieces;
  private ColorSpecification color;
  private WheelSpecification wheels;

  public CarSpecifications(PiecesSpecification pieces, ColorSpecification color, WheelSpecification wheels) {
    this.pieces = pieces;
    this.color = color;
    this.wheels = wheels;
  }

  public PiecesSpecification getPiecesDetails() {
    return pieces;
  }

  public ColorSpecification getColorDetails () {
    return color;
  }

  public WheelSpecification getWheelDetails () {
    return wheels;
  }

  public static class Specification {

    private Duration assemblyTime;

    public Specification(Duration assemblyTime) {
      this.assemblyTime = assemblyTime;
    }

    public Duration getAssemblyTime() {
      return assemblyTime;
    }
  }

  public static class PiecesSpecification extends Specification {

    private List<String> pieces;

    public PiecesSpecification(Duration assemblyTime, List<String> pieces) {
      super(assemblyTime);
      this.pieces = new ArrayList<>(pieces);
    }

    public List<String> getPieces() {
      return pieces;
    }
  }
  
  public static class ColorSpecification extends Specification {

    private String color;

    public ColorSpecification(Duration assemblyTime, String color) {
      super(assemblyTime);
      this.color = color;
    }

    public String getColor() {
      return color;
    }
  }
  
  public static class WheelSpecification extends Specification {

    private String wheels;

    public WheelSpecification(Duration assemblyTime, String wheels) {
      super(assemblyTime);
      this.wheels = wheels;
    }

    public String getWheelModel() {
      return wheels;
    }
  }
}
