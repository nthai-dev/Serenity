package com.edu.serenitydemo.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
public class CustomerResponse {
    long id;
    String firstName;
    String lastName;
    String dob;
    String address;
    String city;
    String identityType;
    String identityNumber;
    Date createdTimestamp;
    Date lastUpdatedTimestamp;
}
