package com.paradigma.java8.optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.paradigma.java8.utils.JavaVersion;
import com.paradigma.java8.utils.models.membership.Customer;
import com.paradigma.java8.utils.models.membership.MemberCard;
import com.paradigma.java8.utils.models.membership.MembershipLevel;

@RunWith(Parameterized.class)
public class InvoiceServiceTest {

  @Parameter(0) public JavaVersion version;

  @Mock private Customer customer;
  @Mock private MemberCard memberCard;

  private InvoiceService service;

  @Parameters(name = "JavaVersion={0}")
  public static Object[] data() {

    return new Object[] {JavaVersion.EIGHT, JavaVersion.SEVEN};
  }

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    given(customer.getMemberCard()).willReturn(memberCard);

    service = new InvoiceService(version);
  }

  @Test
  public void shouldShowDiscountForPlatinumMember() {
    given(memberCard.getLevel()).willReturn(MembershipLevel.PLATINUM);

    testDiscountWithExpected("Discount%: 10.0");
  }

  @Test
  public void shouldShowDiscountForGoldMember() {
    given(memberCard.getLevel()).willReturn(MembershipLevel.GOLD);

    testDiscountWithExpected("Discount%: 5.0");
  }

  @Test
  public void shouldShowNoDiscountForRegularMember() {
    given(memberCard.getLevel()).willReturn(MembershipLevel.REGULAR);

    testDiscountWithExpected("");
  }

  @Test
  public void shouldShowNoDiscountWhenNoMemberCard() {
    given(customer.getMemberCard()).willReturn(null);

    testDiscountWithExpected("");
  }

  public void testDiscountWithExpected(String expected) {

    String discountLine = service.getDiscountLine(customer);

    then(discountLine).isEqualTo(expected);
  }
}
