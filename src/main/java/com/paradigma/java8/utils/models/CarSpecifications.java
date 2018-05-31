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

    private Duration assamblyTime;

    public Specification(Duration assamblyTime) {
      this.assamblyTime = assamblyTime;
    }

    public Duration getAssamblyTime() {
      return assamblyTime;
    }
  }

  public static class PiecesSpecification extends Specification {

    private List<String> pieces;

    public PiecesSpecification(Duration assamblyTime, List<String> pieces) {
      super(assamblyTime);
      this.pieces = new ArrayList<>(pieces);
    }

    public List<String> getPieces() {
      return pieces;
    }
  }
  
  public static class ColorSpecification extends Specification {

    private String color;

    public ColorSpecification(Duration assamblyTime, String color) {
      super(assamblyTime);
      this.color = color;
    }

    public String getColor() {
      return color;
    }
  }
  
  public static class WheelSpecification extends Specification {

    private String wheels;

    public WheelSpecification(Duration assamblyTime, String wheels) {
      super(assamblyTime);
      this.wheels = wheels;
    }

    public String getWheelModel() {
      return wheels;
    }
  }
}
