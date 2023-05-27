package com.schoolmanagement.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ContactMessageRequest {

    @NotNull(message = "Please enter name")
    @Size(min=4, max=16, message = "Your name should be at least 4 chars")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "your message must consist of the character.") //bosluk olmayacak ve en az bir karakter olacak
    private String name;

    @Email(message = "Please enter valid email")
    @Size(min=5, max=20, message = "You remail should be at least 5 chars")
    @NotNull(message = "Please enter your valid email")
    private String email;

    @Email(message = "Please enter subject")
    @Size(min=4, max=50, message = "Your subject should be at least 4 chars")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "your subject must consist of the character.")
    private String subject;

    @Email(message = "Please enter subject")
    @Size(min=4, max=50, message = "Your message should be at least 4 chars")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "your message must consist of the character.")
    private String message;
}
