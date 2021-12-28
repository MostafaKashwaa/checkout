package com.kashwaa.checkout.api.data.paymob;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillingData {
    @JsonProperty("apartment")
    String apartment;
    @JsonProperty("email")
    String email;
    @JsonProperty("floor")
    String floor;
    @JsonProperty("first_name")
    String firstName;
    @JsonProperty("street")
    String street;
    @JsonProperty("building")
    String building;
    @JsonProperty("phone_number")
    String phoneNumber;
    @JsonProperty("shipping_method")
    String shippingMethod;
    @JsonProperty("postal_code")
    String postalCode;
    @JsonProperty("city")
    String city;
    @JsonProperty("country")
    String country;
    @JsonProperty("last_name")
    String lastName;
    @JsonProperty("state")
    String state;

    public BillingData(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.apartment = "NA";
        this.building = "NA";
        this.city = "NA";
        this.country = "NA";
        this.floor = "NA";
        this.postalCode = "NA";
        this.shippingMethod = "NA";
        this.state = "NA";
        this.street = "NA";
    }
}
