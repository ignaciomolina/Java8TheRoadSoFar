package com.paradigma.java8.stream.chains;

import java.util.LinkedList;
import java.util.Queue;

public class ResponsibilityChain<T, U> {

  private Queue<ChainLink<T, U>> queue;

  public ResponsibilityChain() {
    queue = new LinkedList<>();
  }

  public ResponsibilityChain<T, U> next(ChainLink<T, U> link) {
    this.queue.add(link);
    return this;
  }

  public T evaluate(T target, U conditions) {
   return queue.stream()
               .filter(link -> link.test(conditions))
               .map(link -> link.apply(target, conditions))
               .findAny()
               .orElseThrow(() -> new IllegalArgumentException("No link satisfied the chain"));
  }
}