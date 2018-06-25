package com.paradigma.java8.optional;

import static com.paradigma.java8.utils.models.membership.MembershipLevel.GOLD;
import static com.paradigma.java8.utils.models.membership.MembershipLevel.PLATINUM;

import com.paradigma.java8.utils.JavaVersion;
import com.paradigma.java8.utils.models.membership.Customer;
import com.paradigma.java8.utils.models.membership.MemberCard;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public class InvoiceService {

  private Function<Customer, String> discountLineMethod;

  public InvoiceService(JavaVersion version) {

    if (JavaVersion.EIGHT.equals(version)) {

      this.discountLineMethod = this::getDiscountLineWithOpt;
    } else if (JavaVersion.SEVEN.equals(version)) {

      this.discountLineMethod = this::getDiscountLineClassic;
    } else {

      throw new IllegalArgumentException("Java version not supported.");
    }
  }

  public String getDiscountLine(Customer customer) {
    return discountLineMethod.apply(customer);
  }

  private String getDiscountLineClassic(Customer customer) {

    if (Objects.nonNull(customer) && Objects.nonNull(customer.getMemberCard())) {

      Double discount = getDiscountPercentageClassic(customer.getMemberCard());

      if (Objects.nonNull(discount)) {

        return "Discount%: " + discount;
      }
    }

    return "";
  }

  private String getDiscountLineWithOpt(Customer customer) {

    return Optional.ofNullable(customer)
            .map(Customer::getMemberCard)
            .flatMap(this::getDiscountPercentageWithOpt)
            .map(String::valueOf)
            .map("Discount%: "::concat)
            .orElse("");
  }

  private Double getDiscountPercentageClassic(MemberCard card) {

    if (PLATINUM.equals(card.getLevel())) {

      return 10.0D;
    } else if (GOLD.equals(card.getLevel())) {

      return 5.0D;
    }

    return null;
  }

  private Optional<Double> getDiscountPercentageWithOpt(MemberCard card) {

    if (PLATINUM.equals(card.getLevel())) {

      return Optional.of(10.0D);
    } else if (GOLD.equals(card.getLevel())) {

      return Optional.of(5.0D);
    }

    return Optional.empty();
  }
}
