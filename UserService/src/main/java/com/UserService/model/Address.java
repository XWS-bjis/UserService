package com.UserService.model;

import lombok.Data;

@Data
public class Address {
    private Long id;
    private String streetNumber;
    private String streetName;
    private String postalCode;
    private String town;
    private String country;
}
