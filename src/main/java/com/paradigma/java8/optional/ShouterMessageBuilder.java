package com.paradigma.java8.optional;

import static com.paradigma.java8.utils.Predicates.not;
import static java.util.Optional.ofNullable;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;

import com.paradigma.java8.utils.JavaVersion;

public class ShouterMessageBuilder {

  private BiFunction<String, String, String> shoutMethod;

  public ShouterMessageBuilder(JavaVersion version) {

    if (JavaVersion.EIGHT.equals(version)) {

      this.shoutMethod = this::buildShoutJava8;
    } else if (JavaVersion.SEVEN.equals(version)) {

      this.shoutMethod = this::buildShoutJava7;
    } else {

      throw new IllegalArgumentException("Java version not supported.");
    }
  }

  public String buildShout(String shouter, String shout) {
    return shoutMethod.apply(shouter, shout);
  }

  private String buildShoutJava7(String shouter, String shout) {

    if (Objects.isNull(shouter) || shouter.isEmpty()) {

      throw new IllegalArgumentException("Shouter cannot be null nor empty.");
    }

    String message = shout;
    if (Objects.isNull(shout) || shout.isEmpty()) {

       message = "This is a default message!";
    }

    return shouter + " shouts: " + message;
  }

  private String buildShoutJava8(String shouter, String shout) {

    return Optional.ofNullable(shouter)
                   .filter(not(String::isEmpty))
                   .map(who -> who.concat(" shouts: "))
                   .map(prefix -> prefix.concat(ofNullable(shout)
                                                .filter(not(String::isEmpty))
                                                .orElse("This is a default message!")))
                   .orElseThrow(() -> new IllegalArgumentException("Shouter cannot be null nor empty."));
  }
}