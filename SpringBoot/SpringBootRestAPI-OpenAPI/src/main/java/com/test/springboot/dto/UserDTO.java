package com.test.springboot.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;

    //user firstname should not be null or empty
    @NotEmpty(message = "User First Name should not be Empty or Null")
    private String firstName;

    //user last name should not be null or empty
    @NotEmpty(message = "User Last Name should not be Empty or Null")
    private String lastName;

    //user email should not be null or empty
    //Email Address should be valid
    @NotEmpty(message="User Email should not be Null or Empty")
    @Email(message="Email Address Should be valid...!")
    private String email;
}
