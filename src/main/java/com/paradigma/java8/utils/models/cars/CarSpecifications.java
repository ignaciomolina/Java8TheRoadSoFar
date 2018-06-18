package com.paradigma.java8.utils.models.cars;

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

    private List<Piece> pieces;

    public PiecesSpecification(Duration assemblyTime, List<Piece> pieces) {
      super(assemblyTime);
      this.pieces = new ArrayList<>(pieces);
    }

    public List<Piece> getPieces() {
      return pieces;
    }
  }
  
  public static class ColorSpecification extends Specification {

    private Color color;

    public ColorSpecification(Duration assemblyTime, Color color) {
      super(assemblyTime);
      this.color = color;
    }

    public Color getColor() {
      return color;
    }
  }
  
  public static class WheelSpecification extends Specification {

    private Wheel wheels;

    public WheelSpecification(Duration assemblyTime, Wheel wheels) {
      super(assemblyTime);
      this.wheels = wheels;
    }

    public Wheel getWheelModel() {
      return wheels;
    }
  }
}
