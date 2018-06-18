package com.paradigma.java8.utils.models.cars;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Car {

  private Set<Piece> pieces;
  private Color color;
  private Wheel wheels;
  private int weight;

  private Car(Set<Piece> pieces, Color color, Wheel wheels, int weight) {

    this.pieces = pieces;
    this.color = color;
    this.wheels = wheels;
    this.weight = weight;
  }

  public static Builder builder( ) {
    return new Builder();
  }

  @Override
  public String toString() {

    return color + " car with a complete set of " + pieces + ", four " + wheels + " wheels, weighing " + weight + " kg.";
  }

  public static class Builder {

    private Set<Piece> pieces;
    private Color color;
    private Wheel wheels;
    private int weight;

    private Builder() {
    }

    public Builder pieces(List<Piece> pieces) {

      this.pieces = new HashSet<>(pieces);
      return this;
    }

    public Builder color(Color color) {

      this.color = color;
      return this;
    }

    public Builder wheels(Wheel wheels) {
      this.wheels = wheels;
      return this;
    }

    public Builder weight(int weight) {
      this.weight = weight;
      return this;
    }

    public Car build() {

      checkPieces();

      return new Car(pieces, color, wheels, weight);
    }

    private void checkPieces() {

      Objects.requireNonNull(pieces);

      if (pieces.isEmpty()) {

        throw new IllegalArgumentException("Car has to have some kind of piece.");
      }
    }
  }

  public Set<Piece> getPieces() {
    return pieces;
  }

  public Color getColor() {
    return color;
  }

  public Wheel getWheels() {
    return wheels;
  }

  public int getWeight() {
    return weight;
  }
}
