package com.blace.excursion.dto;

import com.blace.excursion.model.Role;
import com.blace.excursion.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private List<String> roles;
    private String phoneNumber;

    public UserDTO(User user) {
        super();
        this.id = user.getId();
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.roles = rolesToString(user);
        this.phoneNumber = user.getPhoneNumber();
    }

    private List<String> rolesToString(User user) {
        List<String> roles = new ArrayList<String>();
        for (Role role : user.getRoles()) {
            roles.add(role.getName());
        }

        return roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "UserDTO [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
                + ", email=" + email + ", roles=" + roles + ", phoneNumber=" + phoneNumber + "]";
    }

}
