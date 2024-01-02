package com.edu.serenitydemo.dto.request;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CustomerRequest {

    @NotNull
    @Length(min = 5, max = 100)
    String firstName;

    @NotNull
    String lastName;

    @NotNull
    String dob;
    String address;
    String city;
    String identityType;
    String identityNumber;
}
