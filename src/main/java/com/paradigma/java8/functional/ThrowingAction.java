package com.paradigma.java8.functional;

@FunctionalInterface
public interface ThrowingAction {

  void tryToExecute() throws Exception;
}
