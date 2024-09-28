package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.entities;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.valueobjects.Address;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.valueobjects.PersonName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalInformation {
    private Address address;
    private String phoneNumber;
    private PersonName personName;
    private Date dateOfBirth;

    public PersonalInformation(String firstName, String lastName, String street, String city, String state, String zipCode, String phoneNumber, Date dateOfBirth) {
        this.personName = new PersonName(firstName, lastName);
        this.address = new Address(street, city, state, zipCode);
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }

    public void setPersonName(String firstName, String lastName) {
        this.personName = new PersonName(firstName, lastName);
    }

    public void setAddress(String street, String city, String state, String zipCode) {
        this.address = new Address(street, city, state, zipCode);
    }

    public String getFullName() {
        return personName.getFullName();
    }
}
