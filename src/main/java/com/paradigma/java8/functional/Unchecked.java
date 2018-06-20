package com.paradigma.java8.functional;

import java.util.function.Supplier;

import com.paradigma.java8.utils.exceptions.UncheckedException;

public class Unchecked {

  public static <T> Supplier<T> supplier(ThrowingSupplier<T> supplier) {

    return () -> {

      try {

        return supplier.get();
      } catch (Throwable e) {

        toUnckedException(e);

        throw new IllegalStateException("Exception handler has failed.");
      }
    };
  }

  public static Action action(ThrowingAction action) {

    return () -> {

      try {

        action.tryToExecute();
      } catch (Throwable e) {

        toUnckedException(e);
      }
    };
  }

  private static void toUnckedException(Throwable e) {

    if (e instanceof RuntimeException) {

      throw (RuntimeException) e;
    }

    throw new UncheckedException(e);
  }
}
