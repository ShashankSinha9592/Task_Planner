package org.example.Model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDetails {

    @Email(message = "Invalid email")
    private String email;

    @Size(min = 6 , max = 12 , message = "Invalid password")
    private String password;

}
