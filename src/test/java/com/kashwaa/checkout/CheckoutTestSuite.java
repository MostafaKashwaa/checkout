package com.kashwaa.checkout;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages({
        "com.kashwaa.checkout",
        "com.kashwaa.checkout.domain",
        "com.kashwaa.checkout.validators",
        "com.kashwaa.checkout.api"
})
public class CheckoutTestSuite {
}
