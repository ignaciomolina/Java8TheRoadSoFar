package com.paradigma.java8.time.clocks;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

import java.time.Clock;
import java.time.Instant;
import java.time.OffsetDateTime;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TimeDisplayerTest {

  @Mock private Clock clock;

  private TimeDisplayer displayer;

  @Before
  public void setUp() {

    MockitoAnnotations.initMocks(this);

    displayer = new TimeDisplayer(clock);
  }

  @Test
  public void shouldShowCurrentDate() {

    Instant instant = OffsetDateTime.now().toInstant();

    given(clock.instant()).willReturn(instant);

    then(displayer.displayMessage()).isEqualTo("The current date is: " + instant.toString());
  }

  @Test
  public void shouldShowDateTwentyEightDaysFromNow() {

    Instant instant = OffsetDateTime.now().plusDays(28L).toInstant();

    given(clock.instant()).willReturn(instant);

    then(displayer.displayMessage()).isEqualTo("The current date is: " + instant.toString());
  }

  @Test(expected = NullPointerException.class)
  public void shouldNotAcceptNullClock() {

    displayer = new TimeDisplayer(null);
  }
}
