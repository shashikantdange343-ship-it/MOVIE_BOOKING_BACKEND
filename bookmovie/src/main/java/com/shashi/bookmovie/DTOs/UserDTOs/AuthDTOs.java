package com.shashi.bookmovie.DTOs.UserDTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Value;

public class AuthDTOs {
    //LOGIN DTOs
    public record LoginRequestDTO(

            @NotBlank
            String userName ,

            @NotBlank
            String password){}
    public record LoginResponseDTO(String token , String message){}

    //REGISTER DTOs
    public record RegisterRequestDTO(

            @Email(message = "EMAIL FORMAT WRONG OR EMAIL BLANK !")
            String email ,

            @NotBlank(message = "PASSWORD IS MUST !")
            String password ,

            @NotBlank (message = "FIRST NAME MUST !")
            String firstName ,

            @NotBlank(message = "LAST NAME MUST !")
            String lastName ,

            @Size(min = 10 , max = 10, message = "PHONE NUMBER MUST !")
            String PhoneNo,

            @NotBlank(message = "USER NAME MUST !")
            String userName ){}
}
