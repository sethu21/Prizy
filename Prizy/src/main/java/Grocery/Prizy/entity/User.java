package Grocery.Prizy.entity;

import Grocery.Prizy.validation.ValidPassword;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[a-zA-Z0-9]{5,20}$", message = "Username must be alphanumeric and between 5 to 20 characters long")
    @NotEmpty
    private String username;



    @Email(regexp = ".+@.+\\..+", message = "Email must be valid")
    @NotEmpty
    private String email;

    @ValidPassword
    @NotEmpty
    private String password;
}