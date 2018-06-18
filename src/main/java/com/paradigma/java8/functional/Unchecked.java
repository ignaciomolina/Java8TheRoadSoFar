package com.paradigma.java8.functional;

import com.paradigma.java8.utils.exceptions.UncheckedException;

public class Unchecked {

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
