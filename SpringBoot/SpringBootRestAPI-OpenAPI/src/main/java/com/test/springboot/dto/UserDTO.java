package com.test.springboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        description = "UserDTO Model Information"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;

    @Schema(description = "User First Name")
    //user firstname should not be null or empty
    @NotEmpty(message = "User First Name should not be Empty or Null")
    private String firstName;
    @Schema(description = "User Last Name")
    //user last name should not be null or empty
    @NotEmpty(message = "User Last Name should not be Empty or Null")
    private String lastName;

    @Schema(description = "User Email Address")
    //user email should not be null or empty
    //Email Address should be valid
    @NotEmpty(message="User Email should not be Null or Empty")
    @Email(message="Email Address Should be valid...!")
    private String email;
}
