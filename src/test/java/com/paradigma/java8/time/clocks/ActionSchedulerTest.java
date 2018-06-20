package com.paradigma.java8.time.clocks;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofMinutes;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.after;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.paradigma.java8.functional.Action;

public class ActionSchedulerTest {

  @Mock private Clock clock;
  @Mock private Action token;

  private Instant currentInstant;
  private ActionScheduler scheduler;

  @Before
  public void setUp() {

    MockitoAnnotations.initMocks(this);

    currentInstant = Instant.now();

    given(clock.instant()).willReturn(currentInstant);
    given(clock.getZone()).willReturn(ZoneId.systemDefault());

    scheduler = new ActionScheduler(clock);
  }

  @Test
  public void shouldTriggerActionAfterTime() {

    Duration after = ofMinutes(10);

    scheduler.start();
    scheduler.schedule(token, after);

    given(clock.instant()).willReturn(currentInstant.plus(after));

    verify(token, timeout(100)).execute();

    scheduler.stop();
  }

  @Test
  public void shouldNotTriggerActionDueToNotExceededTime() {

    Duration after = ofMillis(10);

    scheduler.start();
    scheduler.schedule(token, after);

    given(clock.instant()).willReturn(currentInstant.plus(after).minus(Duration.ofSeconds(1L)));

    verify(token, after(100).never()).execute();

    scheduler.stop();
  }

  @Test(expected = IllegalStateException.class)
  public void shouldThrowExceptionWhenStopNonActiveScheduler() {

    scheduler.stop();
  }

  @Test(expected = IllegalStateException.class)
  public void shouldThrowExceptionWhenStartAnActiveScheduler() {

    scheduler.start();
    scheduler.start();
  }
}
