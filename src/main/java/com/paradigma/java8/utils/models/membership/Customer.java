package com.paradigma.java8.utils.models.membership;

public class Customer {

  private String id;
  private MemberCard memberCard;

  public Customer(String id, MemberCard memberCard) {
    this.id = id;
    this.memberCard = memberCard;
  }

  public String getId() {
    return id;
  }

  public MemberCard getMemberCard() {
    return memberCard;
  }
}
