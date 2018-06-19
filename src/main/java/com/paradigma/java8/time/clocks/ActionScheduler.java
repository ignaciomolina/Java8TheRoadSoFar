package com.paradigma.java8.time.clocks;

import static com.paradigma.java8.utils.Predicates.not;
import static com.paradigma.java8.utils.TimeWaiter.waitKey;

import java.time.Clock;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import com.paradigma.java8.functional.Action;
import com.paradigma.java8.utils.models.DelayedAction;

public class ActionScheduler {

  private Clock clock;
  private List<DelayedAction> actions;
  private ExecutorService executor;

  public ActionScheduler(Clock clock) {

    this.clock = Objects.requireNonNull(clock, "Clock cannot be null.");

    actions = Collections.synchronizedList(new ArrayList<>());
  }

  public void stop() {

    checkIsActive();

    executor.shutdownNow();
    executor = null;
  }

  private void checkIsActive() {

    Optional.ofNullable(executor)
            .filter(not(ExecutorService::isShutdown))
            .orElseThrow(() -> new IllegalStateException("ActionScheduler is not active."));
  }

  public void start() {

    checkIsNonActive();

    executor = Executors.newSingleThreadExecutor();
    executor.execute(() -> startActionDispacher());
  }

  private void checkIsNonActive() {

    if (Objects.nonNull(executor)) {

      throw new IllegalStateException("ActionScheduler is already up and running.");
    }
  }

  private void startActionDispacher() {

    while (!Thread.interrupted()) {

      triggerActions();
    }
  }

  private void triggerActions() {

    List<DelayedAction> toBeTriggered;

    synchronized(actions) {

      toBeTriggered = actions.stream()
                             .filter(not(delayed -> delayed.getTriggerDue().isAfter(ZonedDateTime.now(clock))))
                             .collect(Collectors.toList());
    }

    toBeTriggered.stream()
                 .map(DelayedAction::getAction)
                 .forEach(Action::execute);

    actions.removeAll(toBeTriggered);
  }

  public void schedule(Action action, Duration after) {

    checkIsActive();

    DelayedAction delayed = new DelayedAction(action, ZonedDateTime.now(clock).plus(after));
    actions.add(delayed);
  }

  public static void main(String[] args) {
    ActionScheduler scheduler = new ActionScheduler(Clock.systemDefaultZone());

    scheduler.start();
    System.out.println("Scheduler started");

    waitKey();

    System.out.println("Action scheduled");
    scheduler.schedule(() -> System.out.println("Action triggered!"), Duration.ofSeconds(5));

    waitKey();

    System.out.println("Scheduler stopped");
    scheduler.stop();
  }
}
