package com.paradigma.java8.utils.models;

import com.paradigma.java8.functional.Action;

public class DelayedAction {

  private Action action;
  private long triggerDue;

  public DelayedAction(Action action, long triggerDue) {

    this.triggerDue = triggerDue;
    this.action = action;
  }

  public Action getAction() {
    return action;
  }

  public long getTriggerDue() {
    return triggerDue;
  }
}
