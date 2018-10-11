package com.paradigma.java8.functional;

import java.util.function.Supplier;

import com.paradigma.java8.utils.exceptions.UncheckedException;

public class Unchecked {

  public static <T> Supplier<T> supplier(ThrowingSupplier<T> supplier) {

    return () -> {

      try {

        return supplier.get();
      } catch (Throwable e) {

        throw toUncheckedException(e);
      }
    };
  }

  public static Action action(ThrowingAction action) {

    return () -> {

      try {

        action.tryToExecute();
      } catch (Throwable e) {

        throw toUncheckedException(e);
      }
    };
  }

  private static RuntimeException toUncheckedException(Throwable e) {

    if (e instanceof RuntimeException) {

      return (RuntimeException) e;
    }

    return new UncheckedException(e);
  }
}
