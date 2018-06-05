package com.paradigma.java8.utils.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Car {

  private Set<String> pieces;
  private String color;
  private String wheels;
  private int weight;

  private Car(Set<String> pieces, String color, String wheels, int weight) {

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

    private Set<String> pieces;
    private String color;
    private String wheels;
    private int weight;

    private Builder() {
    }

    public Builder pieces(List<String> pieces) {

      this.pieces = new HashSet<>(pieces);
      return this;
    }

    public Builder color(String color) {

      this.color = color;
      return this;
    }
    
    public Builder wheels(String wheels) {
      this.wheels = wheels;
      return this;
    }

    public Builder weight(int weight) {
      this.weight = weight;
      return this;
    }
    
    public Car build() {

      return new Car(pieces, color, wheels, weight);
    }
  }

  public Set<String> getPieces() {
    return pieces;
  }

  public String getColor() {
    return color;
  }

  public String getWheels() {
    return wheels;
  }

  public int getWeight() {
    return weight;
  }
}
