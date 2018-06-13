package com.paradigma.java8.optional;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.paradigma.java8.utils.JavaVersion;

@RunWith(Parameterized.class)
public class ShouterMessageBuilderTest {

  @Parameter(0) public JavaVersion version;

  private ShouterMessageBuilder shouter;

  
  @Parameters(name = "JavaVersion={0}")
  public static Object[] data() {

    return new Object[] {JavaVersion.EIGHT, JavaVersion.SEVEN};
  }

  @Before
  public void setUp() {

    shouter = new ShouterMessageBuilder(version);
  }

  @Test
  public void shouldShoutTheShoutMadeByTheShouter() {
    String shout = shouter.buildShout("Spartan", "");

    then(shout).isEqualTo("Spartan shouts: This is a default message!");
  }

  @Test
  public void shouldShoutDefaultMessageWhenEmpty() {
    String shout = shouter.buildShout("Spartan", "");

    then(shout).isEqualTo("Spartan shouts: This is a default message!");
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldNotAcceptEmptyShouter() {
    
    shouter.buildShout("", "This is Java!");
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldNotAcceptNullShouter() {
    
    shouter.buildShout(null, "This is Java!");
  }
}
