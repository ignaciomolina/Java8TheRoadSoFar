package com.paradigma.java8.functional;

@FunctionalInterface
public interface ThrowingSupplier<T> {

  T get() throws Exception;
}
