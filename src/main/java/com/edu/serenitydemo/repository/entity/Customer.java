package com.edu.serenitydemo.repository.entity;

import com.edu.serenitydemo.repository.entity.base.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String firstName;
    String lastName;
    String dob;
    String address;
    String city;
    String identityType;
    String identityNumber;

}
