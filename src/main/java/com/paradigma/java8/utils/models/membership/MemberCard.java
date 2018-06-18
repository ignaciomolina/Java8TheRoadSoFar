package com.paradigma.java8.utils.models.membership;

public class MemberCard {

  private String name;
  private MembershipLevel level;

  public MemberCard(String id) {

    this(id, MembershipLevel.REGULAR);
  }

  public MemberCard(String name, MembershipLevel level) {

    this.name = name;
    this.level = level;
  }

  public String getName() {
    return name;
  }

  public MembershipLevel getLevel() {
    return level;
  }

  public void setLevel(MembershipLevel level) {
    this.level = level;
  }
}
