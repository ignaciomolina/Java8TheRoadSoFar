package com.paradigma.java8.stream.chains;

public interface ChainLink<T, U> {

  boolean test(U conditions);

  T apply(T element, U conditions);
}