package com.paradigma.java8.utils.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Car {

  private Set<String> pieces;
  private String color;
  private String wheels;

  private Car(Set<String> pieces, String color, String wheels) {

    this.pieces = pieces;
    this.color = color;
    this.wheels = wheels;
  }

  public static Builder builder( ) {
    return new Builder();
  }

  @Override
  public String toString() {
    
    return color + " car with a complete set of " + pieces + " and four " + wheels + " wheels.";
  }

  public static class Builder {

    private Set<String> pieces;
    private String color;
    private String wheels;

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
    
    public Car build() {
      
      return new Car(pieces, color, wheels);
    }
  }
}
