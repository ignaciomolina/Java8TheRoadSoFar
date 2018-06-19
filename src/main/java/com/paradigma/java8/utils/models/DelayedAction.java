package com.paradigma.java8.utils.models;

import java.time.ZonedDateTime;

import com.paradigma.java8.functional.Action;

public class DelayedAction {

  private Action action;
  private ZonedDateTime triggerDue;

  public DelayedAction(Action action, ZonedDateTime triggerDue) {

    this.triggerDue = triggerDue;
    this.action = action;
  }

  public Action getAction() {
    return action;
  }

  public ZonedDateTime getTriggerDue() {
    return triggerDue;
  }
}
