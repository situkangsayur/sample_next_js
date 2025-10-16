package com.example.employee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String position;
    private String idCard;
    private String phoneNumber;
    private String birthOfPlace;
    private String birthOfDate;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String email, String position, String idCard, String phoneNumber, String birthOfPlace, String birthOfDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.position = position;
        this.idCard = idCard;
        this.phoneNumber = phoneNumber;
        this.birthOfPlace = birthOfPlace;
        this.birthOfDate = birthOfDate;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBirthOfPlace() {
        return birthOfPlace;
    }

    public void setBirthOfPlace(String birthOfPlace) {
        this.birthOfPlace = birthOfPlace;
    }

    public String getBirthOfDate() {
        return birthOfDate;
    }

    public void setBirthOfDate(String birthOfDate) {
        this.birthOfDate = birthOfDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
               "id=" + id +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", email='" + email + '\'' +
               ", position='" + position + '\'' +
               ", idCard='" + idCard + '\'' +
               ", phoneNumber='" + phoneNumber + '\'' +
               ", birthOfPlace='" + birthOfPlace + '\'' +
               ", birthOfDate='" + birthOfDate + '\'' +
               '}';
    }
}