package com.paradigma.java8.optional;

import static com.paradigma.java8.utils.Predicates.not;
import static java.util.Optional.ofNullable;

import java.util.Objects;
import java.util.Optional;

public class OptionalExample {

  public static String buildShout7(String shouter, String shout) {

    if (Objects.isNull(shouter) || shouter.isEmpty()) {

      throw new IllegalArgumentException("Shouter cannot be null nor empty.");
    }

    String message = shout;
    if (Objects.isNull(shout) || shout.isEmpty()) {

       message = "This is a default message!";
    }

    return shouter + " shouts: " + message;
  }

  public static String buildShout8(String shouter, String shout) {

    return Optional.ofNullable(shouter)
                   .filter(not(String::isEmpty))
                   .map(who -> who.concat(" shouts: "))
                   .map(message -> message.concat(ofNullable(shout)
                                                  .filter(not(String::isEmpty))
                                                  .orElse("This is a default message!")))
                   .orElseThrow(() -> new IllegalArgumentException("Shouter cannot be null nor empty."));
  }

  public static void main(String[] args) {

    boolean equals = Objects.equals(buildShout8("Waiter", "This is a cool message!"), buildShout7("Waiter", "This is a cool message!"));
    System.out.println(equals);
    equals = Objects.equals(buildShout8("Waiter", ""), buildShout7("Waiter", ""));
    System.out.println(equals);
    equals = Objects.equals(buildShout8("Hater", null), buildShout7("Hater", null));
    System.out.println(equals);
//    Objects.equals(buildShout8("", "this is an anonymous message"), buildShout7("", "this is an anonymous message"));
//    buildShout8(null, "cooool nuull");
//    buildShout8("", "");
//    buildShout8(null, null);
  }
}
